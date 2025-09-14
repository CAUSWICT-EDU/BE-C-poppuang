package dongne.poppuang.service;

import dongne.poppuang.domain.EnumMajors;
import dongne.poppuang.domain.Major;
import dongne.poppuang.repository.MajorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MajorService {
    MajorRepository majorRepository;
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
        fillOutMajors();  // 시작 시 학과 테이블 채움 (clicks = 0)
    }

    @Transactional
    protected void fillOutMajors() {
        for (EnumMajors enumMajor : EnumMajors.values()) {
            Major major = generateMajor(enumMajor.name());
            majorRepository.addMajor(major);
        }
    }

    private Major generateMajor(String name) {
        Major major = new Major();
        major.setName(name);
        major.setClicks(0L);
        return major;
    }
}
