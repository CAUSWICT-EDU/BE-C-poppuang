package dongne.poppuang.controller;

import dongne.poppuang.domain.RegisterDto;
import dongne.poppuang.domain.User;
import dongne.poppuang.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    UserService userService;
    public TestController(UserService userService) { this.userService = userService; }

    // "localhost:8080/test"로 들어가면 "major - 간호학과"인 쿠키 생성, 인터넷에서 대충 찾아서 쓴거라 정확한지는 모름
    // 쿠키 확인은 f12 - elements console ... 옆에 더보기 - Application - Storage 탭에 Cookies
    @GetMapping("/test")
    public String createCookie(HttpServletResponse response) {
        RegisterDto testDto = new RegisterDto();
        testDto.setUsername("TestUser");
        testDto.setDepartment("소프트웨어학부");
        testDto.setPassword("123456");
        testDto.setPasswordConfirm("123456");

        User testUser = userService.createUser(testDto);
        String id = testUser.getId().toString();
        String major_id = testUser.getMajor().getName();

        // 쿠키 생성
        Cookie cookie = new Cookie("id", id);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(60);
        cookie.setSecure(true);
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("major", major_id);
        cookie2.setDomain("localhost");
        cookie2.setPath("/");
        cookie2.setMaxAge(60);
        cookie2.setSecure(true);
        response.addCookie(cookie2);

        return "redirect:/";
    }
}
