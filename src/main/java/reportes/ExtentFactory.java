package reportes;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {

    public static ExtentReports getInstance() {
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Navegador", "Chrome");
        extent.setSystemInfo("Ambiente", "QA");
        return extent;
    }
}
