import os
from pprint import pp

from openai import OpenAI
from slack_bolt import App
from slack_bolt.adapter.socket_mode import SocketModeHandler
from slack_sdk import WebClient
from slack_sdk.errors import SlackApiError

from src.prompts import SYSTEM_PROMPT
from src.utils import markdown_to_slack_markup

CONVERSATION_HISTORY_LIMIT = 20
HISTORY_TRIM_COUNT = 10

SLACK_BOT_NAME = os.getenv('SLACK_BOT_NAME', '')

SLACK_BOT_TOKEN = os.getenv('SLACK_BOT_TOKEN', '')
SLACK_APP_TOKEN = os.getenv('SLACK_APP_TOKEN', '')

OPENAI_API_KEY = os.getenv('OPENAI_API_KEY', '')


class Application:
    openai_client: OpenAI
    web_client: WebClient
    bolt_app: App

    channel_messages: dict[str, list[str, dict]] = {}

    def __init__(self):
        self.openai_client = OpenAI(api_key=OPENAI_API_KEY)
        self.web_client = WebClient(token=SLACK_BOT_TOKEN)
        self.bolt_app = App(token=SLACK_BOT_TOKEN)

        self.register_listeners()

    def main(self) -> None:
        print('🚀 Starting Slack Bot...')
        print()

        handler = SocketModeHandler(self.bolt_app, SLACK_APP_TOKEN)
        handler.start()

    def register_listeners(self) -> None:
        self.bolt_app.event('message')(self.handle_message)
        self.bolt_app.event('app_mention')(self.handle_mention)

    def handle_message(self, event, say) -> None:
        print()
        print('▶️ handle_message: ', event)

        channel_id = event['channel']
        user = event['user']
        text = event.get('text', '')

        self.send_ephemeral_message(
            channel_id=channel_id, user_id=user, text='Thinking...'
        )

        content = self.process_user_message(
            channel_id=channel_id, user_id=user, text=text
        )

        say(markdown_to_slack_markup(content))

    def handle_mention(self, event, say) -> None:
        print()
        print('▶️ handle_mention: ', event)
        user = event['user']
        text = event.get('text', '')
        say(f'👋 Hello!, <@{user}>! You said: “{text}”.')

    def process_user_message(
        self, channel_id: str, user_id: str, text: str
    ) -> str:
        user_name = self.get_user_name(user_id)

        if channel_id not in self.channel_messages:
            print()
            print('⚠️ Channel ID not found in messages. Loading messages...')
            self.load_messages(channel_id)

        messages = self.channel_messages[channel_id]

        user_prompt = f'User Name: {user_name} / Message: {text}'
        messages.append({'role': 'user', 'content': user_prompt})

        print()
        print('✅ Starting OpenAI API call...')

        completion = self.openai_client.chat.completions.create(
            model='gpt-4.1-nano',
            messages=messages,
        )

        print('✅ OpenAI API call completed.')

        message = completion.choices[0].message

        messages.append(message)

        self.trim_messages(channel_id=channel_id)

        return message.content

    def trim_messages(self, channel_id: str) -> None:
        messages = self.channel_messages[channel_id]
        if len(messages) > CONVERSATION_HISTORY_LIMIT:
            print('🧨 Removing old messages...')
            for i in range(HISTORY_TRIM_COUNT):
                messages.pop(1)

    def load_messages(self, channel_id: str) -> None:
        print('🌏 Loading messages...')

        self.channel_messages[channel_id] = [
            {'role': 'system', 'content': SYSTEM_PROMPT}
        ]

        messages = self.read_messages(
            channel_id=channel_id, limit=CONVERSATION_HISTORY_LIMIT
        )

        user_names = {}
        for message in messages:
            user_id = message['user']
            if user_id not in user_names:
                user_names[user_id] = self.get_user_name(user_id)
            user_name = user_names[user_id]
            self.channel_messages[channel_id].append(
                {
                    'role': (
                        'assistant' if user_name == SLACK_BOT_NAME else 'user'
                    ),
                    'content': message['text'],
                }
            )

    def read_messages(self, channel_id: str, limit: int = 10) -> list[dict]:
        try:
            response = self.web_client.conversations_history(
                channel=channel_id, limit=limit
            )
            messages = response['messages']
            return messages[::-1]
        except SlackApiError as e:
            print(f'Error reading messages: {e.response["error"]}')

    def send_ephemeral_message(
        self, channel_id: str, user_id: str, text: str
    ) -> None:
        try:
            self.web_client.chat_postEphemeral(
                channel=channel_id,
                user=user_id,
                text=text,
            )
        except SlackApiError as e:
            print(f'Error sending ephemeral message: {e.response["error"]}')

    def get_user_name(self, user_id: str) -> str:
        try:
            user_info = self.web_client.users_info(user=user_id)
            profile = user_info['user']['profile']
            name = user_info['user']['name']
            return profile.get('display_name') or name
        except SlackApiError as e:
            print(f'Error fetching user info: {e.response["error"]}')
            return user_id
