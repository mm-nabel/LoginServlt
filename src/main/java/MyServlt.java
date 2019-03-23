import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlt extends HttpServlet {
    private UserDAO userDao = new UserDAO();

    @Override
    public void init() throws ServletException {
        super.init();
        userDao.generateDummyList();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String GeneratedPassword = userDao.findUser(username);

        HttpSession session = req.getSession();


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Test</title></head><body>");
        if (password.equals(GeneratedPassword)) {
            out.print("<script>alert('correct Login')</script>");
            req.getSession().setAttribute("validated", true);

            HttpSession session2 = req.getSession();
            session2.setAttribute("usern", username);

            resp.sendRedirect("welcome");
        } else {
            if (GeneratedPassword.equals("")) {
                out.print("<script>alert('User not found')</script>");
                req.getSession().setAttribute("validated", false);
            } else {
                out.print("<script>alert('password not match')</script>");
                req.getSession().setAttribute("validated", false);
            }
        }
        out.print("</body></html>");
    }

}
