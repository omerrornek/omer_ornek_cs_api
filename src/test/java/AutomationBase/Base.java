package AutomationBase;

import  AutomationBase.Report;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import static AutomationBase.Report.createFolderAndReport;

public class Base {






    @BeforeSuite
    public void adjust (){
        createFolderAndReport();
        Functions.initializeReport();
    }





    @AfterMethod
    public void afterTestReport(ITestResult result) throws Exception {

        if (result.getStatus() == ITestResult.FAILURE) {
            Report.logg.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Case Failed", ExtentColor.RED));
            Report.logg.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " Test Case Failed", ExtentColor.RED));



        } else if (result.getStatus() == ITestResult.SKIP) {
            Report.logg.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case Skipped", ExtentColor.ORANGE));
            Report.logg.log(Status.SKIP, MarkupHelper.createLabel(result.getThrowable() + " Test Case Skipped", ExtentColor.ORANGE));

        } else {
            Report.logg.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case Passed", ExtentColor.GREEN));
            Report.logg.log(Status.PASS, MarkupHelper.createLabel(result.getThrowable() + " Test Case Passed", ExtentColor.GREEN));
        }


    }





    @AfterSuite
    public void tearDown() throws InterruptedException, IOException {

        Thread.sleep(2000);
        Report.extent.flush();


    }




}
