package AutomationBase;

import static AutomationBase.Report.date;
import static AutomationBase.Report.dateFormat;

public class Informations {

    public static String projectName = "InsiderAssignment";
    public static String projectDirectory = System.getProperty("user.dir")+"/" +Informations.projectName +"TestReports" + "/" ;
    public static String todayDate = dateFormat.format(date).toString().replace(":","_").replace(" ","_").replace("/","-");
    public static String baseUrl ="https://petstore.swagger.io/v2";
    public static String api_key = "special-key";
    public static String apiTestCategory = "Assignment";
    public static String author = "Omer Ornek";




}
