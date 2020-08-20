package chamly.learn.spring.microservice.posts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class PostDaoService {

    private static Map<Integer, List<Post>> postsMap = new HashMap<>();
    private static Integer postId = 1;

    static {
        Post post = Post.builder().userId(1).post("User 1 First Post").build();
        post.setId(postId++);
        postsMap.put(post.getUserId(), Collections.singletonList(post));
    }

    public Post createPost(Integer userId, String post) {
        Post resultPost = Post.builder().userId(userId).post(post).build();
        resultPost.setId(postId++);
        postsMap.compute(userId, (integer, posts) -> {
            List<Post> newPosts = new ArrayList<>(posts);
            newPosts.add(resultPost);
            return newPosts;
        });
        return resultPost;
    }

    public List<Post> getAllPosts(Integer userId) {
        List<Post> posts = postsMap.get(userId);
        return posts == null ? Collections.emptyList() : posts;
    }

    public Post getPost(Integer userId, Integer postId) {
        List<Post> posts = getAllPosts(userId);
        if (null != posts) {
            return posts.stream().filter(post -> post.getId().equals(postId)).findFirst().orElse(null);
        }
        return null;
    }


}
