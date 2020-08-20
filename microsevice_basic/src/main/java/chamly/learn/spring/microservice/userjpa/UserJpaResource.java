package chamly.learn.spring.microservice.userjpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import chamly.learn.spring.microservice.user.UserNotFoundException;

@RestController()
@RequestMapping("/jpa/users")
public class UserJpaResource {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping
    public List<UserJpa> findAll() {
        return userJpaRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<UserJpa> findById(@PathVariable Integer id) {
        Optional<UserJpa> user = userJpaRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<UserJpa> en = EntityModel.of(user.get());

        WebMvcLinkBuilder linkTo =
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());

        en.add(linkTo.withRel("all-users"));

        //HATEOAS

        return en;
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody UserJpa user) {
        UserJpa savedUser = userJpaRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        UserJpa user = findById(id).getContent();
        userJpaRepository.delete(user);
        if (null == user) {
            throw new UserNotFoundException("id-" + id);
        }

    }
}
