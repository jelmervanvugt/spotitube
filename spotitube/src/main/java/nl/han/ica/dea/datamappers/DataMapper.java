package nl.han.ica.dea.datamappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMapper<T> {
    T mapToDTO(ResultSet rs) throws SQLException;
}
