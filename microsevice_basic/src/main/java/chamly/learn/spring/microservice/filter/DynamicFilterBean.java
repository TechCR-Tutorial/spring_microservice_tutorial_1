package chamly.learn.spring.microservice.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFilter("FilterBeanFilter")
public class DynamicFilterBean {

    private String name;
    private String description;
    private String unwantedData;
}
