import nl.han.ica.dea.dao.LoginDAO;
import nl.han.ica.dea.database.util.DatabaseProperties;
import org.junit.jupiter.api.Test;

public class DatabasePropertiesTest {

    @Test
    public void test() {
        DatabaseProperties p = new DatabaseProperties();
        LoginDAO dao = new LoginDAO();

        //dao.getAll();
        boolean gang = dao.checkCredentials("user", "password");

        System.out.println(gang);

    }


}
