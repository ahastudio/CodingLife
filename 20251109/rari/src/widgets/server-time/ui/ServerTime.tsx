const dateFormatter = new Intl.DateTimeFormat('ko-KR', {
  year: 'numeric',
  month: '2-digit',
  day: '2-digit',
  hour: '2-digit',
  minute: '2-digit',
  second: '2-digit',
});

export default async function ServerTime() {
  const timestamp = dateFormatter.format(new Date());

  await new Promise((resolve) => setTimeout(resolve, 100));

  return (
    <section className="rounded-2xl border border-slate-200 bg-white/70 p-6 shadow-sm">
      <p className="text-sm text-slate-500">서버 기준 현재 시각</p>
      <p className="mt-2 font-mono text-xl text-slate-900">{timestamp}</p>
    </section>
  );
}
