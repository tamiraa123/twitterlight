package mpp.project.dao;

import mpp.project.model.Post;
import mpp.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
