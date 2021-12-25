package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service;

import com.alibaba.fastjson.JSONObject;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.MapEvent;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.AnchorRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.MapEventDetailRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.MapEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapEventService {

    @Autowired
    private MapEventRepository mapEventRepository;

    @Autowired
    private MapEventDetailRepository mapEventDetailRepository;

    @Autowired
    private AnchorRepository anchorRepository;

    public String getMapEvents(){
        List<MapEvent> mapEventList = mapEventRepository.findAll();
        List<JSONObject> result = mapEventList.stream().map(mapEvent -> {
            return (JSONObject) JSONObject.toJSON(mapEvent);
        }).collect(Collectors.toList());
        return JSONObject.toJSONString(result);
    }
    public void saveMapEvents(){

    }
}
