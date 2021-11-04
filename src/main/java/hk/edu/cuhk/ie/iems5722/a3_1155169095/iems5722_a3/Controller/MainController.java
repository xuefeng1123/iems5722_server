package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Controller;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.MessageRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api/a3")
public class MainController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET, value = "/get_messages")
    @ResponseBody
    public String getMessages(@RequestParam int chatroom_id
            , @RequestParam int page){
        String a  = messageService.getMessage(chatroom_id, page);
        return a;
    }
}
