import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BoardPanelTest.class, BottomPanelTest.class, ChanceCardsTest.class, CommunityChestCardsTest.class, ControllerTest.class, DieTest.class, GoToJailTest.class, JailTest.class,
        MonopolyBoardTest.class, NewGameWindowTest.class, PlayerTest.class, PropertyImprovementTest.class, PropertyTest.class, RailroadTest.class,
        TaxTest.class, UtilityTest.class, WindowTest.class})
public class MonopolySuite {

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MonopolySuite.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(RED + failure.toString() + RESET);
        }

        int testCount = result.getRunCount();
        int failedCount = result.getFailureCount();
        int succeededCount = testCount - failedCount;

        System.out.println("\n=======================");
        System.out.println("Total Tests Run: " + testCount);
        System.out.println("Total Tests Passed: " + GREEN + succeededCount + RESET);
        System.out.println("Total Tests Failed: " + RED + failedCount + RESET);
        System.out.println("=======================\n");
    }
}
