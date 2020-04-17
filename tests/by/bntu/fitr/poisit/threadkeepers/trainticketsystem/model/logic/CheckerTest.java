package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.EmptyFieldException;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class CheckerTest {

    private static final Logger LOG = Logger.getLogger(LogicCashier.class);

    @Test
    public void checkForEmptyStringExceptionTest_NoStrings() {
        try {
            Checker.checkForEmptyFieldException(LOG, "error");
        } catch (EmptyFieldException e) {
            Assert.fail();
        } finally {
            Assert.assertEquals("", "");
        }
    }

    @Test
    public void checkForEmptyStringExceptionTest_NoEmptyStrings() {
        try {
            Checker.checkForEmptyFieldException(LOG, "error", "fds");
        } catch (EmptyFieldException e) {
            Assert.fail();
        } finally {
            Assert.assertEquals("", "");
        }
    }

    @Test
    public void checkForEmptyStringExceptionTest_EmptyStrings() {
        try {
            Checker.checkForEmptyFieldException(LOG, "error", "");
            Assert.fail();
        } catch (EmptyFieldException e) {
            Assert.assertEquals("error", e.getMessage());
        }
    }
}
