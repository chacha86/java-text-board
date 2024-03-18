package org.example.base.dbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MyMapper<T> {
    List<T> getList(ResultSet rs) throws SQLException;
}
