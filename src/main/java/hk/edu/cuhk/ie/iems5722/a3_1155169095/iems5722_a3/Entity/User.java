package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToMany
    Set<Chatroom> joinedChatrooms;
}