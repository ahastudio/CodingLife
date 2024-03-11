import json
import os
from datetime import datetime

from kafka import KafkaProducer

from events import CartEvent
from schema import load_schema

TOPIC = "test"


def main():
    bootstrap_server = os.environ.get("BOOTSTRAP_SERVER", "")

    producer = KafkaProducer(bootstrap_servers=[bootstrap_server])

    schema = load_schema("schema.avsc", CartEvent)

    # cart_created 이벤트

    now = datetime.utcnow()
    event_id = str(int(now.timestamp() * 10**6))

    event = CartEvent(
        timestamp=now.strftime("%Y-%m-%d %H:%M:%S"),
        event_id=event_id,
        event_type="cart_created",
        event_body=json.dumps({"user_id": "1004"}),
    )

    message = schema.encode(event)

    producer.send(TOPIC, message)

    # cart_product_added 이벤트

    now = datetime.utcnow()
    event_id = str(int(now.timestamp() * 10**6))

    event = CartEvent(
        timestamp=now.strftime("%Y-%m-%d %H:%M:%S"),
        event_id=event_id,
        event_type="cart_product_added",
        event_body=json.dumps(
            {
                "user_id": "1004",
                "product_id": "1000",
                "product_name": "MacBook Air (M3)",
                "unit_price": 2_130_000,
                "quantity": 2,
            }
        ),
    )

    message = schema.encode(event)

    producer.send(TOPIC, message)

    ###

    producer.flush()

    producer.close()


if __name__ == "__main__":
    main()
