package bg.temps.jobportal.models;

import lombok.Data;

@Data
public class ApplyJobModel {
    private String email;
    private long contact;
    private String city;
    private java.time.LocalDateTime date;


}
