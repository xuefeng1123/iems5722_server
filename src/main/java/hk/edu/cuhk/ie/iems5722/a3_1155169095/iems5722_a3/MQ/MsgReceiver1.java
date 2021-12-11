package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.MQ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.FBNotification;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.FireBaseUtil;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.RabbitMQUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

@Component
public class MsgReceiver1  {

    @RabbitListener(queues = "hello")
    public void process(Message message, Channel channel) throws IOException {
        try{
            FBNotification notification = (FBNotification) RabbitMQUtil.getObjectFromBytes(message.getBody());
            System.out.println("Receiver1  : " + new String(message.getBody()));
            FireBaseUtil.registrationTopic(notification.getTokens(), notification.getMessage().getChatroomId().toString());
            FireBaseUtil.sendTopicMes(notification.getMessage().getChatroomId().toString(), notification.chatroomName,
                    notification.getSenderName() + " : " + notification.getMessage().getContent());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}