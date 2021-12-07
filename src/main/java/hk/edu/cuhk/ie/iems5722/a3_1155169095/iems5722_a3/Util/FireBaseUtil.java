package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.messaging.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class FireBaseUtil {
// https://blog.csdn.net/qianyesoft001/article/details/102799277
    //获取AndroidConfig.Builder对象
    private static AndroidConfig.Builder androidConfigBuilder = AndroidConfig.builder();
    //获取AndroidNotification.Builder对象
    private static AndroidNotification.Builder androidNotifiBuilder = AndroidNotification.builder();
    public static FirebaseApp firebaseApp;
    private static final String FIREBASE_DB_URL = "https://iems5722-e352f.firebaseio.com";
    private static final String FIREBASE_STORAGE_BUCKET = "iems5722-e352f";
    private static final String FIREBASE_PROJECT_ID = "iems5722-e352f";

    static {
        init();
    }


    public static void main(String[] args) {
        try {
            FirebaseAuth instance = FirebaseAuth.getInstance(firebaseApp);

//            UserRecord user = getUserById("some-uid");
//            System.out.println(user.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        try {
            //设置环境
            InputStream serviceAccount = new ClassPathResource("firebase-adminsdk.json").getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(FIREBASE_DB_URL)
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserRecord getUserById(String uid){
        FirebaseAuth instance = FirebaseAuth.getInstance(firebaseApp);
        UserRecord user = null;
        try {
            user = instance.getUserAsync(uid).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

   /**
     * 单设备推送
     * @param token   注册token
     * @param title   推送题目
     * @param body    推送内容
     * @return
     * @throws IOException
     * @throws FirebaseMessagingException
     */
    public static void pushSingle( String token, String title, String body) throws IOException, FirebaseMessagingException {
        //获取实例
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        //实例为空的情况
        if (firebaseApp == null) {
            return;
        }
        //构建消息内容
        //Notification(String title, String body, String imageUrl)
        Message message = Message.builder().setNotification(Notification.builder().setTitle(title).setBody(body).build())
                .setToken(token)
                .build();
        //发送后，返回messageID
        String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
        System.out.println("单个设备推送成功 : " + response);
    }
    /**
     * 单设备推送
     * @param token   注册token
     * @param title   推送题目
     * @param body    推送内容
     * @param map     额外的参数
     * @return
     * @throws IOException
     * @throws FirebaseMessagingException
     */
    public static void pushSingle(String token, String title, String body, Map<String,String> map) throws IOException, FirebaseMessagingException {
        //获取实例
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        //实例为空的情况
        if (firebaseApp == null) {
            return;
        }
        //构建消息内容
        //Notification(String title, String body, String imageUrl)
        Message message = Message.builder().setNotification(Notification.builder().setBody(body).setTitle(title).build())
                .putAllData(map)
                .setToken(token)
                .build();
        //发送后，返回messageID
        String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
        System.out.println("单个设备推送成功 : " + response);
    }
    /**
     * {@code description: 单设备推送}
     * @param token   注册token
     * @param title   推送题目
     * @param body    推送内容
     * @param extra   额外的参数
     * @return
     * @throws IOException
     * @throws FirebaseMessagingException
     
     */
    public static void pushSingle( String token, String title, String body, String extra) throws IOException, FirebaseMessagingException {
        //获取实例
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        //实例为空的情况
        if (firebaseApp == null) {
            return;
        }
        //构建消息内容
        //Notification(String title, String body, String imageUrl)
        Message message = Message.builder().setNotification(Notification.builder().setTitle(title).setBody(body).build())
                .putData("extra",extra)
                .setToken(token)
                .build();
        //发送后，返回messageID
        String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
        System.out.println("单个设备推送成功 : " + response);
    }

    /**
     * 给设备订阅主题
     * @param tokens  设备的token,最大1000个
     * @param topic   要添加的主题
     * @return
     * @throws FirebaseMessagingException
     * @throws IOException
     */
    public static void registrationTopic(List<String> tokens, String topic) throws FirebaseMessagingException, IOException {
        //获取实例
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        //实例不存在的情况
        if (firebaseApp == null) {
            return;
        }
        //订阅，返回主题管理结果对象。
        TopicManagementResponse response = FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(tokens, topic);
        System.out.println("添加设备主题，成功：" + response.getSuccessCount() + ",失败：" + response.getFailureCount());
    }

    /**
     * 取消设备的订阅主题
     * @param tokens  设备的token,最大1000个
     * @param topic   取消的主题
     * @return
     * @throws FirebaseMessagingException
     * @throws IOException
     */
    public static void cancelTopic( List<String> tokens, String topic) throws FirebaseMessagingException, IOException {
        //获取实例
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        //实例不存在的情况
        if (firebaseApp == null) {
            return;
        }
        //取消订阅，返回主题管理结果对象。
        TopicManagementResponse response = FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(tokens, topic);
        System.out.println("取消设备主题，成功：" + response.getSuccessCount() + ",失败：" + response.getFailureCount());
    }

    /**
     * 按主题推送
     * @param topic   主题的名字
     * @param title   消息题目
     * @param body    消息体
     * @return
     * @throws FirebaseMessagingException
     * @throws IOException
     */
    public static void sendTopicMes( String topic, String title, String body) throws FirebaseMessagingException, IOException {
        //获取实例
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        //实例不存在的情况
        if (firebaseApp == null) {
            return;
        }
        //构建消息
        Message message = Message.builder()
                .setNotification(Notification.builder().setTitle(title).setBody(body).build())
                .setTopic(topic)
                .build();
        //发送后，返回messageID
        String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
        System.out.println("主题推送成功: " + response);
    }

    /**
     * 单条Android设备推送消息(和pushSingle方法几乎没有区别)
     * @param token   注册token
     * @param title   推送题目
     * @param body    推送内容
     * @throws FirebaseMessagingException
     */
    public static void pushSingleToAndroid( String token, String title, String body) throws FirebaseMessagingException {
        //获取实例
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        //实例为空的情况
        if (firebaseApp == null) {
            return;
        }
        androidConfigBuilder.setRestrictedPackageName("io.telecomm.telecomm");
        androidNotifiBuilder.setColor("#55BEB7");// 设置消息通知颜色
        androidNotifiBuilder.setIcon("https://www.shiku.co/images/favicon.png");// 设置消息图标
        androidNotifiBuilder.setTitle(title);// 设置消息标题
        androidNotifiBuilder.setBody(body);// 设置消息内容
        AndroidNotification androidNotification = androidNotifiBuilder.build();
        androidConfigBuilder.setNotification(androidNotification);
        AndroidConfig androidConfig = androidConfigBuilder.build();
        //构建消息
        Message message = Message.builder()
                .setToken(token)
                .setAndroidConfig(androidConfig)
                .build();
        //发送后，返回messageID
        String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
        System.out.println("单个安卓设备推送成功 : " + response);
    }

    /**
     * Android按主题推送(和sendTopicMes方法几乎没有区别)
     * @param topic   主题的名字
     * @param title   消息题目
     * @param body    消息体
     * @return
     * @throws FirebaseMessagingException
     * @throws IOException
     */
    public static void sendTopicMesToAndroid( String topic, String title, String body) throws FirebaseMessagingException, IOException {
        //获取实例
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        //实例为空的情况
        if (firebaseApp == null) {
            return;
        }
        androidNotifiBuilder.setColor("#55BEB7");// 设置消息通知颜色
        androidNotifiBuilder.setIcon("https://www.shiku.co/images/favicon.png");// 设置消息图标
        androidNotifiBuilder.setTitle(title);// 设置消息标题
        androidNotifiBuilder.setBody(body);// 设置消息内容
        AndroidNotification androidNotification = androidNotifiBuilder.build();
        androidConfigBuilder.setNotification(androidNotification);
        AndroidConfig androidConfig = androidConfigBuilder.build();
        //构建消息
        Message message = Message.builder()
                .setTopic(topic)
                .setAndroidConfig(androidConfig)
                .build();
        String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
        System.out.println("安卓主题推送成功: " + response);
    }
}
