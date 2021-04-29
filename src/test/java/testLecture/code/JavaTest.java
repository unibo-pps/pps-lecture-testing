package testLecture.code;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.logging.Logger;

public class JavaTest {
    /*
    static final Logger LOGGER = Logger.getGlobal();

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            LOGGER.info("Starting test: " + description.getMethodName());
        }
    };
    */

    @Test
    public void someTest(){
        assert(true);
    }
}
