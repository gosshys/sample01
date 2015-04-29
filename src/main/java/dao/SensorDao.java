package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Sensor;

public class SensorDao extends DaoBase {

  public SensorDao(Connection connection) {
    super(connection);
  }

  public List<Sensor> getActionRecord() throws Exception {
    ResultSet
        rs =
        executeQuery("SELECT s.id, s.sensor_id, s.status, m.action FROM sensor s JOIN sensor_m m on s.sensor_id = m.id WHERE s.status = '0'");
    List<Sensor> l = new ArrayList<Sensor>();
    while (rs.next()) {
      Sensor sensor = new Sensor();
      sensor.setId(rs.getLong("id"));
      sensor.setSensorId(rs.getLong("sensor_id"));
      sensor.setStatus(rs.getString("status"));
      l.add(sensor);
    }
    return l;
  }

  public Integer updateSendStatus(Long id) throws Exception {
    return executeUpdate("UPDATE sensor SET status = '1' WHERE id = " + id);
  }
}
