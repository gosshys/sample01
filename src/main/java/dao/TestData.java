package dao;

import java.sql.Connection;

public class TestData extends DaoBase {

  public TestData(Connection connection) {
    super(connection);
  }

  public void initialize() throws Exception {
    // sensor master
    executeUpdate("DROP TABLE IF EXISTS sensor_m");
    executeUpdate("CREATE TABLE sensor_m (id int8, name varchar(20), action char(5))");
    executeUpdate("INSERT INTO sensor_m VALUES (1, 'bed', '10000')");
    executeUpdate("INSERT INTO sensor_m VALUES (2, 'chair', '20000')");
    executeUpdate("INSERT INTO sensor_m VALUES (3, 'desk', '30000')");
    executeUpdate("INSERT INTO sensor_m VALUES (4, 'kitchen', '10000')");

    // sensor transaction
    executeUpdate("DROP TABLE IF EXISTS sensor");
    executeUpdate("CREATE TABLE sensor (id int8, sensor_id int8, status char(1))");
    executeUpdate("INSERT INTO sensor VALUES (1, 1, '1')");
    executeUpdate("INSERT INTO sensor VALUES (2, 1, '0')");
    executeUpdate("INSERT INTO sensor VALUES (3, 2, '1')");
    executeUpdate("INSERT INTO sensor VALUES (4, 2, '1')");
    executeUpdate("INSERT INTO sensor VALUES (5, 3, '0')");
    executeUpdate("INSERT INTO sensor VALUES (6, 4, '0')");
  }
}
