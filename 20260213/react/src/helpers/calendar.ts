export function getCalendarDates(year: number, month: number): Date[] {
  const firstDayOfMonth = new Date(year, month, 1)
  const startDay = firstDayOfMonth.getDay()
  const start = new Date(year, month, 1 - startDay)

  const lastDayOfMonth = new Date(year, month + 1, 0)
  const endDay = lastDayOfMonth.getDay()
  const end = new Date(year, month + 1, 6 - endDay)

  const dates: Date[] = []
  const current = new Date(start)
  while (current <= end) {
    dates.push(new Date(current))
    current.setDate(current.getDate() + 1)
  }

  return dates
}

export function isSameDay(a: Date, b: Date): boolean {
  return (
    a.getFullYear() === b.getFullYear() &&
    a.getMonth() === b.getMonth() &&
    a.getDate() === b.getDate()
  )
}

export function isSameMonth(date: Date, year: number, month: number): boolean {
  return date.getFullYear() === year && date.getMonth() === month
}
