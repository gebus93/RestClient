package pl.gebickionline.restclient;

import org.json.JSONArray;

import java.util.*;

public class JsonObjectNodeList implements ObjectNodeList {
    private final JSONArray jsonArray;

    public JsonObjectNodeList(String jsonArray) {
        this.jsonArray = isEmpty(jsonArray)
                ? new JSONArray()
                : new JSONArray(jsonArray);
    }

    private boolean isEmpty(String jsonArray) {
        return jsonArray == null || jsonArray.trim().isEmpty();
    }

    @Override
    public List<String> asStringList() {
        if (jsonArray.length() == 0)
            return Collections.emptyList();

        List<String> list = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++)
            list.add(jsonArray.getString(i));
        return list;
    }
}
