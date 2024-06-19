package bg.temps.jobportal.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "applyjob")
public class ApplyJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private java.time.LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "postJob_id")
    private PostJob postJob;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "applyJob",cascade = CascadeType.ALL)
    private List<Interview> interview;

}
