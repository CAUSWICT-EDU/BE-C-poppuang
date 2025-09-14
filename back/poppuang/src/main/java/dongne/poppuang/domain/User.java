package dongne.poppuang.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uid;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private Timestamp created_at;

    //유저의 클릭 수를 나타내는 변수
    @Column(nullable = false)
    private Long clicks = 0L;

    public Long clickIncrement() {
        return ++(this.clicks);
    }
}
