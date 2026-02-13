interface CalendarHeaderProps {
  year: number
  month: number
  onPrevMonth: () => void
  onNextMonth: () => void
  onGoToToday: () => void
}

export default function CalendarHeader({
  year,
  month,
  onPrevMonth,
  onNextMonth,
  onGoToToday,
}: CalendarHeaderProps) {
  return (
    <header className="flex items-center justify-between mb-4">
      <button
        aria-label="이전 달"
        onClick={onPrevMonth}
        className="px-3 py-1 rounded hover:bg-gray-200"
      >
        ◀
      </button>
      <h2 className="text-xl font-bold">{year}년 {month + 1}월</h2>
      <button
        aria-label="다음 달"
        onClick={onNextMonth}
        className="px-3 py-1 rounded hover:bg-gray-200"
      >
        ▶
      </button>
      <button
        onClick={onGoToToday}
        className="px-3 py-1 rounded bg-blue-500 text-white hover:bg-blue-600"
      >
        오늘
      </button>
    </header>
  )
}
