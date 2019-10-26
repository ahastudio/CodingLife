let add = (x, y) => x + y;
let increase = add(1);

[@react.component]
let make = () => {
  let (count, setCount) = React.useState(_ => 0);

  <div>
    <p onClick={_ => setCount(_ => increase(count))}>
      {React.string("Hello, world! (" ++ string_of_int(count) ++ ")")}
    </p>
  </div>
};
