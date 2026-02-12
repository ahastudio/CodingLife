import { useState } from 'react'

export default function Calendar({ today }: { today: Date }) {
  const [currentDate, setCurrentDate] = useState(
    () => new Date(today.getFullYear(), today.getMonth(), 1),
  )

  const year = currentDate.getFullYear()
  const month = currentDate.getMonth()

  const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토']

  const firstDay = new Date(year, month, 1).getDay()
  const daysInMonth = new Date(year, month + 1, 0).getDate()

  const handlePrev = () => {
    setCurrentDate(new Date(year, month - 1, 1))
  }

  const handleNext = () => {
    setCurrentDate(new Date(year, month + 1, 1))
  }

  const handleToday = () => {
    setCurrentDate(new Date(today.getFullYear(), today.getMonth(), 1))
  }

  const isToday = (day: number) =>
    year === today.getFullYear() &&
    month === today.getMonth() &&
    day === today.getDate()

  const dates: (number | null)[] = []
  for (let i = 0; i < firstDay; i++) {
    dates.push(null)
  }
  for (let d = 1; d <= daysInMonth; d++) {
    dates.push(d)
  }

  return (
    <div>
      <header>
        <button aria-label="이전 달" onClick={handlePrev}>◀</button>
        <h2>{year}년 {month + 1}월</h2>
        <button aria-label="다음 달" onClick={handleNext}>▶</button>
        <button onClick={handleToday}>오늘</button>
      </header>
      <table>
        <thead>
          <tr>
            {daysOfWeek.map((day) => (
              <th key={day}>{day}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {Array.from(
            { length: Math.ceil(dates.length / 7) },
            (_, weekIdx) => (
              <tr key={weekIdx}>
                {dates.slice(weekIdx * 7, weekIdx * 7 + 7).map((date, i) => (
                  <td
                    key={i}
                    role="cell"
                    className={date !== null && isToday(date) ? 'today' : ''}
                  >
                    {date}
                  </td>
                ))}
              </tr>
            ),
          )}
        </tbody>
      </table>
    </div>
  )
}
