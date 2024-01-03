import csv
import os

from kafka import KafkaProducer

from events import Cycle, Row, SignalEvent
from schema import load_schema


def load_cycles_csv() -> list[Cycle]:
    with open("items.csv", newline="") as f:
        reader = csv.DictReader(f)

        items = []

        for row in reader:
            if row["index"]:
                item = Cycle(
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
    topic = "test"

    bootstrap_server = os.environ.get("BOOTSTRAP_SERVER", "")
    sasl_plain_username = os.environ.get("SASL_PLAIN_USERNAME", "")
    sasl_plain_password = os.environ.get("SASL_PLAIN_PASSWORD", "")

    sasl = {}

    if sasl_plain_username:
        sasl = {
            "security_protocol": "SASL_PLAINTEXT",
            "sasl_mechanism": "SCRAM-SHA-256",
            "sasl_plain_username": sasl_plain_username,
            "sasl_plain_password": sasl_plain_password,
        }

    print("Bootstrap Server:", bootstrap_server)

    producer = KafkaProducer(bootstrap_servers=[bootstrap_server], **sasl)

    schema = load_schema("schema.avsc", SignalEvent)

    cycles = load_cycles_csv()

    event = SignalEvent(
        motor_name="X-42",
        timestamp="2023-09-14 17:07:38.996",
        number_of_cycles=600,
        cycle_width=16_666,
        sample_count=128,
        validity=1,
        cycles=cycles,
    )

    message = schema.encode(event)

    print("Total bytes:", len(message))
    print("->", message[:256])

    producer.send(topic, message)

    producer.close()


if __name__ == "__main__":
    main()
