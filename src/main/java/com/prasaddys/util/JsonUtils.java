package com.prasaddys.util;

import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonUtils {

    public static JSONObject extractJSONObject(String fileName) {
        return new JSONObject(readFile(fileName));
    }

    public static JSONObject[] fetchData(String fileName, String arrayName) {
        JSONArray jsonArray;
        try {
            jsonArray = extractJSONObject(fileName).getJSONArray(arrayName);
        } catch (Exception ex) {
            jsonArray = extractJSONObject(fileName).getJSONArray("default");
        }
        JSONObject[] jsonObject = new JSONObject[jsonArray.length()];
        for (int i = 0; i <jsonArray.length(); i++) {
            jsonObject[i] = jsonArray.getJSONObject(i);
        }
        return jsonObject;
    }

    public static String readFile(String fileName) {
        String result = "";
        try {
            Object object = JsonParser.parseReader(new FileReader(fileName));
            result = object.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
