export default function numberFormat(value: number) {
  return new Intl.NumberFormat().format(value);
}
