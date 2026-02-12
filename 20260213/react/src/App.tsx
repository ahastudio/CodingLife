import Calendar from './components/Calendar'

export default function App({ today = new Date() }: { today?: Date }) {
  return <Calendar today={today} />
}
