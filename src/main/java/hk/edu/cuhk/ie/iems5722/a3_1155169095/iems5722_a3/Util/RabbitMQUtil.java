package hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Util;

import hk.edu.cuhk.ie.iems5722.a3_1155169095.iems5722_a3.Entity.FBNotification;

import java.io.*;
import java.util.List;

public class RabbitMQUtil {
    static public byte[] getBytesFromObject(FBNotification obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        return bo.toByteArray();
    }
    static public Object getObjectFromBytes(byte[] objBytes) throws Exception {
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }
}
