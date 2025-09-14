package dongne.poppuang.service;
import dongne.poppuang.domain.EnumMajors;
import dongne.poppuang.domain.Major;
import dongne.poppuang.domain.RegisterDto;
import dongne.poppuang.domain.User;
import dongne.poppuang.repository.MajorRepository;
import dongne.poppuang.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MajorRepository majorRepository;
    public UserService(UserRepository userRepository, MajorRepository majorRepository) {
        this.userRepository = userRepository;
        this.majorRepository = majorRepository;
    }

    private final Map<String,String> users = new ConcurrentHashMap<>();
    
    @Transactional
    public User createUser(RegisterDto registerDto) {
        String uid = registerDto.getUsername();
        // 아이디 중복 확인 필요

        EnumMajors enum_major = EnumMajors.valueOf(registerDto.getDepartment());
        Optional<Major> major = majorRepository.findById((long) (enum_major.ordinal() + 1));
        // 학과명이 없을 때 로직 필요 (major == null 일 때)

        String pw = registerDto.getPassword();
        // 비번 검증?

        User user = new User();
        user.setUid(uid);
        user.setMajor(major.get());
        user.setPw(pw); // 해시 아직 안함
        user.setClicks(0L);
        user.setCreated_at(new Timestamp(System.currentTimeMillis()));

        return userRepository.save(user);
    }

    // 이 메서드 추가
    @Transactional
    public Long incrementClicks(String uid) {
        User user = userRepository.findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + uid));
        return user.clickIncrement(); // JPA가 트랜잭션 끝날 때 자동 저장
    }

    public User login(String uid, String password) {
        // 2. 이전에 만든 findByUid를 사용해 Optional<User>를 가져옵니다.
        Optional<User> optionalUser = userRepository.findByUid(uid);

        // 3. Optional에 User 객체가 있는지 확인합니다. (사용자가 존재하는지 체크)
        if (optionalUser.isPresent()) {
            // 4. 존재한다면 User 객체를 꺼냅니다.
            User user = optionalUser.get();

            // 5. 꺼낸 User 객체의 비밀번호와 입력된 비밀번호를 비교합니다.
            //    (주의: 실제 서비스에서는 암호화된 비밀번호를 비교해야 합니다. 지금은 간단하게 처리)
            if (user.getPw().equals(password)) {
                // 6. 비밀번호가 일치하면 'User 객체'를 반환합니다.
                return user;
            }
        }

        // 7. 사용자가 존재하지 않거나 비밀번호가 틀리면 null을 반환합니다.
        return null;
    }

    public boolean isUidDuplicate(String uid) {
        // findByUid를 호출했을 때 결과가 존재하면(isPresent() == true) 중복된 것입니다.
        return userRepository.findByUid(uid).isPresent();
    }


    public String getMajor(String username) {
        return userRepository.findByUid(username).get().getMajor().getName();
    }


    // 테스트/디버깅용: 전체 사용자 보기 (운영금지)
    public Map<String,String> getAllUsers() {
        return users;
    }
}
