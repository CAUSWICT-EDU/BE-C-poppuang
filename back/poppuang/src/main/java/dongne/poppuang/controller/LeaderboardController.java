package dongne.poppuang.controller;

import dongne.poppuang.domain.LeaderboardEntry;
import dongne.poppuang.service.LeaderboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaderboardController {
    private final LeaderboardService leaderboardService;
    public LeaderboardController(LeaderboardService leaderboardService) { this.leaderboardService = leaderboardService; }
    
    // 푸앙이 클릭하면 호출되는데 정렬 함수 자체가 시간이 너무 오래걸림, 기존 계획대로 n분에 한번씩 갱신해주는 방식 필요
    @GetMapping("/leaderboard")
    public List<LeaderboardEntry> getLeaderboard() {
        return leaderboardService.getData();
    }
}
