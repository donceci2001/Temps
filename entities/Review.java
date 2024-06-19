package bg.temps.jobportal.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "rating")
    private int rating;
    @Column(name = "feedback")
    private String feedback;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_job_id")
    private PostJob postJob;
}