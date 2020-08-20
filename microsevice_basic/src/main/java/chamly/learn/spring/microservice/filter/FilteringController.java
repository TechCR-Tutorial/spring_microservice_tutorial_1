package chamly.learn.spring.microservice.filter;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/filter")
public class FilteringController {

    @GetMapping
    public FilterBean staticFilter() {
        return new FilterBean("Chamly", "Check Filtering", "Unwanted Data.");
    }

    @GetMapping("/dynamic")
    public MappingJacksonValue dynamicFilter() {
        DynamicFilterBean filterBean = new DynamicFilterBean("Chamly", "Check Filtering", "Unwanted Data.");
        MappingJacksonValue mapping = new MappingJacksonValue(filterBean);
        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "description");
        FilterProvider filters = new SimpleFilterProvider()
            .addFilter("FilterBeanFilter", filter);
        mapping.setFilters(filters);
        return mapping;
    }
}
