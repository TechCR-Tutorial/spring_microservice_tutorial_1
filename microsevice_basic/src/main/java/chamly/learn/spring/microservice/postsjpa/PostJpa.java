package chamly.learn.spring.microservice.postsjpa;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import chamly.learn.spring.microservice.userjpa.UserJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "post")
public class PostJpa {
    @Id
    @GeneratedValue
    private Integer id;
    private String post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserJpa user;

}
