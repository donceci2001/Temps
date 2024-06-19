package bg.temps.jobportal.models;

import lombok.Data;

@Data
public class ReviewModel {
    private Long id;
    private int rating;
    private String feedback;
}
