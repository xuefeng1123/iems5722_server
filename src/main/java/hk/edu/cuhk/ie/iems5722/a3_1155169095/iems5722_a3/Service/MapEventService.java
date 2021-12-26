package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service;

import com.alibaba.fastjson.JSONObject;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.Anchor;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.MapEvent;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.MapEventDetail;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.AnchorRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.MapEventDetailRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Repository.MapEventRepository;
import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.yaml.snakeyaml.tokens.Token.ID.Anchor;

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
        List<JSONObject> eventList = mapEventList.stream().map(mapEvent -> {
            return (JSONObject) JSONObject.toJSON(mapEvent);
        }).collect(Collectors.toList());

        JSONObject result = new JSONObject();
        result.put("status", OK);
        result.put("data", eventList);
        return JSONObject.toJSONString(result);
    }


    public String saveMapEvent(String eventTitle, double latitude, double longitude, String eventSnippet, String anchorId){
        MapEventDetail mapEventDetail = new MapEventDetail();
        mapEventDetail.setTitle(eventTitle);
        mapEventDetail.setDate("");
        mapEventDetail.setVenue("");
        mapEventDetail.setHighlight("");

        mapEventDetailRepository.saveAndFlush(mapEventDetail);

        Anchor anchor = new Anchor();
        anchor.setAnchorId(anchorId);
        anchorRepository.saveAndFlush(anchor);

        MapEvent mapEvent = new MapEvent();
        mapEvent.setEventTitle(eventTitle);
        mapEvent.setLatitude(latitude);
        mapEvent.setLongitude(longitude);
        mapEvent.setEventSnippet(eventSnippet);
        mapEvent.setMapEventDetail(mapEventDetail);
        mapEvent.setAnchor(anchor);

        mapEventRepository.saveAndFlush(mapEvent);
        return Response.ReturnOK();
    }

    public String updateEventDetails(int eventId, String title, String date, String venue, String highlight){

        List<MapEvent> mapEvents = mapEventRepository.findAllById(eventId);

        if(mapEvents.size() < 1) return Response.ReturnError("no such a map event!");

        MapEventDetail detail = mapEvents.get(0).getMapEventDetail();
        detail.setTitle(title);
        detail.setDate(date);
        detail.setVenue(venue);
        detail.setHighlight(highlight);

        mapEventDetailRepository.saveAndFlush(detail);

        return Response.ReturnOK();
    }
}
