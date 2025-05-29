from fastapi import FastAPI
from pydantic import BaseModel

from src.rag import ask


class AskRequestDto(BaseModel):
    question: str


app = FastAPI()


@app.get('/')
def health():
    return 'Hello, world!'


@app.post('/ask')
def request_ask(requestDto: AskRequestDto):
    question = requestDto.question
    answer = ask(question)
    return {
        'answer': answer,
    }
