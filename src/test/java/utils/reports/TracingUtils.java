package utils.reports;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import org.testng.ITestResult;
import utils.runner.ProjectProperties;

import java.lang.reflect.Method;
import java.nio.file.Paths;

import static utils.reports.LoggerUtils.logInfo;

public final class TracingUtils {

    public static void startTracing(BrowserContext context) {
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
    }

    public static void stopTracing(Page page, BrowserContext context, Method testMethod, ITestResult testResult) {
        Tracing.StopOptions tracingStopOptions = null;

        if (!testResult.isSuccess()) {
            if (ProjectProperties.TRACING_MODE) {
                tracingStopOptions = new Tracing.StopOptions()
                        .setPath(Paths.get("target/testTracing/" + testMethod.getName() + ".zip"));
                logInfo("Tracing saved");
            }
            if (ProjectProperties.VIDEO_MODE) {
                page.video().saveAs(Paths.get("target/videos/" + testMethod.getName() + ".webm"));
                logInfo("Video saved");

                page.video().delete();
            }
        } else {
            page.video().delete();
        }

        context.tracing().stop(tracingStopOptions);
    }
}