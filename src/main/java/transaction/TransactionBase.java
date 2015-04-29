package transaction;

import com.google.common.base.Stopwatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class TransactionBase {

  private final Logger logger = LoggerFactory.getLogger(TransactionBase.class);

  private Connection connection;

  protected abstract void doExecute() throws Exception;

  public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Connection connection = null;
    try {
      connection = getConnection();
      Stopwatch stopwatch = Stopwatch.createStarted();
      logger.info("start transaction");
      doExecute();
      logger.info("end transaction execute time: {}", stopwatch);
    } catch (Exception e) {
      logger.error("exception.", e);
      resp.getWriter().print("There was an error: " + e.getMessage());
      return;
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
        }
      }
    }
    resp.getWriter().print("Done");
  }

  private Connection getDataBaseConnection() {

    if (this.connection != null) {
      return connection;
    }

    try {
      // postgres://[username]:[password]@[host]:[port]/[database]
      URI dbUri = new URI(System.getenv("DATABASE_URL"));

      String username = dbUri.getUserInfo().split(":")[0];
      String password = dbUri.getUserInfo().split(":")[1];
      int port = dbUri.getPort();

      String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();
      logger.info("jdbc connection: {}", dbUrl);
      return DriverManager.getConnection(dbUrl, username, password);
    } catch (URISyntaxException e) {
      logger.error("no specific DATABASE_URL", e);
      return null;
    } catch (SQLException e) {
      logger.error("database connect failed", e);
      return null;
    }
  }

  // getter / setter

  public Connection getConnection() {
    if (this.connection == null) {
      this.connection = getDataBaseConnection();
    }
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }
}
