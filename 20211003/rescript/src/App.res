@react.component
let make = () => {
  let name = "world"

  <div>
    {React.string("Hello")}
    <T.Text value={", " ++ Js.String.toUpperCase(name) ++ "!"} />
    <Text value=` ( >ㅅ<)=b` />
  </div>
}
