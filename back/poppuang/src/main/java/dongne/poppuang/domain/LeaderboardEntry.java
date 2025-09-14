package dongne.poppuang.domain;

import lombok.Getter;

@Getter
public class LeaderboardEntry {
    private String major;
    private Long clicks;

    public LeaderboardEntry(String major, Long clicks) {
        this.major = major;
        this.clicks = clicks;
    }

    public void addClick() { this.clicks++; }
}
