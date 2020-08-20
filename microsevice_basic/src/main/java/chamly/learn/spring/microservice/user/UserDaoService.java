package chamly.learn.spring.microservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import chamly.learn.spring.microservice.aspect.Auditable;

@Component
public class UserDaoService {

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "William", new Date()));
        userList.add(new User(2, "John", new Date()));
    }

    public List<User> findAll() {
        return userList;
    }

    public User save(User user) {
        if (null == user.getId()) {
            user.setId(userList.size() + 1);
        }
        userList.add(user);
        return user;
    }

    @Auditable
    public User findOne(@Auditable int id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User deleteUser(int id) {
        User findOne = findOne(id);
        if (findOne != null) {
            userList.remove(findOne);
        }
        return findOne;
    }
}
