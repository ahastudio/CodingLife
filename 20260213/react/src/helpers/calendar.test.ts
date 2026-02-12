import { getCalendarDates, isSameDay, isSameMonth } from './calendar'

describe('getCalendarDates', () => {
  it('returns dates starting from Sunday of the first week', () => {
    // 2026년 2월 1일은 일요일
    const dates = getCalendarDates(2026, 1)
    expect(dates[0]).toEqual(new Date(2026, 1, 1))
  })

  it('returns dates ending at Saturday of the last week', () => {
    // 2026년 2월 28일은 토요일
    const dates = getCalendarDates(2026, 1)
    expect(dates[dates.length - 1]).toEqual(new Date(2026, 1, 28))
  })

  it('includes padding days from previous month', () => {
    // 2026년 3월 1일은 일요일
    const dates = getCalendarDates(2026, 2)
    expect(dates[0]).toEqual(new Date(2026, 2, 1))
  })

  it('pads from previous month when month starts mid-week', () => {
    // 2026년 1월 1일은 목요일
    const dates = getCalendarDates(2026, 0)
    // 첫 번째 날은 이전 달 일요일 (2025년 12월 28일)
    expect(dates[0]).toEqual(new Date(2025, 11, 28))
  })

  it('length is always a multiple of 7', () => {
    const dates = getCalendarDates(2026, 1)
    expect(dates.length % 7).toBe(0)
  })
})

describe('isSameDay', () => {
  it('returns true for the same date', () => {
    const a = new Date(2026, 1, 13)
    const b = new Date(2026, 1, 13)
    expect(isSameDay(a, b)).toBe(true)
  })

  it('returns false for different dates', () => {
    const a = new Date(2026, 1, 13)
    const b = new Date(2026, 1, 14)
    expect(isSameDay(a, b)).toBe(false)
  })
})

describe('isSameMonth', () => {
  it('returns true for date in the same month', () => {
    expect(isSameMonth(new Date(2026, 1, 15), 2026, 1)).toBe(true)
  })

  it('returns false for date in a different month', () => {
    expect(isSameMonth(new Date(2026, 0, 31), 2026, 1)).toBe(false)
  })
})
