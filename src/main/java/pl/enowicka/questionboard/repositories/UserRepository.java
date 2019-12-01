package pl.enowicka.questionboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.enowicka.questionboard.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select count(u) > 0 from User u where u.email = ?1")
    boolean checkIfEmailExists(String email);

    @Query("select count(u) > 0 from User u where u.name = ?1")
    boolean checkIfNameExists(String name);
}
