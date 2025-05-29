from dotenv import load_dotenv

load_dotenv()


def app():
    from src.app import app

    return app
