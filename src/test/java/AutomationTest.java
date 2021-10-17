import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.annotation.After;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class AutomationTest {
    private Object driver;
//    private static AndroidDriver<MobileElement> driver;

    @Test
    public void setupAppium() throws IOException, InterruptedException {
        String appiumServerURL = "http://127.0.0.1:4723/wd/hub";
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
        dc.setCapability("platformName","android");
        dc.setCapability("appPackage","io.flutter.demo.gallery");
        dc.setCapability("appActivity",".MainActivity");

        AndroidDriver<AndroidElement> ad = new AndroidDriver(new URL(appiumServerURL), dc);

        // implicit wait
        ad.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

        // create an object of AndroidTouch
        AndroidTouchAction ta = new AndroidTouchAction(ad);

        //swipe left to right
        AndroidElement pic1 = ad.findElementByAccessibilityId("Reply\nAn efficient, focused email app");
        ta.press(ElementOption.element(pic1)).waitAction().moveTo(PointOption.point(-500,210)).release().perform();

        Thread.sleep(2000);

        AndroidElement bannerShrine;
        bannerShrine = (AndroidElement) ad.findElementByAccessibilityId("Shrine\nA fashionable retail app");
        bannerShrine.click();


        MobileElement username = (MobileElement) ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[1]");
        username.click();
        MobileElement usernameinput = (MobileElement) ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[1]");
        usernameinput.sendKeys("admin");
        MobileElement password = (MobileElement) ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[2]");
        password.click();
        MobileElement passwordinput = (MobileElement) ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText[2]");
        passwordinput.sendKeys("admin");

        MobileElement nextbutton = (MobileElement) ad.findElementByAccessibilityId("NEXT");
        nextbutton.click();

        MobileElement addTocart = (MobileElement) ad.findElementByAccessibilityId("Whitney belt\n$35, Add to cart");
        addTocart.click();

        MobileElement cart = (MobileElement) ad.findElementByAccessibilityId("Shopping cart, no items");
        cart.click();

        MobileElement total = (MobileElement) ad.findElementByAccessibilityId("TOTAL\n" +
                "$44.10");
        total.click();

        System.out.println(total);

        MobileElement clearCart = (MobileElement) ad.findElementByAccessibilityId("CLEAR CART");
        clearCart.click();

        captureScreenShots();

        ad.quit();
    }

    public void captureScreenShots() throws IOException {
        String folder_name = "screenshot";
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Date format fot screenshot file name
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        //create dir with given folder name
        new File(folder_name).mkdir();
        //Setting file name
        String file_name=df.format(new Date())+".png";
        //coppy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
    }



}
