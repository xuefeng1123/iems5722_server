package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.MQ;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.FBNotification;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.RabbitMQUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(FBNotification notification) {
        System.out.println("Sender1 : " + notification.toString());
        try{
            this.rabbitTemplate.convertAndSend("hello", RabbitMQUtil.getBytesFromObject(notification));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
