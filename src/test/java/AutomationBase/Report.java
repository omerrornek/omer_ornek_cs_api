package AutomationBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static AutomationBase.Informations.projectDirectory;
import static AutomationBase.Informations.todayDate;

public class Report {

    public static ExtentReports extent;
    public static ExtentTest logg;
    static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    static Date date = new Date();
    static Report report = new Report();

    public static void infoLog(String infoText) {
        Report.logg.log(Status.INFO, MarkupHelper.createLabel(infoText, ExtentColor.BLUE));
    }


    public static void warningLog(String infoText) {
        Report.logg.log(Status.WARNING, MarkupHelper.createLabel(infoText, ExtentColor.YELLOW));
    }






    public static void createFolderAndReport() {

        File todayFile = new File(projectDirectory + todayDate);
        todayFile.mkdir();

        File failSkippedFile = new File(projectDirectory + todayDate + "/" + "Fail-Skipped-Passed Images");
        failSkippedFile.mkdir();



    }






}
