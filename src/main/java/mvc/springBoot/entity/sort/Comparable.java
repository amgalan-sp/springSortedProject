package mvc.springBoot.entity.sort;

import mvc.springBoot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Comparable extends PagingAndSortingRepository<User, Integer>  {
    boolean toCompare();
    Page<User> findAll(Pageable pageable);
    Page<User> findByFirstName(String firstName, Pageable pageable);
    Page<User> findByLastName(String lastName, Pageable pageable);
    Slice<User> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);
}
