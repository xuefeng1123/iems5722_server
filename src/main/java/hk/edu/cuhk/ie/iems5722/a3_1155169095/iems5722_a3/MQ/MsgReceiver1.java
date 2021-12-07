package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.MQ;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

@Component
public class MsgReceiver1  {

    @RabbitListener(queues = "hello")
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("Receiver1  : " + new String(message.getBody()));
    }

}