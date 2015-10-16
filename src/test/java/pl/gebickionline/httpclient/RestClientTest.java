package pl.gebickionline.httpclient;

import org.junit.Test;

/**
 * Created by £ukasz on 2015-10-16.
 */
public class RestClientTest {

    @Test
    public void testCreatingInstance() throws Exception {
        RestClient.get("http://google.pl").send();
    }
}