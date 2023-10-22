from typing import TypedDict


class Row(TypedDict):
    u: float
    v: float
    w: float


class Item(TypedDict):
    index: int
    timestamp: str
    width: int
    rows: list[Row]


class TestEvent(TypedDict):
    name: str
    timestamp: str
    validity: int
    sample: int
    width: int
    count: int
    items: list[Item]
