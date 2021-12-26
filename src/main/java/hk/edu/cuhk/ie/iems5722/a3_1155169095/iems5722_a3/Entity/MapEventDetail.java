package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity;

import javax.persistence.*;

@Table(name = "map_event_detail")
@Entity
public class MapEventDetail {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "date", nullable = false, length = 100)
    private String date;

    @Column(name = "venue", nullable = false, length = 100)
    private String venue;

    @Column(name = "highlight", nullable = false, length = 200)
    private String highlight;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "mapEventDetail")
    private MapEvent mapEvent;

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}