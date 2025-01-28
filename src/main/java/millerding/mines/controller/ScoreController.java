package millerding.mines.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"https://mines.millerding.com", "http://localhost:3000"})
public class ScoreController {
    private final Map<String, UserScore> scoreMap = new HashMap<>();

    @PostMapping("/submit")
    public String submitScore(@RequestParam String username, @RequestParam double balance) {
        UserScore existingScore = scoreMap.get(username);
        
        if (existingScore == null || existingScore.getBalance() < balance) {
            scoreMap.put(username, new UserScore(username, balance));
        }
        
        return "Score submitted!";
    }

    @GetMapping("/top")
    public List<UserScore> getTopScores() {
        return scoreMap.values().stream()
            .sorted((a, b) -> Double.compare(b.getBalance(), a.getBalance()))
            .limit(10)
            .toList();
    }

    @PostMapping("/reset")
    public String resetScores() {
        scoreMap.clear();
        return "Leaderboard reset!";
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
