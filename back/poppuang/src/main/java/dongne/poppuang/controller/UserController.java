package dongne.poppuang.controller;

import dongne.poppuang.domain.Major;
import dongne.poppuang.domain.RegisterDto;
import dongne.poppuang.repository.JpaMajorRepository;
import dongne.poppuang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController { // 클래스 이름은 예시입니다.

    private final UserService userService;
    private final JpaMajorRepository jpaMajorRepository; // major 리스트를 다시 전달하기 위해 필요

    public UserController(UserService userService, JpaMajorRepository jpaMajorRepository) {
        this.userService = userService;
        this.jpaMajorRepository = jpaMajorRepository;
    }

    // ... 다른 메소드들 ...

    /**
     * 회원가입 폼 제출을 처리하는 메소드
     */
    @PostMapping("/register-submit")
    public String registerSubmit(RegisterDto registerDto, Model model) {
        String uid = registerDto.getUsername(); // DTO에서 uid를 가져옵니다.

        // 1. ⭐ 아이디 중복 확인
        if (userService.isUidDuplicate(uid)) {
            // 2. ⭐ 중복된 경우, 에러 메시지를 모델에 담아 다시 회원가입 페이지로 보냅니다.
            model.addAttribute("duplicateError", "이미 존재하는 아이디 입니다");

            // (중요!) 회원가IP 페이지에 전공(major) 목록이 다시 필요하므로 모델에 담아줍니다.
            List<Major> majors = jpaMajorRepository.findAll();
            model.addAttribute("majors", majors);

            return "signUp"; // 회원가입 뷰 이름 (signUp.html)
        }

        // 3. 중복이 아닌 경우, 회원 생성 로직을 실행합니다.
        userService.createUser(registerDto);

        // 4. 회원가입 성공 후 로그인 페이지로 리다이렉트합니다.
        return "redirect:/login";
    }
}
