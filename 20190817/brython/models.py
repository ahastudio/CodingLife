from datetime import datetime


class Post:
    def __init__(self, body):
        self.body = body
        self.created_at = datetime.now()
