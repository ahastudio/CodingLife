from typing import TypedDict


class CartEvent(TypedDict):
    timestamp: str
    event_id: str
    event_type: str
    event_body: str
