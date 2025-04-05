package Apitest;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Apitest.utils.KarateExtentReporter; // Make sure this path is correct

class TestRunner {

    @Test
    void testParallel() {
        Results results = Runner.path("classpath:Apitest") // Feature files should be in src/test/resources/Apitest
                .outputCucumberJson(true)
                .parallel(5);

        // Generate Extent Report
        KarateExtentReporter.generateReport(results);

        // Assert test success
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
