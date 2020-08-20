package chamly.learn.spring.microservice.postsjpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chamly.learn.spring.microservice.user.UserNotFoundException;
import chamly.learn.spring.microservice.userjpa.UserJpa;
import chamly.learn.spring.microservice.userjpa.UserJpaRepository;

@RestController
@RequestMapping("/jpa/users/{id}/posts")
public class PostJpaResource {

    @Autowired
    private PostJpaDaoService postDaoService;
    @Autowired
    private PostJpaRepository postRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping
    public List<PostJpa> getAllPosts(@PathVariable(value = "id") Integer userId) {
        Optional<UserJpa> userJpa = userJpaRepository.findById(userId);
        if (!userJpa.isPresent()) {
            throw new UserNotFoundException("id-" + userId);
        }
        return userJpa.get().getPosts();
    }

    @GetMapping("/{postId}")
    public PostJpa getPost(@PathVariable(value = "id") Integer userId, @PathVariable Integer postId) {
        Optional<PostJpa> postJpa = postRepository.findById(postId);
        if (!postJpa.isPresent()) {
            throw new UserNotFoundException("id-" + userId);
        }
        return postJpa.get();
    }

    @PostMapping()
    public PostJpa createPost(@PathVariable(value = "id") Integer userId, @RequestBody String post) {
        Optional<UserJpa> userJpa = userJpaRepository.findById(userId);
        if (!userJpa.isPresent()) {
            throw new UserNotFoundException("id-" + userId);
        }
        PostJpa postJpa = PostJpa.builder().user(userJpa.get()).post(post).build();


        return postRepository.save(postJpa);
    }
}
