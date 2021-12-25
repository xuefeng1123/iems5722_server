package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity;

import javax.persistence.*;

@Table(name = "map_event")
@Entity
public class MapEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "event_title", nullable = false, length = 100)
    private String eventTitle;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "event_snippet", nullable = false, length = 100)
    private String eventSnippet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "map_event_detail_id")
    private MapEventDetail mapEventDetail;


    public String getEventSnippet() {
        return eventSnippet;
    }

    public void setEventSnippet(String eventSnippet) {
        this.eventSnippet = eventSnippet;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}