package pl.gebickionline.restclient;

import org.junit.Test;
import wiremock.org.json.JSONObject;

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
    public void whenFieldIsString_returnsString() throws Exception {
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
    public void whenFieldIsInteger_returnsInteger() throws Exception {
        String jsonString = new JSONObject().put("fieldName", 5).toString();
        ObjectNode node = new JsonObjectNode(jsonString);
        assertEquals(new Integer(5), node.getInt("fieldName"));
    }
}