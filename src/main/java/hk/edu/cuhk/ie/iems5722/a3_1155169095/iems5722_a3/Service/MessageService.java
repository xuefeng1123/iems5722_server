package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.Message;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.ChatroomRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public String getMessage(int chatroomId, int page){
        List<Message> messages = messageRepository.findAllByChatroomIdOrderByTime(chatroomId);
        return JSON.toJSONString(messages);
    }
}
