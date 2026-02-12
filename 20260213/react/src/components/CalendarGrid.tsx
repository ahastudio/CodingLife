import { isSameDay, isSameMonth } from '../helpers/calendar'

interface CalendarGridProps {
  weeks: Date[][]
  year: number
  month: number
  today: Date
}

const DAYS_OF_WEEK = ['일', '월', '화', '수', '목', '금', '토']

export default function CalendarGrid({
  weeks,
  year,
  month,
  today,
}: CalendarGridProps) {
  return (
    <table className="w-full table-fixed">
      <thead>
        <tr>
          {DAYS_OF_WEEK.map((day) => (
            <th key={day} className="py-2 text-center text-sm text-gray-500">
              {day}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {weeks.map((week, weekIndex) => (
          <tr key={weekIndex}>
            {week.map((date, dateIndex) => {
              const isCurrentMonth = isSameMonth(date, year, month)
              const isDateToday = isSameDay(date, today)
              return (
                <td
                  key={dateIndex}
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
  )
}
