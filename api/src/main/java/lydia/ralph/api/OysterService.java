package lydia.ralph.api;

import lydia.ralph.domain.User;
import lydia.ralph.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Service
public class OysterService {

    @Autowired
    private UserRepository userRepository;

    private static final String BALANCE_REMAINING_STR = "Balance remaining for User %s: %s";

    private static final DecimalFormat POUNDS_PENCE_FORMAT = new DecimalFormat("'£'0.00");

    public String getBalance(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(userId));
        return String.format(BALANCE_REMAINING_STR, userId, POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }

    public String updateBalance(String userId, BigDecimal amount) {
        User user = userRepository.findById(userId).orElseThrow();

        user.setBalance(user.getBalance().add(amount));
        user.setNew(false);
        userRepository.save(user);

        return String.format("New balance for user %s: %s", userId, POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }
}
