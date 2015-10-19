package pl.gebickionline.restclient;

import org.junit.Test;
import wiremock.org.json.JSONArray;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JsonObjectNodeListTest {
    @Test
    public void givenNullBody_whenGetStringList_returnsEmptyList() throws Exception {
        ObjectNodeList nodeList = new JsonObjectNodeList(null);
        assertEquals(Collections.emptyList(), nodeList.asStringList());
    }

    @Test
    public void givenEmptyBody_whenGetStringList_returnsEmptyList() throws Exception {
        ObjectNodeList nodeList = new JsonObjectNodeList("    ");
        assertEquals(Collections.emptyList(), nodeList.asStringList());
    }

    @Test
    public void whenGetStringList_returnsStringList() throws Exception {
        String body = new JSONArray()
                .put(0, "value1")
                .put(1, "value2")
                .toString();

        ObjectNodeList nodeList = new JsonObjectNodeList(body);
        final List<String> expectedList = new ArrayList<>(asList("value1", "value2"));
        assertEquals(expectedList, nodeList.asStringList());
    }
}