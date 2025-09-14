package dongne.poppuang.controller;

import dongne.poppuang.domain.LoginedUserDto;
import dongne.poppuang.domain.User;
import dongne.poppuang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public String loginSubmit(String uid, String password,
                              HttpSession session, Model model) { // HttpServletResponse는 이제 필요 없으므로 삭제

        User loginUser = userService.login(uid, password);
        if (loginUser != null) {
            // 3. ⭐ 핵심: 로그인에 성공한 User 객체를 LoginedUserDto로 변환합니다.
            LoginedUserDto loginedUser = new LoginedUserDto(loginUser);

            // 4. ⭐ 핵심: 변환된 DTO 객체를 세션에 저장합니다.
            //    이제 여러 정보를 각각 저장할 필요 없이, 이 객체 하나만 저장하면 됩니다.
            session.setAttribute("loginedUser", loginedUser); // "loginedUser"라는 이름으로 저장

            return "redirect:/"; // 메인 페이지로 리다이렉트

        } else {
            // 로그인 실패 시
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) { // HttpServletResponse, Model 파라미터 삭제
        session.invalidate(); // 세션만 무효화하면 됩니다.

        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}
