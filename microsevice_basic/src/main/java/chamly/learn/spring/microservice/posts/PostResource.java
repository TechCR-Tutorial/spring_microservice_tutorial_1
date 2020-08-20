package chamly.learn.spring.microservice.posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{id}/posts")
public class PostResource {

    @Autowired
    private PostDaoService postDaoService;

    @GetMapping
    public List<Post> getAllPosts(@PathVariable(value = "id") Integer userId) {
        return postDaoService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable(value = "id") Integer userId, @PathVariable Integer postId) {
        return postDaoService.getPost(userId, postId);
    }

    @PostMapping()
    public Post createPost(@PathVariable(value = "id") Integer userId, @RequestBody String post) {
        return postDaoService.createPost(userId, post);
    }
}
