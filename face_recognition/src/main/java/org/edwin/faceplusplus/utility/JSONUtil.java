package org.edwin.faceplusplus.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {

    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();;

    public static <T>T toObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static String formatFieldName(String json) {
        StringBuffer sb = new StringBuffer();
        formatEachField(json, sb);
        return sb.toString();
    }

    protected static void formatEachField(String json, StringBuffer sb) {
        int fieldEndPos = json.indexOf("\":");
        if (fieldEndPos > -1) {
            fieldEndPos += 2;
            String leftStr = json.substring(0, fieldEndPos);
            int fieldStartPos = leftStr.indexOf("\"");
            sb.append(leftStr.substring(0, fieldStartPos));

            String[] tmpStr = leftStr.substring(fieldStartPos).split("_");
            sb.append(tmpStr[0]);

            for (int i = 1; i < tmpStr.length; i++) {
                // first letter to upper case
                sb.append(Character.toUpperCase(tmpStr[i].charAt(0)));
                sb.append(tmpStr[i].substring(1));
            }

            json = json.substring(fieldEndPos);

            formatEachField(json, sb);
        } else {
            sb.append(json);
        }
    }

}
