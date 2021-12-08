package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FBNotification implements Serializable {
    public List<String> tokens;
    public Message message;
    public String chatroomName;
    public String senderName;
}
