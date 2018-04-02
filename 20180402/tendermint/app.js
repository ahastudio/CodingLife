const lotion = require('lotion');

let app = lotion({
  initialState: {
    balances: {
      'a': 1000,
      'b': 0,
    },
  },
  tendermintPort: 46657,
  p2pPort: 46658,
});

app.use((state, tx) => {
  console.log('<< Transaction >>')
  console.log(tx);
  const amount = parseInt(tx.amount);
  if (amount < 0) {
    console.log('invalid amount');
    return;
  }
  if (state.balances[tx.from] < amount) {
    console.log('not enough coins');
    return;
  }
  state.balances[tx.from] -= amount;
  state.balances[tx.to] += amount;
});

app.listen(3000);
