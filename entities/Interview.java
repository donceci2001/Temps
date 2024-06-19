package bg.temps.jobportal.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "applyJob_id")
    private ApplyJob applyJob;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interviewstatus_id")
    private InterviewStatus interviewStatus;

}
