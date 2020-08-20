package chamly.learn.spring.microservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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

@RestController()
@RequestMapping("/demousers")
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping
    public List<User> findAll() {
        return userDaoService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> findById(@PathVariable Integer id) {
        User user = userDaoService.findOne(id);
        if (null == user) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> en = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());

        en.add(linkTo.withRel("all-users"));

        //HATEOAS

        return en;
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        User user = userDaoService.deleteUser(id);
        if (null == user) {
            throw new UserNotFoundException("id-" + id);
        }

    }
}
