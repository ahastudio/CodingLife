export function getCalendarDates(year: number, month: number): Date[] {
  const firstDayOfMonth = new Date(year, month, 1)
  const firstDayOfWeek = firstDayOfMonth.getDay()
  const calendarStart = new Date(year, month, 1 - firstDayOfWeek)

  const lastDayOfMonth = new Date(year, month + 1, 0)
  const lastDayOfWeek = lastDayOfMonth.getDay()
  const calendarEnd = new Date(year, month + 1, 6 - lastDayOfWeek)

  const dates: Date[] = []
  const current = new Date(calendarStart)
  while (current <= calendarEnd) {
    dates.push(new Date(current))
    current.setDate(current.getDate() + 1)
  }

  return dates
}

export function isSameDay(dateA: Date, dateB: Date): boolean {
  return (
    dateA.getFullYear() === dateB.getFullYear() &&
    dateA.getMonth() === dateB.getMonth() &&
    dateA.getDate() === dateB.getDate()
  )
}

export function isSameMonth(date: Date, year: number, month: number): boolean {
  return date.getFullYear() === year && date.getMonth() === month
}
