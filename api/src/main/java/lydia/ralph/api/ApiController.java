package lydia.ralph.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/")
    public String home() {
        return "TODO: Welcome to Oyster Card System";
    }

    @GetMapping("/balance")
    public String viewBalance() {
        return "TODO: Implement viewBalance";
    }

    @PutMapping("/balance")
    public String balance(@RequestParam double amount) {
        return String.format("TODO: Implement balance: adding £%f", amount);
    }

    @PutMapping("/tap")
    public String tap(@RequestParam(name = "userId") String userId, @RequestParam(name = "stationName") String stationName) {
        return String.format("TODO: Implement topUpBalance for userId %s, station name %s", userId, stationName);
    }
}