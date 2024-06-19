package bg.temps.jobportal.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "post_job")
public class PostJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @OneToMany(mappedBy = "postJob",cascade = CascadeType.ALL)
    private List<ApplyJob> applyJob;
    @OneToMany(mappedBy = "postJob", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

}
