import asyncio

from websockets.server import serve
from websockets.exceptions import ConnectionClosed


MESSAGES = ['hello', 'hi', 'the end']


async def recv_messages(websocket):
    async for message in websocket:
        print(message)


async def send_messages(websocket):
    try:
        i = 0
        while True:
            message = MESSAGES[i % len(MESSAGES)]
            await websocket.send(message)
            i += 1
            await asyncio.sleep(1)
    except ConnectionClosed as e:
        print('Disconnect')


async def handler(websocket):
    print('Connect')
    recv_task = asyncio.create_task(recv_messages(websocket))
    send_task = asyncio.create_task(send_messages(websocket))
    await asyncio.wait([recv_task, send_task])


async def main():
    async with serve(handler, "localhost", 8000):
        print('Start!')
        await asyncio.Future()


asyncio.run(main())
