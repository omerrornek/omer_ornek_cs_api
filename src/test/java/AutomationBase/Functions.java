package AutomationBase;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static AutomationBase.Informations.projectDirectory;
import static AutomationBase.Informations.todayDate;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class Functions {


    public static void reportTestCase(String author, String testCategory) {
        String functionName = Thread.currentThread().getStackTrace()[2].getMethodName();
        Report.logg = Report.extent.createTest(functionName).assignAuthor(author).assignCategory(testCategory);
    }


    public static void initializeReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter(new File(projectDirectory + todayDate + "/" + todayDate + ".html"));
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle(Informations.projectName + "TestReport" + todayDate);
        spark.config().setReportName(Informations.projectName + "TestReport" + todayDate);
        Report.report.extent = new ExtentReports();
        Report.report.extent.attachReporter(spark);


    }







}






