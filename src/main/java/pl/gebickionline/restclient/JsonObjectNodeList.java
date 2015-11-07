package pl.gebickionline.restclient;

import org.json.JSONArray;

import java.util.*;

public class JsonObjectNodeList implements ObjectNodeList {
    private final JSONArray jsonArray;

    public JsonObjectNodeList(String jsonArray) {
        if (isEmpty(jsonArray)) {
            this.jsonArray = new JSONArray();
            return;
        }

        if (jsonArray.trim().startsWith("{") && jsonArray.trim().endsWith("}"))
            jsonArray = String.format("[%s]", jsonArray);

        this.jsonArray = new JSONArray(jsonArray);
    }


    private boolean isEmpty(String jsonArray) {
        return jsonArray == null || jsonArray.trim().isEmpty();
    }

    @Override
    public List<String> asStringList() {
        List<String> list = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++)
            list.add(getString(i));
        return list;
    }

    private String getString(int i) {
        if (isJsonObject(i))
            return jsonArray.getJSONObject(i).toString();

        if (isJsonArray(i))
            return jsonArray.getJSONArray(i).toString();

        return jsonArray.getString(i);
    }

    private boolean isJsonArray(int i) {
        try {
            jsonArray.getJSONArray(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isJsonObject(int i) {
        try {
            jsonArray.getJSONObject(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Long> asLongList() {
        List<Long> list = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++)
            list.add(jsonArray.getLong(i));
        return list;
    }

    @Override
    public List<Integer> asIntList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++)
            list.add(jsonArray.getInt(i));
        return list;
    }

    @Override
    public List<Double> asDoubleList() {
        List<Double> list = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++)
            list.add(jsonArray.getDouble(i));
        return list;
    }

    @Override
    public List<ObjectNode> asObjectList() {
        List<ObjectNode> list = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++)
            list.add(new JsonObjectNode(jsonArray.get(i).toString()));
        return list;
    }
}
