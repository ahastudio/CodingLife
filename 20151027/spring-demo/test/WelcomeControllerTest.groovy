class WelcomeControllerTest {

    @Test
    void index() {
        assertEquals("home", new WelcomeController().index())
    }

}
