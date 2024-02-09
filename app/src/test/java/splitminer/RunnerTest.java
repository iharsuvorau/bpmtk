package splitminer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RunnerTest {
  @Test
  void runSplitMiner1() throws Exception {
    Configuration configuration =
        new Configuration("src/test/resources/Production.xes", 0.5, 0.5, false, false, false);
    String result = Runner.runSplitMiner1(configuration);
    assertFalse(result.isEmpty());
  }
}
