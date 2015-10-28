@Grab("spring-boot-starter-freemarker")

@Controller
public class Application {

    @RequestMapping("/")
    def home() {
        return "home"
    }
}
