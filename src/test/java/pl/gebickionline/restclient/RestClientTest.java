package pl.gebickionline.restclient;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

public class RestClientTest {
    public static final String TARGET_HOST = "http://localhost:8000";
    @Rule
    public WireMockRule service = new WireMockRule(8000);

    @Test
    public void testCreatingInstance() throws Exception {
        RestClient restClient = RestClient.get(TARGET_HOST);
        restClient = RestClient.post(TARGET_HOST);
        restClient = RestClient.put(TARGET_HOST);
        restClient = RestClient.delete(TARGET_HOST);
    }

    @Test
    public void testDefaultAcceptTypeIsPlainText() throws Exception {
        stubForGetMethod("application/json", "{\"response\":\"ok\"}");
        stubForGetMethod("text/plain", "ok");
        stubForGetMethod("application/xml", "<response>ok</response>");

        final Response response = RestClient.get(TARGET_HOST + "/resource").send();

        assertEquals(200, response.statusCode());
        assertEquals("ok", response.asString());
    }

    @Test
    public void testGetMethod() throws Exception {
        stubForGetMethod("application/json", "{\"response\":\"ok\"}");

        final Response response = RestClient.get(TARGET_HOST + "/resource")
                .header("accept", "application/json")
                .send();

        assertEquals(200, response.statusCode());
        assertEquals("{\"response\":\"ok\"}", response.asString());
    }

    private void stubForGetMethod(String contentType, String responseBody) {
        service.stubFor(get(urlEqualTo("/resource"))
                .withHeader("accept", equalTo(contentType))
                .willReturn(aResponse()
                        .withHeader("content-type", contentType)
                        .withStatus(200)
                        .withBody(responseBody)));
    }
}