package lydia.ralph.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;

@RestController
public class ApiController implements WebMvcConfigurer {

    @Autowired
    private OysterService oysterService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
    }

    @GetMapping("/balance")
    public String balance(@RequestParam String userId) {
        return oysterService.getBalance(userId);
    }

    @PutMapping("/balance")
    public String balance(@RequestParam String userId, @RequestParam BigDecimal amount) {
        return oysterService.updateBalance(userId, amount);
    }

    @PutMapping("/tap")
    public String tap(@RequestParam(name = "userId") String userId, @RequestParam(name = "stationName") String stationName) {
        return oysterService.tap(userId, stationName);
    }


}
