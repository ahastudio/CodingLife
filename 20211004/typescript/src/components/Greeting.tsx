export default function Greeting({ name, age }: {
  name: string;
  age?: number;
}) {
  return (
    <div>
      <p>
        Hello,
        {' '}
        {name}
        {age ? (
          <>
            (
            {age}
            )
          </>
        ) : null}
        !
      </p>
    </div>
  );
}
