package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

public class Response {
    static public String ReturnOK(){
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("status", OK);
        return JSON.toJSONString(jsonResult);
    }

    static public String ReturnError(String errorInfo){
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        jsonResult.put("info", errorInfo);
        return JSON.toJSONString(jsonResult);
    }
}
