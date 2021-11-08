package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message,Integer> {
    public List<Message> findAllByChatroomIdOrderByTime(int chatroomId);
    public List<Message> findAllByChatroomIdOrderByTime(Pageable pageable);

}
