package chamly.learn.spring.microservice.postsjpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PostJpaDaoService {

    private static Map<Integer, List<PostJpa>> postsMap = new HashMap<>();
    private static Integer postId = 1;

    static {
        PostJpa post = PostJpa.builder().post("User 1 First Post").build();
        post.setId(postId++);
        postsMap.put(1, Collections.singletonList(post));
    }

    public PostJpa createPost(Integer userId, String post) {
        PostJpa resultPost = PostJpa.builder().post(post).build();
        resultPost.setId(postId++);
        postsMap.compute(userId, (integer, posts) -> {
            List<PostJpa> newPosts = new ArrayList<>(posts);
            newPosts.add(resultPost);
            return newPosts;
        });
        return resultPost;
    }

    public List<PostJpa> getAllPosts(Integer userId) {
        List<PostJpa> posts = postsMap.get(userId);
        return posts == null ? Collections.emptyList() : posts;
    }

    public PostJpa getPost(Integer userId, Integer postId) {
        List<PostJpa> posts = getAllPosts(userId);
        if (null != posts) {
            return posts.stream().filter(post -> post.getId().equals(postId)).findFirst().orElse(null);
        }
        return null;
    }


}
