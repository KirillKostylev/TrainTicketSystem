package by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.logic;

import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.entity.Admin;
import by.bntu.fitr.poisit.threadkeepers.trainticketsystem.model.exception.NullException;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminLogicTest {


    @Test
    public void writeAndRead() {
        try {
            Admin expectAdmin = new Admin("qwe", "asd");
            AdminLogic.write(new Admin("qwe", "asd"), AdminLogic.FILE_NAME_WITH_ADMINS_INFO);
            Assert.assertEquals(expectAdmin,
                    AdminLogic.readAdminsList(AdminLogic.FILE_NAME_WITH_ADMINS_INFO));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    @Test(expected = NullException.class)
    public void checkLoginAndPasswordWithNullParameter() throws FileNotFoundException, NullException{
       AdminLogic.checkLoginAndPassword(null, "asd");

}

    @Test
    public void checkLoginAndPassword() throws FileNotFoundException {
        try {
            Assert.assertTrue(AdminLogic.checkLoginAndPassword("qwe", "asd"));
        } catch ( NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void failCheckLoginAndPassword() throws FileNotFoundException {
        try {
            Assert.assertFalse(AdminLogic.checkLoginAndPassword("qwe", "asdd"));
        } catch ( NullException e) {
            e.printStackTrace();
        }
    }
}