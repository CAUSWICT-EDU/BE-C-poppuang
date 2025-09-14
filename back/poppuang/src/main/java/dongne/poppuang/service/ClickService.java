package dongne.poppuang.service;

import dongne.poppuang.domain.EnumMajors;
import dongne.poppuang.domain.Major;
import dongne.poppuang.domain.User;
import dongne.poppuang.repository.MajorRepository;
import dongne.poppuang.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static dongne.poppuang.domain.EnumMajors.*;

@Service
public class ClickService {
    UserRepository userRepository;

    public ClickService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Transactional
    public User addClick(String uid) {
        // 유저가 존재하지 않는 경우 예외 처리 필요
        User user = userRepository.findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다"));
        user.clickIncrement();
        user.getMajor().clickIncrement();

        return user;
    }
    
}

