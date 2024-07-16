package lydia.ralph.api;

import lydia.ralph.UserRepository;
import lydia.ralph.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@RestController
public class ApiController {

    DecimalFormat POUNDS_PENCE_FORMAT = new DecimalFormat("'£'0.00");

    UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "TODO: Welcome to Oyster Card System";
    }

    @GetMapping("/balance")
    public String viewBalance(@RequestParam String userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return String.format("Balance remaining for User %s: %s", userId, POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }

    @PutMapping("/balance")
    public String balance(@RequestParam String userId, @RequestParam BigDecimal amount) {
        User user = userRepository.findById(userId).orElseThrow();

        user.setBalance(user.getBalance().add(amount));
        user.setNew(false);
        userRepository.save(user);

        return String.format("New balance for user %s: %s", userId, POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }

    @PutMapping("/tap")
    public String tap(@RequestParam(name = "userId") String userId, @RequestParam(name = "stationName") String stationName) {
        return String.format("TODO: Implement topUpBalance for userId %s, station name %s", userId, stationName);
    }
}
