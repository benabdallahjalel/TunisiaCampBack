package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface CommentRepository extends CrudRepository<Comment, Long> {





}
