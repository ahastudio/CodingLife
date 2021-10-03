module Text = {
  @react.component
  let make = (~value: string) => {
    {React.string(value)}
  }
}
