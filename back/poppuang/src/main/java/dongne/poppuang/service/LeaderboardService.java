package dongne.poppuang.service;

import dongne.poppuang.domain.LeaderboardEntry;
import dongne.poppuang.domain.Major;
import dongne.poppuang.repository.MajorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LeaderboardService {
    MajorRepository majorRepository;
    public LeaderboardService(MajorRepository majorRepository) { this.majorRepository = majorRepository; }

    @Transactional
    public List<LeaderboardEntry> getData() {
        return createMajorList();
    }

    private List<LeaderboardEntry> createMajorList() {
        List<Major> data_list = majorRepository.findAll();
        List<LeaderboardEntry> list = new ArrayList<LeaderboardEntry>();
        for (Major major : data_list) {
            LeaderboardEntry entry = new LeaderboardEntry(major.getName(), major.getClicks());
            list.add(entry);
        }

        // 정렬 함수, 대충 인터넷에서 찾아봄, 실행 시간 너무 김
        // + JS로 정렬 가능, 따라서 안써도 됨
        // Collections.sort(list, (o1,o2) -> Math.toIntExact(o2.getClicks() - o1.getClicks()));

        return list;
    }
}
