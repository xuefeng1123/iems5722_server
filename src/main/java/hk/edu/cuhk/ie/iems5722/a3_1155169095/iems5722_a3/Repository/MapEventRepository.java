package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.MapEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapEventRepository extends JpaRepository<MapEvent, Integer> {
    public List<MapEvent> findAll();
    public List<MapEvent> findAllById(Integer id);
}