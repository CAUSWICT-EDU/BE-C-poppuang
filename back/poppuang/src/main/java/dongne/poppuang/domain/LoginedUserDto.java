package dongne.poppuang.domain;

import dongne.poppuang.domain.Major;
import dongne.poppuang.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 세션에 사용자 정보를 저장하기 위한 DTO (Lombok 적용).
 * @Getter: 모든 필드의 Getter 메소드를 자동 생성.
 * @NoArgsConstructor: 파라미터가 없는 기본 생성자를 자동 생성.
 */
@Getter
@NoArgsConstructor
public class LoginedUserDto implements Serializable {

    private Long id;
    private String uid;
    private Major major;

    /**
     * User 엔티티를 DTO로 변환하는 생성자.
     * 이 생성자는 특정 로직을 포함하므로 Lombok으로 대체하지 않고 직접 작성합니다.
     *
     * @param user 로그인에 성공한 User 엔티티 객체
     */
    public LoginedUserDto(User user) {
        this.id = user.getId();
        this.uid = user.getUid();
        this.major = user.getMajor();
    }
}