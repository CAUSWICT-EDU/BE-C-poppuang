package dongne.poppuang.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String username; // username이 아이디
    private String password; // 비밀번호
}