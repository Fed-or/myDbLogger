package servlet;

import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/userResult")
public class UserServlet extends HttpServlet {
  private static final UserDao USERDAO = UserDao.getInstance();
  String firstName = null;
  String lastName = null;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html");

    firstName = req.getParameter("firstName");
    lastName = req.getParameter("lastName");

    long id = USERDAO.getIdByName(firstName, lastName);

    Optional <User> user = USERDAO.getUserByLoginAndPass(firstName, lastName);

    req.setAttribute("user", user);
    req.setAttribute("id", id);
    req.setAttribute("firstName", user.get().getFirstName());
    req.setAttribute("lastName", user.get().getLastName());
    req.setAttribute("login", user.get().getLogin());
    req.setAttribute("pass", user.get().getPassword());
    req.setAttribute("adress", user.get().getAdress());
    req.setAttribute("email", user.get().getEmail());

    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/userPage.jsp");
    requestDispatcher.forward(req, resp);
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("/userPage.jsp");
    req.setAttribute("user", USERDAO.getUserByLoginAndPass(firstName, lastName));
    dispatcher.forward(req, resp);
  }
}