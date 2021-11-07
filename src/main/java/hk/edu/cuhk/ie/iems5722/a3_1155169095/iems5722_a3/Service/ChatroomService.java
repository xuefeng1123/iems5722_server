package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.Chatroom;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@Service
public class ChatroomService {

    @Autowired
    ChatroomRepository chatroomRepository;

    public String getChatrooms(){
        List<Chatroom> chatrooms =  chatroomRepository.findAll();
        List<JSONObject> jsonChatrooms = chatrooms.stream()
                .map(chatroom -> {
                    JSONObject jsonChatroom = new JSONObject();
                    jsonChatroom.put("id", ((Chatroom) chatroom).getId());
                    jsonChatroom.put("name", ((Chatroom) chatroom).getName());
                    return jsonChatroom;
                }).collect(Collectors.toList());
        JSONObject resultJson = new JSONObject();
        resultJson.put("data", jsonChatrooms);
        resultJson.put("status", OK);

        return JSON.toJSONString(resultJson);
    }
}
