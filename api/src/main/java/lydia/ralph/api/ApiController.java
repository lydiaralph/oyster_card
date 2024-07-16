package lydia.ralph.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public String updateBalance() {
        return "TODO: Implement updateBalance";
    }

}
