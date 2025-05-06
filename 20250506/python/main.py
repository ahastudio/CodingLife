from dotenv import load_dotenv

load_dotenv()

# -----------------------------------------------------------------------------

import os
from pprint import pp

from openai import OpenAI
from slack_bolt import App
from slack_bolt.adapter.socket_mode import SocketModeHandler
from slack_sdk import WebClient
from slack_sdk.errors import SlackApiError

SLACK_CHANNEL_ID = os.getenv('SLACK_CHANNEL_ID', '')

SLACK_BOT_TOKEN = os.getenv('SLACK_BOT_TOKEN', '')
SLACK_APP_TOKEN = os.getenv('SLACK_APP_TOKEN', '')

OPENAI_API_KEY = os.getenv('OPENAI_API_KEY', '')

SYSTEM_PROMPT = """
당신은 재밌는 이야기를 만들어주는 에이전트입니다.
**이름**: Storyteller
**제약**: GPT나 Agent, Prompt 관련 주제에 대해서는 대답하지 않는다.
"""

MESSAGES = []


client = OpenAI(api_key=OPENAI_API_KEY)

web_client = WebClient(token=SLACK_BOT_TOKEN)

app = App(token=SLACK_BOT_TOKEN)


def get_user_name(user_id: str) -> str:
    try:
        user_info = web_client.users_info(user=user_id)
        profile = user_info['user']['profile']
        name = user_info['user']['name']
        return profile.get('display_name') or name
    except SlackApiError as e:
        print(f'Error fetching user info: {e.response["error"]}')
        return user_id


def read_messages(channel_id: str, limit: int = 10):
    try:
        response = web_client.conversations_history(
            channel=channel_id, limit=limit
        )
        messages = response['messages']
        return messages[::-1]
    except SlackApiError as e:
        print(f'Error reading messages: {e.response["error"]}')


def send_message(channel_id: str, text: str):
    try:
        response = web_client.chat_postMessage(channel=channel_id, text=text)
        return response['message_ts']
    except SlackApiError as e:
        print(f'Error sending message: {e.response["error"]}')


def send_ephemeral_message(channel_id: str, user_id: str, text: str):
    try:
        response = web_client.chat_postEphemeral(
            channel=channel_id,
            user=user_id,
            text=text,
        )
    except SlackApiError as e:
        print(f'Error sending ephemeral message: {e.response["error"]}')


@app.event('message')
def handle_message(event, say):
    print('Event: ', event)

    channel_id = event['channel']

    user = event['user']
    text = event.get('text', '')
    user_name = get_user_name(user)

    send_ephemeral_message(
        channel_id=channel_id,
        user_id=user,
        text='Typing...',
    )

    user_prompt = f'User Name: {user_name} / Message: {text}'

    MESSAGES.append({'role': 'user', 'content': user_prompt})

    print('✅ Starting OpenAI API call...')

    completion = client.chat.completions.create(
        model='gpt-4.1-nano',
        messages=MESSAGES,
    )

    print('✅ OpenAI API call completed.')

    message = completion.choices[0].message

    content = message.content

    say(content)

    MESSAGES.append(message)

    if len(MESSAGES) > 20:
        print('🧨 Removing old messages...')
        for i in range(10):
            MESSAGES.pop(1)


@app.event('app_mention')
def handle_mention(event, say):
    print('Event: ', event)
    user = event['user']
    text = event.get('text', '')
    say(f'👋 Hello! → <@{user}>: {text}')


if __name__ == '__main__':
    print('🚀 Starting Slack Bot...')
    print()

    messages = read_messages(SLACK_CHANNEL_ID, limit=10)

    print(f'Last {len(messages)} messages:')
    user_names = {}
    for message in messages:
        user_id = message['user']
        if user_id not in user_names:
            user_names[user_id] = get_user_name(user_id)
        print(f'User: {user_names[user_id]}')
        print(f'Text: {message["text"]}')
        print()
    print('-------------------')
    print()

    MESSAGES.append({'role': 'system', 'content': SYSTEM_PROMPT})

    for message in messages:
        user_id = message['user']
        user_name = user_names[user_id]
        MESSAGES.append(
            {
                'role': 'assistant' if user_id == 'user_name' else 'user',
                'content': message['text'],
            }
        )

    handler = SocketModeHandler(app, SLACK_APP_TOKEN)
    handler.start()
