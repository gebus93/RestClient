package pro.lgebicki.restclient;

import org.junit.*;
import pro.lgebicki.restclient.api.ObjectNode;
import pro.lgebicki.restclient.api.Response;

import java.util.*;

import static org.junit.Assert.*;

public class ResponseImplTest {
    private Map<String, String> headers;

    @Before
    public void setUp() {
        headers = new HashMap<>();
    }

    @Test
    public void givenStatusCode_returnsStatusCode() {
        Response response = new ResponseImpl(headers, 200, null);
        assertEquals(200, response.statusCode());
    }

    @Test
    public void givenNullBody_returnsEmptyString() {
        Response response = new ResponseImpl(headers, 200, null);
        assertEquals("", response.asString());
    }

    @Test
    public void givenNullBody_returnsNullByteArray() {
        Response response = new ResponseImpl(headers, 200, null);
        assertNull(response.asByteArray());
    }

    @Test
    public void givenStringBody_returnsStringBody() {
        Response response = new ResponseImpl(headers, 200, "Response".getBytes());
        assertEquals("Response", response.asString());
    }

    @Test
    public void givenNullBody_whenGetJsonObject_returnsNull() {
        Response response = new ResponseImpl(headers, 200, null);
        assertNull(response.asJsonObject());
    }

    @Test
    public void givenJsonResponse_returnsObjectNode() {
        Response response = new ResponseImpl(headers, 200, "{}".getBytes());
        ObjectNode jsonObject = response.asJsonObject();
        assertTrue(jsonObject instanceof JsonObjectNode);
    }

    @Test
    public void givenNullBody_whenGetList_returnsNull() {
        Response response = new ResponseImpl(headers, 200, null);
        assertNull(response.asList());
    }

    @Test
    public void givenJsonListResponse_returnsObjectNodeList() {
        Response response = new ResponseImpl(headers, 200, "[]".getBytes());
        assertTrue(response.asList() instanceof JsonObjectNodeList);
    }
}