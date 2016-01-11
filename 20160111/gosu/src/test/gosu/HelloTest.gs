uses org.junit.Assert
uses org.junit.Test

class HelloTest {

  @Test
  function simplee() {
    Assert.assertEquals(2, 1 + 1)
  }

  @Test
  function sayHello() {
    var hello = new Hello()
    Assert.assertEquals('Hello, world!', hello.say('world'))
  }
}
