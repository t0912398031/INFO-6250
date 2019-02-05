import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResultServlet extends HttpServlet {
    //Service method
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        // String ans = request.getParameter("ans");
        // String ans2 = request.getParameter("ans2");
        String ans3 = request.getParameter("ans3");

        // Cookie[] cookies = request.getCookies();

        HttpSession session = request.getSession();
        String ans = (String)session.getAttribute("ans");
        String ans2 = (String)session.getAttribute("ans2");
        session.invalidate();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Quiz</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Results here:</h2>");
        // for (Cookie c: cookies){
        //     out.println("<p>"+ c.getName() + ":" + c.getValue() + "</p>");
        //     c.setMaxAge(0);
        //     response.addCookie(c);
        // }
        // out.println("<p> ans3 :"+ request.getParameter("ans3") + "</p>");

        out.println("<p>"+ ans +"</p><br>");
        out.println("<p>"+ ans2 +"</p><br>");
        out.println("<p>"+ ans3 +"</p><br>");
        out.println("</body>");
        out.println("</html>");
    }
}