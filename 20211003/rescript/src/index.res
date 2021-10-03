Js.log("Hello, world!")

switch ReactDOM.querySelector("#app") {
| Some(el) => ReactDOM.render(<App />, el)
| None => ()
}
