from dotenv import load_dotenv

load_dotenv()

# ----------------------------------------------------------------------------

import os
from datetime import datetime

from google import genai
from google.genai import types

GEMINI_API_KEY = os.getenv('GEMINI_API_KEY')

client = genai.Client(api_key=GEMINI_API_KEY)


def main():
    with open('input/prompt.md', 'r') as f:
        prompt = f.read()

    with open('input/picture.jpg', 'rb') as f:
        image_bytes = f.read()

    response = client.models.generate_content(
        model='gemini-2.5-flash-preview-05-20',
        contents=[
            types.Part.from_bytes(
                data=image_bytes,
                mime_type='image/jpeg',
            ),
            prompt,
        ],
    )

    print(response.text)

    now = datetime.now()

    with open(f'output/result-{now.timestamp()}.json', 'w') as f:
        f.write(response.text)


if __name__ == '__main__':
    main()
