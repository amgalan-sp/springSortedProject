package mvc.springBoot.repository;

import mvc.springBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
//    @Query(value = "CREATE TABLE sortedUsers SELECT * FROM users ;", nativeQuery = true)
//    void createTable();
}
