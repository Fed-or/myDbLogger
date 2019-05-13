package servlet;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    long id = 1;
    private static final UserDao USERDAO = UserDao.getInstance();
    public static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String email = req.getParameter("email");
        LOGGER.debug("User with id " + USERDAO.getIdByEmail(email) + " start registered");
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String adress = req.getParameter("adress");
        String email = req.getParameter("email");

        if (USERDAO.CheckUserExist(email)) {
            LOGGER.debug("User " + firstName + " already was registered");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("signin.jsp?message=error");
            requestDispatcher.forward(req, resp);
        }
        id = USERDAO.getIdByEmail(email);
        boolean newUser = USERDAO.addUser(new User(id, firstName, lastName,
                login, pass, adress, email, "user"));
        if (newUser) {
            LOGGER.debug("User with name " + firstName + " and id " + id + " was registered");
            req.setAttribute("login", login);
            req.setAttribute("firstName", firstName);
            req.setAttribute("firstName", lastName);
            req.setAttribute("pass", pass);
            req.setAttribute("email", email);
            req.setAttribute("isRegistered", true);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        }
        Optional<User> userOptional = USERDAO.getUserById(id);

        if (userOptional.isPresent()) {
            User userFromDb = userOptional.get();
            if (userFromDb.getFirstName().length() == 0
                    || userFromDb.getLastName().length() == 0
                    || userFromDb.getLogin().length() == 0
                    || userFromDb.getPassword().length() == 0
                    || userFromDb.getAdress().length() == 0
                    || userFromDb.getEmail().length() == 0) {
                req.setAttribute("errorField", "You did not fill one or more fields");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("registration.jsp");
                requestDispatcher.forward(req, resp);
                LOGGER.warn("User did not fill all fields");
            } else if (!newUser) {
                req.setAttribute("error", "User did not add");
            }
        }
    }
}