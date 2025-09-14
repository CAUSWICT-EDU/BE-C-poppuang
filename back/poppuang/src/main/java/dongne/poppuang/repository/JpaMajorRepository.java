package dongne.poppuang.repository;

import dongne.poppuang.domain.Major;
import dongne.poppuang.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaMajorRepository implements MajorRepository {
    private final EntityManager em;
    public JpaMajorRepository(EntityManager em) { this.em = em; }

    @Override
    public Long addClick(Long id) {
        Optional<Major> target = findById(id + 1);

        target.get().setClicks(target.get().getClicks() + 1);

        return target.get().getClicks();
    }

    @Override
    public Optional<Major> findById(Long id) {
        Major major = em.find(Major.class, id);
        return Optional.ofNullable(major);
    }

    @Override
    public Major findByName(String name) {
        List<Major> result = em.createQuery("SELECT m FROM Major m WHERE m.name = :name", Major.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny().get();
    }

    @Override
    public List<Major> findAll() {
        return em.createQuery("SELECT m FROM Major m", Major.class).getResultList();
    }


    @Override
    public Major addMajor(Major major) {
        em.persist(major);
        return major;
    }
}


