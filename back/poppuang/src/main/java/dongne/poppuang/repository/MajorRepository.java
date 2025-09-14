package dongne.poppuang.repository;

import dongne.poppuang.domain.Major;

import java.util.List;
import java.util.Optional;

public interface MajorRepository {
    Long addClick(Long id);
    Optional<Major> findById(Long id);
    Major findByName(String name);
    List<Major> findAll();
    Major addMajor(Major major);
}
