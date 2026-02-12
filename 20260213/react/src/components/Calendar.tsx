import { useState } from 'react'

import { getCalendarDates, isSameDay, isSameMonth } from '../helpers/calendar'

export default function Calendar({ today }: { today: Date }) {
  const [currentDate, setCurrentDate] = useState(
    () => new Date(today.getFullYear(), today.getMonth(), 1),
  )

  const year = currentDate.getFullYear()
  const month = currentDate.getMonth()

  const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토']
  const calendarDates = getCalendarDates(year, month)

  const handlePrev = () => {
    setCurrentDate(new Date(year, month - 1, 1))
  }

  const handleNext = () => {
    setCurrentDate(new Date(year, month + 1, 1))
  }

  const handleToday = () => {
    setCurrentDate(new Date(today.getFullYear(), today.getMonth(), 1))
  }

  const weeks: Date[][] = []
  for (let i = 0; i < calendarDates.length; i += 7) {
    weeks.push(calendarDates.slice(i, i + 7))
  }

  return (
    <div className="max-w-md mx-auto p-4">
      <header className="flex items-center justify-between mb-4">
        <button
          aria-label="이전 달"
          onClick={handlePrev}
          className="px-3 py-1 rounded hover:bg-gray-200"
        >
          ◀
        </button>
        <h2 className="text-xl font-bold">{year}년 {month + 1}월</h2>
        <button
          aria-label="다음 달"
          onClick={handleNext}
          className="px-3 py-1 rounded hover:bg-gray-200"
        >
          ▶
        </button>
        <button
          onClick={handleToday}
          className="px-3 py-1 rounded bg-blue-500 text-white hover:bg-blue-600"
        >
          오늘
        </button>
      </header>
      <table className="w-full table-fixed">
        <thead>
          <tr>
            {daysOfWeek.map((day) => (
              <th key={day} className="py-2 text-center text-sm text-gray-500">
                {day}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {weeks.map((week, weekIndex) => (
            <tr key={weekIndex}>
              {week.map((date, i) => {
                const isCurrentMonth = isSameMonth(date, year, month)
                const isDateToday = isSameDay(date, today)
                return (
                  <td
                    key={i}
                    role="cell"
                    className={[
                      'py-2 text-center',
                      isDateToday ? 'today bg-blue-500 text-white rounded-full font-bold' : '',
                      !isCurrentMonth ? 'text-gray-300' : '',
                    ].join(' ')}
                  >
                    {date.getDate()}
                  </td>
                )
              })}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
