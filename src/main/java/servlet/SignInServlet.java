package servlet;

import dao.UserDao;
import model.Role;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/signin")
public class SignInServlet extends HttpServlet {

    private static final UserDao USERDAO = UserDao.getInstance();
    public static final Logger LOGGER = Logger.getLogger(SignInServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        req.setAttribute("login", login);

        User user = USERDAO.getUserByLoginAndPass(login, password).get();
        long id = USERDAO.getIdBylogin(login);
        if (user != null) {
            String helloUser = user.getFirstName();
            req.setAttribute("helloUser", helloUser);
            if(user.getRoleId().equals(Role.USER)){
                req.getSession().setAttribute("user", user);
                LOGGER.debug("User with id " + id + "logged in system like user");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("userPage.jsp");
            requestDispatcher.forward(req, resp);
        } else if(user.getRoleId().equals(Role.ADMIN)){
                LOGGER.debug("User with id " + id + "logged in system like admin");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin");
                requestDispatcher.forward(req, resp);
            }
            req.setAttribute("error", "User with such login/pass did not find");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        }
        else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp?message=isNotPresent");
            requestDispatcher.forward(req, resp);
        }
    }
}
