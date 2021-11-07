package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Integer> {
    public Chatroom getChatroomById(int chatroom_id);
    public List<Chatroom> findAll();
}