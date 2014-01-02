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
public class CalculateTest {

    private static final WebDriver driver = new ChromeDriver();

    @AfterClass
    public static void down() {
        driver.quit();
    }

    @Test
    public void add() {
        final int a = 12;
        final int b = 34;

        driver.get(IndexTest.INDEX_URL);

        driver.findElement(By.cssSelector("input[name='a']")).sendKeys(String.valueOf(a));
        driver.findElement(By.cssSelector("input[name='b']")).sendKeys(String.valueOf(b));
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        String result = driver.findElement(By.cssSelector("#result")).getText();

        assertEquals(String.valueOf(a + b), result);
    }
}
