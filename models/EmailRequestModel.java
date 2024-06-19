package bg.temps.jobportal.models;

import lombok.Data;

@Data
public class EmailRequestModel {
    private String subject;
    private String to;
    private String message;

}
