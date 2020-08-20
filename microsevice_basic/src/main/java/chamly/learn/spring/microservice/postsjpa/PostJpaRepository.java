package chamly.learn.spring.microservice.postsjpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chamly.learn.spring.microservice.userjpa.UserJpa;

@Repository
public interface PostJpaRepository extends JpaRepository<PostJpa, Integer> {
    public List<PostJpa> findByUserAndId(UserJpa userJpa, Integer id);
}
