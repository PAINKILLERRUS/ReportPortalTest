package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Повторно запускает нестабильные тесты
 */
public class RetryService implements IRetryAnalyzer {
    private int count = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        int maxCount = 1;
        if (count < maxCount) {
            count++;
            System.out.println("Retrying test " + iTestResult.getName() +
                    " for the " + count + " time");
            return true;
        }
        count = 0;
        return false;
    }
}
