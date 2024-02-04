package tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.*;

import utils.reports.ReportUtils;
import utils.reports.TracingUtils;
import utils.runner.BrowserManager;
import utils.runner.LoginUtils;
import utils.runner.ProjectProperties;

import java.io.IOException;
import java.lang.reflect.Method;

import static utils.reports.LoggerInfo.getBrowserInfo;
import static utils.reports.LoggerUtils.*;

abstract class BaseTest {

    private final Playwright playwright = Playwright.create();
    private final Browser browser = BrowserManager.createBrowser(playwright);
    private BrowserContext context;
    private Page page;

    @BeforeSuite
    void launchBrowser() {

        if (playwright != null){
            logInfo("Playwright created.");
        } else {
            logFatal("FATAL: Playwright is NOT created.");
            System.exit(1);
        }

        if (browser.isConnected()) {
            logInfo(getBrowserInfo(browser));

        } else {
            logFatal("FATAL: Browser is NOT connected.");
            System.exit(1);
        }
    }

    @BeforeMethod
    void createContextAndPage(Method method) {

        logInfo("RUN " + ReportUtils.getTestMethodName(method));

        context = BrowserManager.createContext(browser);
        logInfo("Context created.");

        TracingUtils.startTracing(context);
        logInfo("Tracing started");

        page = context.newPage();
        logInfo("Page created.");

        page.navigate(ProjectProperties.BASE_URL);
        logInfo("Open Home page");
        Allure.step("User has navigated to the Home page.");
        logInfo("Testing....");

        LoginUtils.login(page);
        Allure.step("User logged in.");
    }

    @AfterMethod
    void closeContext(Method method, ITestResult testResult) throws IOException {
        ReportUtils.logTestStatistic(method, testResult);
        ReportUtils.addScreenshotToAllureReportForCIFailure(page,testResult);

        if (page != null){
            page.close();
            logInfo("Page closed.");
        }

        TracingUtils.stopTracing(page, context, method, testResult);
        logInfo("Tracing stopped");

        ReportUtils.addVideoAndTracingToAllureReportForCIFailure(method, testResult);

        if (context !=null) {
            context.close();
            logInfo("Context closed.");
        }
    }

    @AfterSuite
    void closeBrowserAndPlaywright() {
        if(browser != null) {
            browser.close();
            logInfo("Browser closed.");
        }

        if(playwright != null) {
            playwright.close();
            logInfo("Playwright closed.");
        }
    }

    Page getPage() {
        return page;
    }
}
