package bg.temps.jobportal.models;

import lombok.Data;

@Data
public class CreateReviewModel {
    private long userId;
    private long jobId;
    private int rating;
    private String feedback;
}
