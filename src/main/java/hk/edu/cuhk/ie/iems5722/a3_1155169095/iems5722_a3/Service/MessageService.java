package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.*;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.MQ.MsgSender;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.ChatroomRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.MessageRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.TokenRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.UserRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;


@Service
public class MessageService {

    public static int PAGE_SIZE = 5;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatroomRepository chatroomRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    MsgSender msgSender;

    public String getMessage(int chatroomId, int page){
        List<Message> messages = messageRepository.findAllByChatroomIdOrderByTimeDesc(chatroomId);
        int totalSize = messages.size();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(messages));
        List<JSONObject> messagesResults =  jsonArray.stream().filter(json -> json instanceof JSONObject)
                .skip((long) PAGE_SIZE * (page - 1))
                .limit(PAGE_SIZE)
                .map(json ->{
                    User user = userRepository.getUserById(Integer.parseInt(((JSONObject)json).get("userId").toString()));
                    Timestamp timestamp = new Timestamp((Long) ((JSONObject)json).get("time"));
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("message", ((JSONObject)json).get("content"));
                    jsonObject.put("name", user.getName());
                    jsonObject.put("message_time", sdf.format(timestamp));
                    jsonObject.put("user_id", user.getId());
                    return jsonObject;

        }).collect(Collectors.toList());
        JSONObject jsonData = new JSONObject();
        jsonData.put("messages", messagesResults);
        jsonData.put("total_pages", totalSize /PAGE_SIZE + 1);
        jsonData.put("current_page", page);

        JSONObject jsonResult = new JSONObject();
        jsonResult.put("data", jsonData);
        jsonResult.put("status", OK);
        return JSON.toJSONString(jsonResult);
    }

    public String addMessage(int chatroom_id, int user_id, String name, String message) {

        List<User> users = userRepository.findAllById(user_id);
        if (users.size() == 0) {
            User user = new User();
            user.setId(user_id);
            user.setName(name);
            userRepository.saveAndFlush(user);
        }

        Message m = new Message();
        m.setChatroomId(chatroom_id);
        m.setUserId(user_id);
        m.setTime(new Timestamp(System.currentTimeMillis()));
        m.setContent(message);
        messageRepository.save(m);

        List<Chatroom> chatrooms = chatroomRepository.findAllById(chatroom_id);
        if (chatrooms.size() == 1){
            List<Integer> notifyUsers = chatrooms.get(0).getMembers().stream().map(User::getId).collect(Collectors.toList());
            msgSender.send(FBNotification.builder()
                    .message(m)
                    .tokens(tokenRepository.findTokensByUserIdIn(notifyUsers).stream().map(Token::getTokenStr).toList())
                    .chatroomName(chatroomRepository.findAllById(m.getChatroomId()).get(0).getName())
                    .senderName(userRepository.findAllById(m.getUserId()).get(0).getName())
                    .build());
        }
        return Response.ReturnOK();
    }
    static public String convertFormat(String originTime){
        return originTime.replace('T', ' ').substring(0,originTime.length() - 1);
    }

}
