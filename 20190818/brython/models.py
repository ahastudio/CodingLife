from datetime import datetime


class Post:
    def __init__(self, body):
        self.body = body
        self.created_at = datetime.now()

    def dict(self):
        return {
            'body': self.body,
            'created_at': self.created_at.strftime('%Y-%m-%d %H:%M:%S')
        }
