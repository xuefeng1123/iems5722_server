package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Controller;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service.ChatroomService;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api/a3")
public class MainController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatroomService chatroomService;
    @RequestMapping(method = RequestMethod.GET, value = "/get_messages")
    @ResponseBody
    public String getMessages(@RequestParam int chatroom_id
            , @RequestParam int page){
        return messageService.getMessage(chatroom_id, page);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/get_chatrooms")
    @ResponseBody
    public String getChatrooms(){
        return chatroomService.getChatrooms();
    }

    @RequestMapping(value="/send_message",method=RequestMethod.POST)
    public String sendMessage(@RequestParam("chatroom_id") int chatroom_id,
                              @RequestParam("user_id") int user_id,
                              @RequestParam("name") String name,
                              @RequestParam("message") String message){
        return messageService.addMessage(chatroom_id, user_id, message);
    }
}
