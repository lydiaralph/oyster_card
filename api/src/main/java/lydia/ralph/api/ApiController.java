package lydia.ralph.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class ApiController {

    @Autowired
    private OysterService oysterService;

    @RequestMapping("/")
    public String home() {
        return "TODO: Welcome to Oyster Card System";
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
