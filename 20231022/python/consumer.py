import os
from datetime import datetime

from kafka import KafkaConsumer

from events import TestEvent
from schema import load_schema


def main():
    topic = "test-topic"

    bootstrap_server = os.environ.get("BOOTSTRAP_SERVER", "")
    print("Bootstrap Server:", bootstrap_server)

    consumer = KafkaConsumer(
        topic,
        bootstrap_servers=[bootstrap_server],
        auto_offset_reset="earliest",
    )

    schema = load_schema("schema.avsc", TestEvent)

    for message in consumer:
        timestamp = datetime.fromtimestamp(message.timestamp / 1_000)
        print(
            f"Record - topic: {message.topic} / partition: {message.partition}"
            f" / offset: {message.offset} / timestamp: {timestamp}"
            f" / value length: {len(message.value)}"
        )
        print("-> ", message.value[:256])

        if len(message.value) < 1_000_000:
            continue

        event = schema.decode(message.value)
        for key in event.keys():
            if key == "items":
                continue
            print(f"- {key}: {event[key]}")
        print("- Items: ", len(event["items"]))


if __name__ == "__main__":
    main()
