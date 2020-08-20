package chamly.learn.spring.microservice.userjpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import chamly.learn.spring.microservice.postsjpa.PostJpa;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All about the user")
@Entity
@Table(name = "user")
public class UserJpa {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Name should have minimum 2 charctors")
    @ApiModelProperty(notes = "Name should at least has 2 charctors.")
    private String name;
    @Past
    @ApiModelProperty(notes = "Birthday should be in past")
    private Date birthDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PostJpa> posts;

}
