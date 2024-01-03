from typing import TypedDict


class Row(TypedDict):
    u: float
    v: float
    w: float


class Cycle(TypedDict):
    index: int
    timestamp: str
    width: int
    rows: list[Row]


class SignalEvent(TypedDict):
    motor_name: str
    timestamp: str
    number_of_cycles: int
    cycle_width: int
    sample_count: int
    validity: int
    cycles: list[Cycle]
