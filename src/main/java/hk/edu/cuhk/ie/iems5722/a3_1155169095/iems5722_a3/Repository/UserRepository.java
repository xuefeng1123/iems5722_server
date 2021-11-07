package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User getUserById(int id);
}