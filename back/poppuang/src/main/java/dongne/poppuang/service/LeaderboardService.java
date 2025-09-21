package dongne.poppuang.service;

import dongne.poppuang.domain.LeaderBoardDto;
import dongne.poppuang.domain.LeaderboardEntry;
import dongne.poppuang.domain.Major;
import dongne.poppuang.domain.User;
import dongne.poppuang.repository.MajorRepository;
import dongne.poppuang.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {
    MajorRepository majorRepository;
    UserRepository userRepository;
    public LeaderboardService(MajorRepository majorRepository, UserRepository userRepository) {
        this.majorRepository = majorRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<LeaderboardEntry> getData() {
        return createMajorList();
    }

    @Transactional
    public List<LeaderBoardDto> getLeaderBoard() {
        return createNicknameList();
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

    private List<LeaderBoardDto> createNicknameList() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> LeaderBoardDto.builder()
                        .nickname(user.getNickname())
                        .clicks(user.getClicks())
                        .majors(String.valueOf(user.getMajor()))
                        .build())
                .collect(Collectors.toList());
    }
}
