import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static javax.ws.rs.core.Response.StatusType;
import static org.junit.Assert.assertEquals;


/**
 * @author Akira Koyasu
 */
public class IndexTest {
    static final String INDEX_URL = "http://localhost:63342/tdd-sample/public/index.php";

    private static final WebDriver driver = new ChromeDriver();
    private static final Client client = ClientBuilder.newClient();

    static {
        client.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        client.property(ClientProperties.READ_TIMEOUT, 3000);
        client.register(new ClientRequestFilter() {
            @Override
            public void filter(ClientRequestContext requestContext) throws IOException {
                requestContext.getHeaders().add(
                        HttpHeaders.USER_AGENT,
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
            }
        });
    }

    @AfterClass
    public static void down() {
        driver.quit();
    }

    @Test
    public void testHeading() {
        driver.get(INDEX_URL);

        WebElement element = driver.findElement(By.cssSelector("h1"));

        assertEquals("Hello, I'm Calculator.", element.getText());
    }

    @Test
    public void testHttpResponse() throws IOException {
        Response r = client.target(INDEX_URL).request()
                .header(HttpHeaders.ACCEPT_LANGUAGE, "ja")
                .get();

        StatusType status = r.getStatusInfo();
        String contentType = r.getHeaderString(HttpHeaders.CONTENT_TYPE);

        assertEquals(200, status.getStatusCode());
        assertEquals(MediaType.HTML_UTF_8.withoutParameters().toString(), contentType);
    }

    @Test
    public void hasForm() {
        driver.get(INDEX_URL);

        driver.findElement(By.cssSelector("input[name='a']"));
        driver.findElement(By.cssSelector("input[name='b']"));
        driver.findElement(By.cssSelector("input[type='submit']"));
    }
}
