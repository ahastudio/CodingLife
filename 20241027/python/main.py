from datetime import datetime, timedelta

import requests
from ics import Calendar

# CALENDAR_URL = (
#     "https://calendar.google.com/calendar/ical/"
#     "ko.south_korea%23holiday%40group.v.calendar.google.com"
#     "/public/basic.ics"
# )

CALENDAR_URL = "https://holidays.hyunbin.page/basic.ics"


def load_calendar():
    response = requests.get(CALENDAR_URL)
    response.raise_for_status()

    return Calendar(response.text)


def main():
    now = datetime.now()

    calendar = load_calendar()

    events = sorted(calendar.events, key=lambda event: event.begin)

    for event in events:
        start_date = event.begin.date()
        end_date = event.end.date() - timedelta(days=1)
        if now.year in (start_date.year, end_date.year):
            print(f"{event.name} ▶ {start_date} ~ {end_date}")
            print(f"(UID: {event.uid})")
            if start_date.year != end_date.year:
                print("⚠ This event is spanning multiple years.")
            print()


if __name__ == "__main__":
    main()
