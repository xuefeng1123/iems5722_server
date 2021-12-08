package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.MQ;

import com.rabbitmq.client.Channel;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.FBNotification;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.FireBaseUtil;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.RabbitMQUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MsgReceiver2 {

    @RabbitListener(queues = "hello")
    public void process(Message message, Channel channel) throws IOException {
        try{
            FBNotification notification = (FBNotification) RabbitMQUtil.getObjectFromBytes(message.getBody());
            System.out.println("Receiver1  : " + new String(message.getBody()));
//            String token = "dw2XSal8Rrq0bQwYL7Pq8y:APA91bFsDvfUzHZwrH5oVyKtF5JSdpCUCSWDOX3yPNbC1K52OJ4w3NijcXmZtu25TTEstBOaIBE23YTfDht3_NErbUVHrg1fdL1S-bsC9UySHjwecOeTXLkv3lfMV-lRNd-Y80TXxRhn";
//            tokens.add(token);
            FireBaseUtil.registrationTopic(notification.getTokens(), notification.getMessage().getChatroomId().toString());
            FireBaseUtil.sendTopicMes(notification.message.getChatroomId().toString(),
                    notification.chatroomName,
                    notification.getSenderName() + " : " + notification.getMessage().getContent());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}