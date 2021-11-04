package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Table(name = "message")
@Entity
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    private Integer id;

    @Getter
    @Column(name = "chatroom_id", nullable = false)
    private Integer chatroomId;

    @Getter
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Getter
    @Column(name = "content", nullable = false, length = 45)
    private String content;

    @Getter
    @Column(name = "time", nullable = false)
    private Instant time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(Integer chatroomId) {
        this.chatroomId = chatroomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}