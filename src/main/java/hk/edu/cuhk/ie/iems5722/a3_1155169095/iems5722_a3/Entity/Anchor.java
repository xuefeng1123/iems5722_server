package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity;

import javax.persistence.*;

@Table(name = "anchor", indexes = {
        @Index(name = "anchorId_UNIQUE", columnList = "anchorId", unique = true)
})
@Entity
public class Anchor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "anchorId", nullable = false, length = 100)
    private String anchorId;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "anchor")
    private MapEvent mapEvent;

    public String getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(String anchorId) {
        this.anchorId = anchorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}