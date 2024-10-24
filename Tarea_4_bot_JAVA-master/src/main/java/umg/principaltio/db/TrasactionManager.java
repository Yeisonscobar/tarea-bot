package umg.principaltio.db;

import java.sql.Connection;
import java.sql.SQLException;

public class TrasactionManager {


    private Connection connection;

    public TrasactionManager(Connection connection) {
        this.connection = connection;
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

}
