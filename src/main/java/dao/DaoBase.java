package dao;

import com.google.common.base.Stopwatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DaoBase {

  private final Logger logger = LoggerFactory.getLogger(DaoBase.class);

  private Connection connection;

  public DaoBase(Connection connection) {
    this.connection = connection;
  }

  protected ResultSet executeQuery(String sql) throws Exception {
    Stopwatch stopwatch = Stopwatch.createStarted();
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    stopwatch.stop();
    logger.info("{}\t{}", sql, stopwatch);
    return rs;
  }

  protected Integer executeUpdate(String sql) throws Exception {
    Stopwatch stopwatch = Stopwatch.createStarted();
    Statement stmt = connection.createStatement();
    Integer count = stmt.executeUpdate(sql);
    stopwatch.stop();
    logger.info("{}\t{}\tupdate count ={}", sql, stopwatch, count);
    return count;
  }

  protected Connection getConnection() {
    return connection;
  }

  protected void setConnection(Connection connection) {
    this.connection = connection;
  }
}
