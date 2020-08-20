package chamly.learn.spring.microservice.exception;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {

    private Date time;
    private String message;
    private String details;


}
