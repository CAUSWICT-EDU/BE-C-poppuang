package dongne.poppuang.repository;

import dongne.poppuang.domain.Major;
import dongne.poppuang.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaUserRepository implements UserRepository {
    private final EntityManager em;
    public JpaUserRepository(EntityManager em) { this.em = em; }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUid(String uid) {
        try{
            User result = em.createQuery("SELECT u FROM User u WHERE u.uid = :uid", User.class)
                    .setParameter("uid", uid)
                    .getSingleResult();
            return Optional.of(result);
        }catch (NoResultException e){
            return Optional.empty();
        }
    }


    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }


    public Optional<String> findPwByUid(String uid) {
        try {
            String pw = em.createQuery(
                            "SELECT u.pw FROM User u WHERE u.uid = :uid", String.class)
                    .setParameter("uid", uid)
                    .getSingleResult();
            return Optional.of(pw);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
