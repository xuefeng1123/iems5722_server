package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.Message;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.User;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.MessageRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String getMessage(int chatroomId, int page){
        List<Message> messages = messageRepository.findAllByChatroomIdOrderByTime(chatroomId);
        int totalSize = messages.size();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(messages));
        List<JSONObject> messagesResults =  jsonArray.stream().filter(json -> json instanceof JSONObject)
                .skip((long) PAGE_SIZE * (page - 1))
                .limit(PAGE_SIZE)
                .map(json ->{
                    User user = userRepository.getUserById(Integer.parseInt(((JSONObject)json).get("userId").toString()));

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("message", ((JSONObject)json).get("content"));
                    jsonObject.put("name", user.getName());
                    jsonObject.put("message_time", convertFormat(((JSONObject)json).get("time").toString()));
                    jsonObject.put("user_id", ((JSONObject)json).get("id"));
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

    static public String convertFormat(String originTime){
        return originTime.replace('T', ' ').substring(0,originTime.length() - 1);
    }

}
