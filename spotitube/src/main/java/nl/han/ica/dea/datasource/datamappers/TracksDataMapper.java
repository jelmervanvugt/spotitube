package nl.han.ica.dea.datasource.datamappers;

import nl.han.ica.dea.controller.dto.TrackDTO;
import nl.han.ica.dea.controller.dto.TracksDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TracksDataMapper implements DataMapper<TracksDTO> {
    @Override
    public TracksDTO mapToDTO(ResultSet rs) throws SQLException {
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        while (rs.next()) {
            tracks.add(new TrackDTO(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("performer"),
                    rs.getInt("duration"),
                    rs.getString("album"),
                    rs.getInt("playcount"),
                    rs.getString("publicationDate"),
                    rs.getString("description"),
                    rs.getBoolean("offlineAvailable")
            ));
        }
        return new TracksDTO(tracks);
    }
}
