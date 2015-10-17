package pl.gebickionline.restclient;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseImplTest {
    @Test
    public void givenStatusCode_returnsStatusCode() throws Exception {
        Response response = new ResponseImpl(200, null);
        assertEquals(200, response.statusCode());
    }

    @Test
    public void givenNoBody_returnsEmptyString() throws Exception {
        Response response = new ResponseImpl(200, null);
        assertEquals("", response.asString());
    }

    @Test
    public void givenNoBody_returnsNullByteArray() throws Exception {
        Response response = new ResponseImpl(200, null);
        assertNull(response.asByteArray());
    }

    @Test
    public void givenStringBody_returnsStringBody() throws Exception {
        Response response = new ResponseImpl(200, "Response".getBytes());
        assertEquals("Response", response.asString());
    }

    @Test
    public void givenJsonResponse_returnsJsonObject() throws Exception {
        Response response = new ResponseImpl(200, "Response".getBytes());
        ObjectNode jsonObject = response.asJsonObject();
        assertTrue(jsonObject instanceof JsonObjectNode);
    }
}