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
}
