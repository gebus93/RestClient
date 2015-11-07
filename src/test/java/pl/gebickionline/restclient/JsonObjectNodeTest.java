package pl.gebickionline.restclient;

import org.junit.Test;
import wiremock.org.json.*;

import static org.junit.Assert.*;

public class JsonObjectNodeTest {
    @Test
    public void givenNullBody_whenGetString_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode(null);
        assertNull(node.getString("fieldName"));
    }

    @Test
    public void givenJsonWithoutRequestedField_whenGetString_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode("{}");
        assertNull(node.getString("fieldName"));
    }

    @Test
    public void givenStringValue_when_returnsString() throws Exception {
        String jsonString = new JSONObject()
                .put("fieldName", "value")
                .toString();
        ObjectNode node = new JsonObjectNode(jsonString);
        assertEquals("value", node.getString("fieldName"));
    }

    @Test
    public void givenNullBody_whenGetInt_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode(null);
        assertNull(node.getInt("fieldName"));
    }


    @Test
    public void givenJsonWithoutRequestedField_whenGetInt_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode("{}");
        assertNull(node.getInt("fieldName"));
    }

    @Test
    public void givenIntegerValue_whenGetInt_returnsInteger() throws Exception {
        String jsonString = new JSONObject().put("fieldName", 5).toString();
        ObjectNode node = new JsonObjectNode(jsonString);
        assertEquals(new Integer(5), node.getInt("fieldName"));
    }


    @Test
    public void givenNullBody_whenGetLong_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode(null);
        assertNull(node.getLong("fieldName"));
    }

    @Test
    public void givenJsonWithoutRequestedField_whenGetLong_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode("{}");
        assertNull(node.getLong("fieldName"));
    }

    @Test
    public void givenLongValue_whenGetLong_returnsLong() throws Exception {
        String jsonString = new JSONObject().put("fieldName", Long.MAX_VALUE).toString();
        ObjectNode node = new JsonObjectNode(jsonString);
        assertEquals(Long.MAX_VALUE, (long) node.getLong("fieldName"));
    }

    @Test
    public void givenNullBody_whenGetDouble_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode(null);
        assertNull(node.getDouble("fieldName"));
    }

    @Test
    public void givenJsonWithoutRequestedField_whenGetDouble_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode("{}");
        assertNull(node.getDouble("fieldName"));
    }

    @Test
    public void givenDoubleValue_whenGetDouble_returnsDouble() throws Exception {
        String jsonString = new JSONObject().put("fieldName", 1.125).toString();
        ObjectNode node = new JsonObjectNode(jsonString);
        assertEquals(1.125, node.getDouble("fieldName"), 0.00001);
    }

    @Test
    public void givenNullBody_whenGetBoolean_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode(null);
        assertNull(node.getBoolean("fieldName"));
    }

    @Test
    public void givenJsonWithoutRequestedField_whenGetBoolean_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode("{}");
        assertNull(node.getBoolean("fieldName"));
    }

    @Test
    public void givenBooleanValue_whenGetBoolean_returnsBoolean() throws Exception {
        String jsonString = new JSONObject().put("fieldName", false).toString();
        ObjectNode node = new JsonObjectNode(jsonString);
        assertFalse(node.getBoolean("fieldName"));
    }

    @Test
    public void givenNullBody_whenGetObjectNode_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode(null);
        assertNull(node.getObjectNode("fieldName"));
    }

    @Test
    public void givenJsonWithoutRequestedField_whenGetObjectNode_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode("{}");
        assertNull(node.getObjectNode("fieldName"));
    }

    @Test
    public void givenJsonObject_whenGetObjectNode_returnsObjectNode() throws Exception {
        String jsonObject1 = new JSONObject().put("fieldName", "value").toString();
        String jsonObject2 = new JSONObject().put("object", jsonObject1).toString();
        ObjectNode node = new JsonObjectNode(jsonObject2);

        assertEquals(new JsonObjectNode(jsonObject1), node.getObjectNode("object"));
    }


    @Test
    public void givenNullBody_whenGetList_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode(null);
        assertNull(node.getList("array"));
    }

    @Test
    public void givenEmptyBody_whenGetList_returnsNull() throws Exception {
        ObjectNode node = new JsonObjectNode("   ");
        assertNull(node.getList("array"));
    }

    @Test
    public void givenJsonListResponse_returnsObjectNodeList() throws Exception {
        String jsonObject = new JSONObject().put("array", new JSONArray()).toString();
        JsonObjectNode node = new JsonObjectNode(jsonObject);
        assertTrue(node.getList("array") instanceof JsonObjectNodeList);
    }

    @Test
    public void givenJsonObject_whenGetString_returnsString() throws Exception {
        String jsonString = new JSONObject()
                .put("fieldName", new JSONObject()
                        .put("fieldName2", "value"))
                .toString();
        ObjectNode node = new JsonObjectNode(jsonString);

        assertEquals("{\"fieldName2\":\"value\"}", node.getString("fieldName"));
    }

    @Test
    public void givenJsonArray_whenGetString_returnsString() throws Exception {
        String jsonString = new JSONObject()
                .put("fieldName", new JSONArray().put(0, "value"))
                .toString();
        ObjectNode node = new JsonObjectNode(jsonString);

        assertEquals("[\"value\"]", node.getString("fieldName"));
    }
}