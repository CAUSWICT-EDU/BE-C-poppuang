package dongne.poppuang.repository;

import dongne.poppuang.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByUid(String uid); //이게 사용자 이름으로 User 찾는거
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<String> findPwByUid(String uid);

}
