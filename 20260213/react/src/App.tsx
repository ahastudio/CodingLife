export default function App({ today = new Date() }: { today?: Date }) {
  const year = today.getFullYear()
  const month = today.getMonth() + 1

  return (
    <div>
      <h1>{year}년 {month}월</h1>
    </div>
  )
}
