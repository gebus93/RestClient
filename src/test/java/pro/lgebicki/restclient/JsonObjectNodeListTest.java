package pro.lgebicki.restclient;

import org.hamcrest.CoreMatchers;
import org.json.*;
import org.junit.Test;
import pro.lgebicki.restclient.api.ObjectNodeList;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class JsonObjectNodeListTest {
    @Test
    public void givenNullBody_whenGetStringList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList(null);
        assertEquals(Collections.emptyList(), nodeList.asStringList());
    }

    @Test
    public void givenEmptyBody_whenGetStringList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList("    ");
        assertEquals(Collections.emptyList(), nodeList.asStringList());
    }

    @Test
    public void whenGetStringList_returnsStringList() {
        String body = new JSONArray()
                .put(0, "value1")
                .put(1, "value2")
                .toString();

        ObjectNodeList nodeList = new JsonObjectNodeList(body);
        final List<String> expectedList = new ArrayList<>(asList("value1", "value2"));
        assertEquals(expectedList, nodeList.asStringList());
        assertThat(nodeList.asStringList(), hasItems("value1", "value2"));
    }

    @Test
    public void givenNullBody_whenGetLongList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList(null);
        assertEquals(Collections.emptyList(), nodeList.asLongList());
    }

    @Test
    public void givenEmptyBody_whenGetLongList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList("    ");
        assertEquals(Collections.emptyList(), nodeList.asLongList());
    }

    @Test
    public void whenGetLongList_returnsLongList() {
        String body = new JSONArray()
                .put(0, 15)
                .put(1, 32)
                .toString();

        ObjectNodeList nodeList = new JsonObjectNodeList(body);
        assertThat(nodeList.asLongList(), hasItems(15L, 32L));
    }

    @Test
    public void givenNullBody_whenGetIntList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList(null);
        assertEquals(Collections.emptyList(), nodeList.asIntList());
    }

    @Test
    public void givenEmptyBody_whenGetIntList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList("    ");
        assertEquals(Collections.emptyList(), nodeList.asIntList());
    }

    @Test
    public void whenGetIntList_returnsIntList() {
        String body = new JSONArray()
                .put(0, 15)
                .put(1, 32)
                .toString();

        ObjectNodeList nodeList = new JsonObjectNodeList(body);
        assertThat(nodeList.asIntList(), hasItems(15, 32));
    }

    @Test
    public void givenNullBody_whenGetDoubleList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList(null);
        assertEquals(Collections.emptyList(), nodeList.asDoubleList());
    }

    @Test
    public void givenEmptyBody_whenGetDoubleList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList("    ");
        assertEquals(Collections.emptyList(), nodeList.asDoubleList());
    }

    @Test
    public void whenGetDoubleList_returnsDoubleList() {
        String body = new JSONArray()
                .put(0, 15.345)
                .put(1, 32.865)
                .toString();

        ObjectNodeList nodeList = new JsonObjectNodeList(body);
        assertThat(nodeList.asDoubleList(), hasItems(15.345, 32.865));
    }

    @Test
    public void givenNullBody_whenGetObjectList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList(null);
        assertEquals(Collections.emptyList(), nodeList.asObjectList());
    }

    @Test
    public void givenEmptyBody_whenGetObjectList_returnsEmptyList() {
        ObjectNodeList nodeList = new JsonObjectNodeList("    ");
        assertEquals(Collections.emptyList(), nodeList.asObjectList());
    }

    @Test
    public void whenGetObjectList_returnsObjectList() {
        String body = new JSONArray()
                .put(0, new JSONObject().put("fieldName1", "value1"))
                .put(1, new JSONObject().put("fieldName2", "value2"))
                .toString();

        ObjectNodeList nodeList = new JsonObjectNodeList(body);
        JsonObjectNode object1 = new JsonObjectNode("{\"fieldName1\":\"value1\"}");
        JsonObjectNode object2 = new JsonObjectNode("{\"fieldName2\":\"value2\"}");

        assertThat(nodeList.asObjectList(), CoreMatchers.hasItems(object1, object2));
    }

    @Test
    public void givenObject_whenGetObjectList_returnsListWithOneObject() {

        String body = new JSONObject()
                .put("fieldName1", "value1")
                .put("fieldName2", "value2")
                .toString();

        ObjectNodeList nodeList = new JsonObjectNodeList(body);
        JsonObjectNode object = new JsonObjectNode("{\"fieldName1\":\"value1\", \"fieldName2\":\"value2\"}");

        assertThat(nodeList.asObjectList(), CoreMatchers.hasItems(object));

    }

    @Test
    public void givenObjectList_whenGetStringList_returnsStringList() {
        String body = new JSONArray()
                .put(0, new JSONObject().put("fieldName1", "value1"))
                .put(1, new JSONObject().put("fieldName2", "value2"))
                .toString();

        ObjectNodeList nodeList = new JsonObjectNodeList(body);
        String object1 = new JsonObjectNode("{\"fieldName1\":\"value1\"}").toString();
        String object2 = new JsonObjectNode("{\"fieldName2\":\"value2\"}").toString();

        assertThat(nodeList.asStringList(), hasItems(object1, object2));
    }
}