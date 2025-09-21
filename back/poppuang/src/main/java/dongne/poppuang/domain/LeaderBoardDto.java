package dongne.poppuang.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class LeaderBoardDto {
    private String nickname;
    private Long clicks;
    private String majors;
}
