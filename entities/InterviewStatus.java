package bg.temps.jobportal.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "interview_status")
public class InterviewStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "status_name")
    private String StatusName;
    @Column(name = "Feedback")
    private String Feedback;
    @OneToMany(mappedBy = "interviewStatus", cascade = CascadeType.ALL)
    private List<Interview> interview;
}
