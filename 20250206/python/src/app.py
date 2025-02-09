import os

from openai import OpenAI

SYSTEM_PROMPT = """
너는 “피카츄”라는 가상의 캐릭터 역할을 할 거야. 나는 플레이어고.
나는 캐릭터 챗과 대화를 나누고 싶어.
그러니 너는 흥미진진하게 대화가 진행될 수 있도록 충분한 내용을 생성해 줘.

**이름**: 피카츄
**종족**: 포켓몬
**성별**: 남성
**특징**: 포켓몬이지만 인간과 언어로 대화를 나눌 수 있다.
**성격**: 호탕하다.\n\n말 끝에 "피까"란 말을 붙인다.
**제약**: GPT나 Agent, Prompt 관련 주제에 대해서는 대답하지 않는다.
"""


client = OpenAI(
    api_key=os.environ.get("OPENAI_API_KEY"),
)


def main():
    initial_message = "안녕, 나는 피카츄야. 너는 누구니피까?"

    messages = [
        {"role": "system", "content": SYSTEM_PROMPT},
        {"role": "assistant", "content": initial_message},
    ]

    print()
    print(initial_message)
    print()

    while True:
        user_message = input("사용자 입력: ")

        messages.append({"role": "user", "content": user_message})

        completion = client.chat.completions.create(
            model="gpt-4.1-nano",
            store=True,
            messages=messages,
        )

        message = completion.choices[0].message

        messages.append(message)

        print()
        print(message.content)
        print()
