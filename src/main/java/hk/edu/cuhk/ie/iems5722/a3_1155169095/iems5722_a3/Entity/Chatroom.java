package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "chatroom")
@Entity
public class Chatroom {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "total_page", nullable = false)
    private Integer totalPage;

    @Getter
    @ManyToMany
    @JoinTable(
            name = "chatroom_member",
            joinColumns = @JoinColumn(name = "chatroom_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> members;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}