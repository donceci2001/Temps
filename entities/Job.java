package bg.temps.jobportal.entities;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "job_detail")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description",length = 2000)
    private String description;
    @Column(name = "date")
    private java.time.LocalDateTime date;
    @Column(name = "salary")
    private long salary;
    @Column(name = "address")
    private String address;

    //Creating relation between job and post job
    @OneToMany(mappedBy = "job",cascade = CascadeType.ALL)
    private List<PostJob> postJob;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

}
