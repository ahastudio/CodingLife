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

  // ── 수동 테스트 대체: 오늘 날짜 강조 확인 ──

  it('highlights only today and no other dates', () => {
    render(<Calendar today={today} />)

    const allCells = screen.getAllByRole('cell')
    const todayCells = allCells.filter((cell) =>
      cell.classList.contains('today'),
    )

    expect(todayCells).toHaveLength(1)
    expect(todayCells[0]).toHaveTextContent('13')
  })

  it('today cell has visual emphasis styling', () => {
    render(<Calendar today={today} />)

    const todayCell = screen.getByRole('cell', { name: '13' })
    expect(todayCell).toHaveClass('bg-blue-500')
    expect(todayCell).toHaveClass('text-white')
    expect(todayCell).toHaveClass('font-bold')
  })

  it('other-month dates are dimmed', () => {
    // 2026년 1월 1일은 목요일 → 앞에 12/28(일)~12/31(수) 패딩
    const jan = new Date(2026, 0, 15)
    render(<Calendar today={jan} />)

    const allCells = screen.getAllByRole('cell')
    // 첫 번째 셀은 2025년 12월 28일 (이전 달) → 흐리게
    expect(allCells[0]).toHaveClass('text-gray-300')
  })

  // ── 수동 테스트 대체: 이전/다음 달 이동 (연도 경계 포함) ──

  it('handles year boundary forward (December to January)', async () => {
    const user = userEvent.setup()
    const december = new Date(2025, 11, 15) // 2025-12-15
    render(<Calendar today={december} />)

    await user.click(screen.getByRole('button', { name: '다음 달' }))

    expect(screen.getByText('2026년 1월')).toBeInTheDocument()
  })

  it('navigates multiple months consecutively', async () => {
    const user = userEvent.setup()
    render(<Calendar today={today} />)

    // 2월 → 3월 → 4월 → 5월
    await user.click(screen.getByRole('button', { name: '다음 달' }))
    await user.click(screen.getByRole('button', { name: '다음 달' }))
    await user.click(screen.getByRole('button', { name: '다음 달' }))

    expect(screen.getByText('2026년 5월')).toBeInTheDocument()
  })

  it('dates update correctly after navigation', async () => {
    const user = userEvent.setup()
    render(<Calendar today={today} />)

    // 2월은 28일까지, 3월은 31일까지
    expect(screen.getByRole('cell', { name: '28' })).toBeInTheDocument()

    await user.click(screen.getByRole('button', { name: '다음 달' }))

    expect(screen.getByRole('cell', { name: '31' })).toBeInTheDocument()
  })

  // ── 수동 테스트 대체: 오늘 버튼 복귀 ──

  it('today button restores today highlight after navigating away', async () => {
    const user = userEvent.setup()
    render(<Calendar today={today} />)

    // 다른 달로 이동하면 today 강조가 없어야 함
    await user.click(screen.getByRole('button', { name: '다음 달' }))
    const cellsInMarch = screen.getAllByRole('cell')
    const todayInMarch = cellsInMarch.filter((cell) =>
      cell.classList.contains('today'),
    )
    expect(todayInMarch).toHaveLength(0)

    // 오늘 버튼으로 복귀하면 today 강조가 다시 보여야 함
    await user.click(screen.getByRole('button', { name: '오늘' }))
    const todayCell = screen.getByRole('cell', { name: '13' })
    expect(todayCell).toHaveClass('today')
  })

  it('today button works after navigating multiple months away', async () => {
    const user = userEvent.setup()
    render(<Calendar today={today} />)

    // 5개월 뒤로 이동
    for (let i = 0; i < 5; i++) {
      await user.click(screen.getByRole('button', { name: '다음 달' }))
    }
    expect(screen.getByText('2026년 7월')).toBeInTheDocument()

    // 오늘 버튼으로 한 번에 복귀
    await user.click(screen.getByRole('button', { name: '오늘' }))
    expect(screen.getByText('2026년 2월')).toBeInTheDocument()
    expect(screen.getByRole('cell', { name: '13' })).toHaveClass('today')
  })

  it('today button works after navigating to a different year', async () => {
    const user = userEvent.setup()
    render(<Calendar today={today} />)

    // 12개월 뒤로 → 2027년 2월
    for (let i = 0; i < 12; i++) {
      await user.click(screen.getByRole('button', { name: '다음 달' }))
    }
    expect(screen.getByText('2027년 2월')).toBeInTheDocument()

    await user.click(screen.getByRole('button', { name: '오늘' }))
    expect(screen.getByText('2026년 2월')).toBeInTheDocument()
    expect(screen.getByRole('cell', { name: '13' })).toHaveClass('today')
  })
})
