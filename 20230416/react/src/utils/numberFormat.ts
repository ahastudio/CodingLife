export default function numberFormat(value: number): string {
  return new Intl.NumberFormat().format(value);
}
