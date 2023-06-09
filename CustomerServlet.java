import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;
public class CustomerServlet extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter out=res.getWriter();
out.println("<center><h1>Customer Details</h1></center>");
try{
Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/customers?useSSL=false&allowPublicKeyRetrieval=true","root","spandana123");
Statement stm = con.createStatement();
ResultSet r=stm.executeQuery("select * from customerdetails");
out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">");
out.println("<div class=\"container\">");
out.println("<table class=\"table table-hover table-bordered table-success\" cellspacing=\"5\" cellpadding=\"5\">");
out.println("<tr><th>Bank Account Number</th> <th> Name</th> <th> Email  </th> <th> Current Balance</th> </tr>");
while(r.next())
{
out.println("<tr> <td> "+r.getString(1)+"</td> ");
out.println("<td> "+r.getString(2)+"</td> ");
out.println("<td> "+r.getString(3)+"</td>");
out.println("<td> "+r.getInt(4)+"</td></tr> ");
}
out.println("</table></div></center>");
con.close();
}
catch(SQLException sq)
{
out.println("sql exception"+sq);
}
}
}





