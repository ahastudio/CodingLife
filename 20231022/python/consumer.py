import os
from datetime import datetime

from kafka import KafkaConsumer

from events import SignalEvent
from schema import load_schema


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

    consumer = KafkaConsumer(
        topic,
        bootstrap_servers=[bootstrap_server],
        **sasl,
        # auto_offset_reset="earliest",
        api_version=(0, 10, 0),
    )

    schema = load_schema("schema.avsc", SignalEvent)

    print("Topics:", consumer.topics())

    while True:
        topic_records = consumer.poll(timeout_ms=1_000)
        if not topic_records:
            continue

        print()

        for records in topic_records.values():
            for record in records:
                process_record(record, schema)


def process_record(record, schema):
    timestamp = datetime.fromtimestamp(record.timestamp / 1_000)

    print(
        f"Record - topic: {record.topic} / partition: {record.partition}"
        f" / offset: {record.offset} / timestamp: {timestamp}"
        f" / value length: {len(record.value)}"
    )
    print("-> ", record.value[:256])

    if len(record.value) < 1_000_000:
        return

    event = schema.decode(record.value)
    for key in event.keys():
        if key == "cycles":
            continue
        print(f"- {key}: {event[key]}")

    print(f"- cycles: {len(event['cycles'])}")

    # for cycle in event["cycles"]:
    #     print(
    #         f"  - Index: {cycle['index']}"
    #         f" / Timestamp: {cycle['timestamp']}"
    #         f" / Width: {cycle['width']}"
    #         f" / Rows: {len(cycle['rows'])}"
    #     )


if __name__ == "__main__":
    main()
