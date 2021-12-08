package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.FireBaseUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Iems5722A3Application {

    public static void main(String[] args) {
        SpringApplication.run(Iems5722A3Application.class, args);
//        try{
//            String token = "dw2XSal8Rrq0bQwYL7Pq8y:APA91bFsDvfUzHZwrH5oVyKtF5JSdpCUCSWDOX3yPNbC1K52OJ4w3NijcXmZtu25TTEstBOaIBE23YTfDht3_NErbUVHrg1fdL1S-bsC9UySHjwecOeTXLkv3lfMV-lRNd-Y80TXxRhn";
////            FireBaseUtil.pushSingle(token,
////                    "Server",
////                    "Server Message");
//            List<String> tokens = new ArrayList<>();
//            tokens.add(token);
//            FireBaseUtil.registrationTopic(tokens, "Weather");
//            FireBaseUtil.sendTopicMes("Weather", "Weather Multiple Msg", "Weather Multiple Msg");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

}
