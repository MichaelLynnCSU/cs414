import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class BaseTest {

    private static int testCount;
    private static int succeededCount;
    private static int failedCount;

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    @Rule
    public TestRule testWatcher = new TestWatcher() {
        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            succeededCount++;
            System.out.println("Test " + description.getDisplayName() + GREEN + " passed" + RESET);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            failedCount++;
            System.out.println("Test " + description.getDisplayName() + RED + " failed" + RESET);
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            System.out.println("Test " + description.getDisplayName() + " skipped");
        }

        @Override
        protected void starting(Description description) {
            testCount++;
            super.starting(description);
            System.out.println("====================================================================================");
            System.out.println("Test " + description.getDisplayName() +" started");
        }

        @Override
        protected void finished(Description description) {
            super.finished(description);
            System.out.println("Test " + description.getDisplayName() + " finished");
        }
    };

    @BeforeClass
    public static void initCount() {
        testCount = 0;
        succeededCount = 0;
        failedCount = 0;

        System.out.println("==============================================================================================================================");
        System.out.println("==============================================================================================================================");
    }

    @AfterClass
    public static void printCount() {
        System.out.println("\n==================");
        System.out.println("Tests Run: " + testCount);
        System.out.println("Tests Passed: " + GREEN + succeededCount + RESET);
        System.out.println("Tests Failed: " + RED + failedCount + RESET);
        System.out.println("==================\n");
    }

}
