import com.google.common.net.HttpHeaders;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author Akira Koyasu
 */
public class IndexTest {

    private static final WebDriver driver = new ChromeDriver();

    @AfterClass
    public static void down() {
        driver.quit();
    }

    @Test
    public void testHeading() {
        String url = "http://localhost:63342/tdd-sample/public/index.php";
        driver.get(url);

        WebElement element = driver.findElement(By.cssSelector("h1"));

        Assert.assertEquals("Hello, I'm Calculator.", element.getText());
    }

    @Test
    public void testHttpHeaderByHc() throws IOException, URISyntaxException {
        String url = "http://localhost:63342/tdd-sample/public/index.php";

        URI uri = new URIBuilder(url).addParameter("q", "httpclient")
                .build();
        Header header = Request.Get(uri)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .addHeader(HttpHeaders.USER_AGENT, "MyAgent").execute()
                .returnResponse()
                .getFirstHeader(HttpHeaders.CONTENT_TYPE);

        System.out.println(header);
    }

    @Test
    public void testHttpHeaderByHc2() throws IOException, URISyntaxException {
        String url = "http://localhost:63342/tdd-sample/public/index.php";

        URI uri = new URIBuilder(url).addParameter("q", "httpclient")
                .build();
        Header header = Executor.newInstance()
                .auth(new HttpHost("myproxy", 8080), "username", "password")
                .execute(Request.Get(uri)
                        .connectTimeout(1000)
                        .socketTimeout(1000)
                        .addHeader(HttpHeaders.USER_AGENT, "MyAgent"))
                .returnResponse()
                .getFirstHeader(HttpHeaders.CONTENT_TYPE);

        System.out.println(header);
    }

    @Test
    public void testHttpHeaderByJaxrs() throws IOException {
        String url = "http://localhost:63342/tdd-sample/public/index.php";

        Client c = ClientBuilder.newClient();
        c.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        c.property(ClientProperties.READ_TIMEOUT, 1000);
        c.register(HttpAuthenticationFeature.basic("user", "password"));

        String header = c.target(url).queryParam("q", "httpclient").request()
                .header(HttpHeaders.USER_AGENT, "MyAgent")
                .cookie("hehehe", "hohoho")
                .get()
                .getHeaderString(HttpHeaders.CONTENT_TYPE);

        System.out.println(header);
    }
}
