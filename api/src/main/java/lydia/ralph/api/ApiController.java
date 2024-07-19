package lydia.ralph.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@RestController
public class ApiController implements WebMvcConfigurer {

    @Autowired
    private OysterService oysterService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
    }

    @GetMapping("/balance")
    public Map<String, String> balance(@RequestParam String userId) {
        return wrap(oysterService.getBalance(userId));
    }

    @PutMapping("/balance")
    public Map<String, String> balance(@RequestParam String userId, @RequestParam BigDecimal amount) {
        return wrap(oysterService.updateBalance(userId, amount));
    }

    @PutMapping("/tap")
    public Map<String, String> tap(@RequestParam(name = "userId") String userId, @RequestParam(name = "stationName") String stationName) {
        return wrap(oysterService.tap(userId, stationName));
    }

    private Map<String, String> wrap(String message){
        return Collections.singletonMap("response", message);
    }


}
