package dongne.poppuang.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String username; // username이 아이디임, User 엔티티에서는 uid로 명시
    private String department; // User 엔티티에서는 major로 명시
    private String password; // User 엔티티에서는 pw로 명시
    private String passwordConfirm; // User 엔티티에서는 안씀
}
