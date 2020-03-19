import nl.han.ica.dea.controllers.PlaylistController;
import nl.han.ica.dea.dao.LoginDAO;
import nl.han.ica.dea.dao.PlaylistsDAO;
import nl.han.ica.dea.database.util.DatabaseProperties;
import nl.han.ica.dea.dto.PlaylistsDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabasePropertiesTest {

    @Test
    public void test() throws SQLException {

//        PlaylistsDAO pd = new PlaylistsDAO();
//        pd.initConnection();
//        PlaylistsDTO pdto = pd.getAllPlaylists("135-783-710");
//
//        System.out.println(pdto);

//        PlaylistsDAO pd = new PlaylistsDAO();
//        pd.initConnection();
//        ResultSet resultSet = pd.queryPlaylistInfo("135-783-710");
//
//        ResultSetMetaData rsmd = resultSet.getMetaData();
//        int columnsNumber = rsmd.getColumnCount();
//        while (resultSet.next()) {
//            for (int i = 1; i <= columnsNumber; i++) {
//                if (i > 1) System.out.print(",  ");
//                String columnValue = resultSet.getString(i);
//                System.out.print(columnValue + " " + rsmd.getColumnName(i));
//            }
//            System.out.println("");
//        }

    }


}
