import json
from io import BytesIO
from pathlib import Path
from typing import Generic, Type, TypeVar

import fastavro.types
from fastavro import parse_schema, schemaless_reader, schemaless_writer

T = TypeVar("T")


class Schema(Generic[T]):
    _schema: fastavro.types.Schema

    def __init__(self, schema: fastavro.types.Schema):
        self._schema = schema

    def encode(self, message: T) -> bytes:
        out = BytesIO()
        schemaless_writer(out, self._schema, message)
        return out.getvalue()

    def decode(self, value: bytes) -> T:
        return schemaless_reader(BytesIO(value), self._schema)


def load_schema(pathname: str, _class: Type[T]) -> Schema[T]:
    with Path(pathname).open("r") as f:
        schema = json.load(f)
        return Schema[T](parse_schema(schema))
