import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import Calendar from './Calendar'

const today = new Date(2026, 1, 13) // 2026-02-13

describe('Calendar', () => {
  it('displays year and month', () => {
    render(<Calendar today={today} />)

    expect(screen.getByText('2026년 2월')).toBeInTheDocument()
  })

  it('displays day-of-week headers', () => {
    render(<Calendar today={today} />)

    const days = ['일', '월', '화', '수', '목', '금', '토']
    days.forEach((day) => {
      expect(screen.getByText(day)).toBeInTheDocument()
    })
  })

  it('highlights today', () => {
    render(<Calendar today={today} />)

    const todayCell = screen.getByRole('cell', { name: '13' })
    expect(todayCell).toHaveClass('today')
  })

  it('navigates to previous month', async () => {
    const user = userEvent.setup()
    render(<Calendar today={today} />)

    await user.click(screen.getByRole('button', { name: '이전 달' }))

    expect(screen.getByText('2026년 1월')).toBeInTheDocument()
  })

  it('navigates to next month', async () => {
    const user = userEvent.setup()
    render(<Calendar today={today} />)

    await user.click(screen.getByRole('button', { name: '다음 달' }))

    expect(screen.getByText('2026년 3월')).toBeInTheDocument()
  })

  it('returns to today when clicking today button', async () => {
    const user = userEvent.setup()
    render(<Calendar today={today} />)

    await user.click(screen.getByRole('button', { name: '다음 달' }))
    expect(screen.getByText('2026년 3월')).toBeInTheDocument()

    await user.click(screen.getByRole('button', { name: '오늘' }))
    expect(screen.getByText('2026년 2월')).toBeInTheDocument()
  })

  it('displays dates of the month', () => {
    render(<Calendar today={today} />)

    expect(screen.getByRole('cell', { name: '1' })).toBeInTheDocument()
    expect(screen.getByRole('cell', { name: '28' })).toBeInTheDocument()
  })

  it('handles year boundary (January to December)', async () => {
    const user = userEvent.setup()
    const january = new Date(2026, 0, 15) // 2026-01-15
    render(<Calendar today={january} />)

    await user.click(screen.getByRole('button', { name: '이전 달' }))

    expect(screen.getByText('2025년 12월')).toBeInTheDocument()
  })
})
