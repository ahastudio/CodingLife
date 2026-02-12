import { useState } from 'react'

import { getCalendarDates } from '../helpers/calendar'

import CalendarHeader from './CalendarHeader'
import CalendarGrid from './CalendarGrid'

export default function Calendar({ today }: { today: Date }) {
  const [currentDate, setCurrentDate] = useState(
    () => new Date(today.getFullYear(), today.getMonth(), 1),
  )

  const year = currentDate.getFullYear()
  const month = currentDate.getMonth()

  const calendarDates = getCalendarDates(year, month)

  const handlePrevMonth = () => {
    setCurrentDate(new Date(year, month - 1, 1))
  }

  const handleNextMonth = () => {
    setCurrentDate(new Date(year, month + 1, 1))
  }

  const handleGoToToday = () => {
    setCurrentDate(new Date(today.getFullYear(), today.getMonth(), 1))
  }

  const weeks: Date[][] = []
  for (let startIndex = 0; startIndex < calendarDates.length; startIndex += 7) {
    weeks.push(calendarDates.slice(startIndex, startIndex + 7))
  }

  return (
    <div className="max-w-md mx-auto p-4">
      <CalendarHeader
        year={year}
        month={month}
        onPrevMonth={handlePrevMonth}
        onNextMonth={handleNextMonth}
        onGoToToday={handleGoToToday}
      />
      <CalendarGrid
        weeks={weeks}
        year={year}
        month={month}
        today={today}
      />
    </div>
  )
}
