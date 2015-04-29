import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import transaction.DataInitializeTransaction;
import transaction.SendActionTransaction;

public class Main extends HttpServlet {

  private static Logger logger = LoggerFactory.getLogger(Main.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    logger.info("request url:{}", req.getRequestURI());
    if (req.getRequestURI().startsWith("/transaction")) {
      transaction(req, resp);
    } else {
      showHome(req, resp);
    }
  }

  private void showHome(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.getWriter().print("Hello from Java!");
  }

  private void transaction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getRequestURI().startsWith("/transaction/initdatabase")) {
      DataInitializeTransaction transaction = new DataInitializeTransaction();
      transaction.execute(req, resp);
    } else if (req.getRequestURI().startsWith("/transaction/sendaction")) {
      SendActionTransaction transaction = new SendActionTransaction();
      transaction.execute(req, resp);
    }
  }

  public static void main(String[] args) throws Exception {
    Server server = new Server(Integer.valueOf(System.getenv("PORT")));
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    context.addServlet(new ServletHolder(new Main()), "/*");
    server.start();
    server.join();
  }
}
