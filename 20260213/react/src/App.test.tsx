import { render, screen } from '@testing-library/react'
import App from './App'

describe('App', () => {
  it('renders calendar with current year and month', () => {
    render(<App today={new Date(2026, 1, 13)} />)

    expect(screen.getByText('2026년 2월')).toBeInTheDocument()
  })
})
