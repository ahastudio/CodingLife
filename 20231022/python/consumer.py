import json
import os
import pprint

from kafka import KafkaConsumer

from events import CartEvent
from schema import load_schema

TOPIC = "test"


def main():
    bootstrap_server = os.environ.get("BOOTSTRAP_SERVER", "")

    consumer = KafkaConsumer(
        TOPIC,
        bootstrap_servers=[bootstrap_server],
        # 테스트를 위한 옵션: 처음부터 시작해서 지난 이벤트를 모두 받는다.
        auto_offset_reset="earliest",
    )

    schema = load_schema("schema.avsc", CartEvent)

    pp = pprint.PrettyPrinter()

    for message in consumer:
        event: CartEvent = schema.decode(message.value)
        print("-" * 80)
        print("Timestamp:", event["timestamp"])
        print("Event ID:", event["event_id"])
        print("Event type:", event["event_type"])
        pp.pprint(json.loads(event["event_body"]))


if __name__ == "__main__":
    main()
