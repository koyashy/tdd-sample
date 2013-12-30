import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
}
