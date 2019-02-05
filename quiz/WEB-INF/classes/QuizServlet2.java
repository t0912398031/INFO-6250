import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuizServlet2 extends HttpServlet {
    //Service method
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String ans = request.getParameter("ans");
        String ans2 = request.getParameter("ans2");
        // Cookie ans2 = new Cookie("ans2", request.getParameter("ans2"));
        // response.addCookie(ans2);

        HttpSession session = request.getSession();
        session.setAttribute("ans2", ans2);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Quiz</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Page 3</h2>");
        out.println("<form action = 'resultservlet' method = 'post'>");
        // out.println("<form action = 'resultservlet?ans=" + ans + "&ans2=" + ans2 + "' method = 'post'>");
        out.println("<p>Question 3: Which HTTP method is idempotent ?</p>"
                + "<input type='radio' name='ans3' value='post' />Post<br />"
                + "<input type='radio' name='ans3' value='init' />Init<br />"
                + "<input type='radio' name='ans3' value='get' />Get<br />"
                + "<input type='radio' name='ans3' value='option' />Option<br />"
                // + "<input type='hidden' name='ans' value='" +ans+ "' />"
                // + "<input type='hidden' name='ans2' value='" +ans2+ "' />"               
                );
        out.println("<input type =  'submit' value = 'Next' name = 'button'/><br /></p>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}