package bg.temps.jobportal.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "company_details")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String Description;
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<User> user;
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<PostJob> postJob;
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Job> job;


}
