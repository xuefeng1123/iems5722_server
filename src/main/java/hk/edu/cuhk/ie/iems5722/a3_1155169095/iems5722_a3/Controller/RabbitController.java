package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Controller;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.MQ.MsgSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    MsgSender msgSender;

    /**
     * one to many
     */
    @GetMapping("/12many")
    public void one2Many(){
        for (int i = 0; i < 4; i++) {
            msgSender.send("第[" + (i + 1) + "]个 ---------> ");
        }
    }
}
