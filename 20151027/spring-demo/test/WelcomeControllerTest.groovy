class WelcomeControllerSpec extends Specification {
    def "show the home page"() {
        setup:
        def controller = new WelcomeController()

        expect:
        controller.index() == "home"
    }
}
