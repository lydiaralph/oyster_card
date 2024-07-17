package lydia.ralph.api;

import lydia.ralph.repositories.UserRepository;
import lydia.ralph.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@RestController
public class ApiController {

    String BALANCE_REMAINING_STR = "Balance remaining for User %s: %s";

    DecimalFormat POUNDS_PENCE_FORMAT = new DecimalFormat("'£'0.00");

    private final UserRepository userRepository;

    public ApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String home() {
        return "TODO: Welcome to Oyster Card System";
    }

    @GetMapping("/balance")
    public String viewBalance(@RequestParam String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return String.format(BALANCE_REMAINING_STR, userId, POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }

    @PutMapping("/balance")
    public String topUpBalance(@RequestParam String userId, @RequestParam BigDecimal amount) {
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
