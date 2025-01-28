package millerding.mines.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"https://mines.millerding.com", "http://localhost:3000"}) // Allow requests from these origins
public class ScoreController {
    private final List<UserScore> scores = new ArrayList<>();

    @PostMapping("/submit")
    public String submitScore(@RequestParam String username, @RequestParam double balance) {
        scores.add(new UserScore(username, balance));
        scores.sort((a, b) -> Double.compare(b.getBalance(), a.getBalance())); // Sort descending
        return "Score submitted!";
    }

    @GetMapping("/top")
    public List<UserScore> getTopScores() {
        return scores.stream().limit(10).toList(); // Return top 10
    }
}

class UserScore {
    private final String username;
    private final double balance;

    public UserScore(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }
}
