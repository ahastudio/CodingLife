import csv
import os

from kafka import KafkaProducer

from events import Item, Row, TestEvent
from schema import load_schema


def load_items_csv() -> list[Item]:
    with open("items.csv", newline="") as f:
        reader = csv.DictReader(f)

        items = []

        for row in reader:
            if row["index"]:
                item = Item(
                    index=int(row["index"]),
                    timestamp=row["timestamp"],
                    width=int(row["width"]),
                    rows=[],
                )
                items.append(item)

            item = items[-1]
            item["rows"].append(
                Row(
                    u=float(row["u"]),
                    v=float(row["v"]),
                    w=float(row["w"]),
                )
            )

        return items


def main():
    topic = "test-topic"

    bootstrap_server = os.environ.get("BOOTSTRAP_SERVER", "")
    print("Bootstrap Server:", bootstrap_server)

    producer = KafkaProducer(bootstrap_servers=[bootstrap_server])

    schema = load_schema("schema.avsc", TestEvent)

    items = load_items_csv()

    event = TestEvent(
        name="Deep Thought 42",
        timestamp="2023-09-14 17:07:38.996",
        validity=1,
        sample=128,
        width=16_666,
        count=600,
        items=items,
    )

    message = schema.encode(event)

    print("Total bytes:", len(message))
    print("->", message[:256])

    producer.send(topic, message)

    producer.close()


if __name__ == "__main__":
    main()
