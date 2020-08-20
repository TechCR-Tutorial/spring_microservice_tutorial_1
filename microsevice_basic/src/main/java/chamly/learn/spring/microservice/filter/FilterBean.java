package chamly.learn.spring.microservice.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterBean {

    private String name;
    private String description;
    @JsonIgnore
    private String unwantedData;
}
