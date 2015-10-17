package pl.gebickionline.restclient;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.*;
import wiremock.org.json.JSONObject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class RestClientTest {
    public static final String TARGET_HOST = "http://localhost:8000";
    @Rule
    public WireMockRule service = new WireMockRule(8000);
    private String jsonObject;

    @Before
    public void setUp() throws Exception {
        jsonObject = new JSONObject().put("field", "value").toString();
    }

    @Test
    public void givenNoAcceptHeader_whenGetRequestInvoked_setsAcceptHeaderToAllTypes() throws Exception {
        stubForGetMethod(MediaType.TEXT_PLAIN);
        RestClient.get(TARGET_HOST + "/resource").send();
        verify(getRequestedFor(urlEqualTo("/resource")).withHeader("accept", equalTo("*/*")));
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
    public void whenPutRequestInvoked_sendsRequest() throws Exception {
        service.stubFor(put(urlEqualTo("/resource")).willReturn(aResponse().withStatus(200)));
        RestClient.put(TARGET_HOST + "/resource").send();
        verify(putRequestedFor(urlEqualTo("/resource")));
    }

    @Test
    public void whenPostRequestInvoked_sendsRequest() throws Exception {
        service.stubFor(post(urlEqualTo("/resource")).willReturn(aResponse().withStatus(200)));
        RestClient.post(TARGET_HOST + "/resource").send();
        verify(postRequestedFor(urlEqualTo("/resource")));
    }

    @Test
    public void whenDeleteRequestInvoked_sendsRequest() throws Exception {
        service.stubFor(delete(urlEqualTo("/resource")).willReturn(aResponse().withStatus(204)));
        RestClient.delete(TARGET_HOST + "/resource").send();
        verify(deleteRequestedFor(urlEqualTo("/resource")));
    }


    @Test
    public void givenBody_whenPutRequestInvoked_sendsRequestWithBody() throws Exception {
        stubWithBodyFor(put(urlEqualTo("/resource")));
        RestClient.put(TARGET_HOST + "/resource")
                .header("content-type", MediaType.APPLICATION_JSON)
                .body(jsonObject)
                .send();
        verify(putRequestedFor(urlEqualTo("/resource"))
                .withHeader("content-type", equalTo(MediaType.APPLICATION_JSON))
                .withRequestBody(equalTo(jsonObject)));
    }


    @Test
    public void givenBody_whenPostRequestInvoked_sendsRequestWithBody() throws Exception {
        stubWithBodyFor(post(urlEqualTo("/resource")));
        RestClient.post(TARGET_HOST + "/resource")
                .header("content-type", MediaType.APPLICATION_JSON)
                .body(jsonObject)
                .send();
        verify(postRequestedFor(urlEqualTo("/resource"))
                .withHeader("content-type", equalTo(MediaType.APPLICATION_JSON))
                .withRequestBody(equalTo(jsonObject)));
    }


    @Test
    public void givenBody_whenDeleteRequestInvoked_sendsRequestWithBody() throws Exception {
        stubWithBodyFor(delete(urlEqualTo("/resource")));
        RestClient.delete(TARGET_HOST + "/resource")
                .header("content-type", MediaType.APPLICATION_JSON)
                .body(jsonObject)
                .send();
        verify(deleteRequestedFor(urlEqualTo("/resource"))
                .withHeader("content-type", equalTo(MediaType.APPLICATION_JSON))
                .withRequestBody(equalTo(jsonObject)));
    }


    private void stubForGetMethod(String contentType) {
        service.stubFor(get(urlEqualTo("/resource"))
                .withHeader("accept", equalTo(contentType))
                .willReturn(aResponse().withStatus(200)));
    }

    private void stubWithBodyFor(MappingBuilder builder) {
        service.stubFor(builder
                .withHeader("content-type", equalTo(MediaType.APPLICATION_JSON))
                .withRequestBody(equalTo(jsonObject))
                .willReturn(aResponse().withStatus(200)));
    }

}