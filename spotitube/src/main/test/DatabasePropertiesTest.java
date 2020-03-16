import nl.han.ica.dea.dao.testdao;
import nl.han.ica.dea.database.util.DatabaseProperties;
import org.junit.jupiter.api.Test;

public class DatabasePropertiesTest {

    @Test
    public void test() {
        DatabaseProperties p = new DatabaseProperties();
        testdao dao = new testdao();

        dao.getAll();
    }


}
