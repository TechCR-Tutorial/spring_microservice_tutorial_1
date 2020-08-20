package chamly.learn.spring.microservice.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All about the user")
public class User {
    private Integer id;
    @Size(min = 2, message = "Name should have minimum 2 charctors")
    @ApiModelProperty(notes = "Name should at least has 2 charctors.")
    private String name;
    @Past
    @ApiModelProperty(notes = "Birthday should be in past")
    private Date birthDate;
}
