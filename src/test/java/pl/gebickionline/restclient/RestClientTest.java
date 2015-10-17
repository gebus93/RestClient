package pl.gebickionline.restclient;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.*;
import wiremock.org.json.JSONObject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class RestClientTest {
    public static final String TARGET_HOST = "http://localhost:8000";
    @Rule
    public WireMockRule service = new WireMockRule(8000);
    private String jsonObject;
    private String xmlObject;

    @Before
    public void setUp() throws Exception {
        jsonObject = new JSONObject().put("field", "value").toString();
        xmlObject = "<field>value</field>";
    }

    @Test
    public void givenNoAcceptHeader_whenGetRequestInvoked_setsAcceptHeaderToPlainText() throws Exception {
        stubForGetMethod(MediaType.APPLICATION_JSON);
        stubForGetMethod(MediaType.TEXT_PLAIN);
        stubForGetMethod(MediaType.APPLICATION_XML);
        RestClient.get(TARGET_HOST + "/resource").send();
        verify(getRequestedFor(urlEqualTo("/resource")).withHeader("accept", equalTo(MediaType.TEXT_PLAIN)));
    }

    @Test
    public void givenAcceptHeader_whenGetRequestInvoked_sendsRequestWithAcceptHeader() throws Exception {
        stubForGetMethod(MediaType.APPLICATION_JSON);
        stubForGetMethod(MediaType.TEXT_PLAIN);
        stubForGetMethod(MediaType.APPLICATION_XML);

        RestClient.get(TARGET_HOST + "/resource")
                .header("accept", MediaType.APPLICATION_JSON)
                .send();

        verify(getRequestedFor(urlEqualTo("/resource")).withHeader("accept", equalTo(MediaType.APPLICATION_JSON)));
    }


    @Test
    public void givenBody_whenPutRequestInvoked_sendsRequestWithBody() throws Exception {
        stubForPutMethod(MediaType.APPLICATION_JSON, jsonObject);
        RestClient.put(TARGET_HOST + "/resource")
                .header("content-type", MediaType.APPLICATION_JSON)
                .body(jsonObject)
                .send();
        verify(putRequestedFor(urlEqualTo("/resource"))
                .withHeader("content-type", equalTo(MediaType.APPLICATION_JSON))
                .withRequestBody(equalTo(jsonObject)));
    }

    private void stubForPutMethod(String contentType, String jsonObject) {
        service.stubFor(put(urlEqualTo("/resource"))
                .withHeader("content-type", equalTo(contentType))
                .withRequestBody(equalTo(jsonObject))
                .willReturn(aResponse().withStatus(200)));
    }

    private void stubForGetMethod(String contentType) {
        service.stubFor(get(urlEqualTo("/resource"))
                .withHeader("accept", equalTo(contentType))
                .willReturn(aResponse().withStatus(200)));
    }

}