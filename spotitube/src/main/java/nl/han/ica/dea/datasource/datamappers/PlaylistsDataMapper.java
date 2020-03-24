package nl.han.ica.dea.datasource.datamappers;

import nl.han.ica.dea.controller.dto.PlaylistDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistsDataMapper implements DataMapper<ArrayList<PlaylistDTO>> {

    @Override
    public ArrayList<PlaylistDTO> mapToDTO(ResultSet rs) throws SQLException {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        while (rs.next()) {
            playlists.add(new PlaylistDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("isowner")));
        }
        return playlists;
    }
}
