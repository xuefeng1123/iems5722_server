package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.MQ;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MsgReceiver2 {

    @RabbitListener(queues = "hello")
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("Receiver2  : " + new String(message.getBody()));
    }

}