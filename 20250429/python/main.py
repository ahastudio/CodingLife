import os
from datetime import datetime

from dotenv import load_dotenv
from openai import OpenAI

load_dotenv()


client = OpenAI(api_key=os.getenv('OPENAI_API_KEY', ''))


def read_system_prompt():
    with open('prompts/system.txt', 'r', encoding='utf-8') as file:
        return file.read()


def read_user_prompt():
    with open('prompts/user.txt', 'r', encoding='utf-8') as file:
        return file.read()


def write_output(content):
    filename = datetime.now().strftime('%Y%m%d-%H%M%S')
    with open(f'output/{filename}.md', 'w', encoding='utf-8') as file:
        file.write(content)


def main():
    system_prompt = read_system_prompt()
    user_prompt = read_user_prompt()

    print('=' * 80)
    print('🤖 System Prompt:')
    print(system_prompt)
    print('=' * 80)
    print('🤖 User Prompt:')
    print(user_prompt)
    print('=' * 80)
    print()

    print('🚀 Generating content...')

    messages = [
        {'role': 'system', 'content': system_prompt},
        {'role': 'user', 'content': user_prompt},
    ]

    completion = client.chat.completions.create(
        model='gpt-4.1-mini',
        messages=messages,
        max_tokens=32_768,
        temperature=0.8,
        top_p=0.95,
    )

    content = completion.choices[0].message.content

    print('✅ Content generated successfully!')

    write_output(content)

    print('✅ Output saved to file!')


main()
