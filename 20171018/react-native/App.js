import React from 'react';
import { Container, Header, Title, Text, Form, Item, Input } from 'native-base';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
    };
  }

  render() {
    const { name } = this.state;
    return (
      <Container>
        <Header>
          <Title>Hello!</Title>
        </Header>
        <Form>
          <Item>
            <Text>Hello, {name || 'world'}!</Text>
          </Item>
          <Item>
            <Input placeholder="Input your name"
                   value={name}
                   onChangeText={name => this.setState({ name })} />
          </Item>
        </Form>
      </Container>
    );
  }
}
