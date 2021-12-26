package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Controller;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Service.MapEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api/project")
public class MapAnchorController {

    @Autowired
    private MapEventService mapEventService;


    @RequestMapping(method = RequestMethod.GET, value = "/get_map_events")
    @ResponseBody
    public String getMapEvents(){
        return mapEventService.getMapEvents();
    }

    @RequestMapping(value="/save_map_event",method=RequestMethod.POST)
    @ResponseBody
    public String saveMapEvent(@RequestParam("event_title") String event_title,
                              @RequestParam("latitude") double latitude,
                              @RequestParam("longitude") double longitude,
                              @RequestParam("event_snippet") String eventSnippet,
                              @RequestParam("anchor_id") String anchorId){
        return mapEventService.saveMapEvent(event_title, latitude, longitude, eventSnippet, anchorId);
    }

    @RequestMapping(value = "/update_event_detail", method = RequestMethod.POST)
    @ResponseBody
    public String updateEventDetail(@RequestParam("event_id") int eventId,
                                    @RequestParam("event_title") String title,
                                    @RequestParam("event_date") String date,
                                    @RequestParam("event_venue") String venue,
                                    @RequestParam("highlight") String highlight){
        return mapEventService.updateEventDetails(eventId, title, date, venue, highlight);
    }


}
