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
    @Test
    public void checkLoginAndPasswordTest_SuccessLogin() throws FileNotFoundException, NullException{
       Assert.assertTrue(AdminLogic.checkLoginAndPassword("qwe", "asd"));

}

    @Test
    public void checkLoginAndPasswordTest_UnsuccessLogin() throws FileNotFoundException {
        try {
            Assert.assertFalse(AdminLogic.checkLoginAndPassword("qw", "asd"));
        } catch (FileNotFoundException | NullException e) {
            e.printStackTrace();
        }
    }

    /*@Test
    public void failCheckLoginAndPassword() throws FileNotFoundException {
        try {
            Assert.assertFalse(AdminLogic.checkLoginAndPassword("qwe", "asdd"));
        } catch ( NullException e) {
            e.printStackTrace();
        }
    }*/
}