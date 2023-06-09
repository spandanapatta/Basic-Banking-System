import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class TransactionServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String usr = req.getParameter("customer");
String pwd = req.getParameter("amount");
String usr1 = req.getParameter("customer1");
String accn1 = req.getParameter("accn1");
String accn2 = req.getParameter("accn2");

int amount = Integer.parseInt(pwd);
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customers?useSSL=false","root","spandana123");
Statement stm = con.createStatement();
ResultSet rs = stm.executeQuery("select balance from customerdetails where name='"+usr1+"'");

if(rs.next()){
String n = rs.getString(1);
int balance1 = Integer.parseInt(n);
if(balance1 - amount > 0){
String q = "update customerdetails set balance = balance +'"+amount+"' where name='"+usr+"'";
int x = stm.executeUpdate(q);
String q1 = "update customerdetails set balance = balance - '"+amount+"' where name='"+usr1+"'";
int x2 = stm.executeUpdate(q1);
out.print("<html><br><br><center><img src = https://png.pngtree.com/png-vector/20190228/ourmid/pngtree-check-mark-icon-design-template-vector-isolated-png-image_711429.jpg width = 400 height = 400 <br></center></html>");
out.println("<center><h2>Transaction Succesfull...</h2></center>");
String q2 = "insert into transaction values('"+usr+"','"+usr1+"',"+amount+",'"+dtf.format(now)+"')";
PreparedStatement stm1 = con.prepareStatement(q2);
int x1 = stm1.executeUpdate();
}
else{
out.print("<html><br><br><center><img src = https://jumeirahroyal.com/wp-content/uploads/d7e50cb89c.png width = 400 height = 400 <br></center></html>");
out.println("<center><h2>Transaction Failed due to Insufficient Balance...</h2></center>");
String q2 = "insert into transaction values('"+usr+"','"+usr1+"',"+amount+",'"+dtf.format(now)+" (Failed) "+"')";
PreparedStatement stm1 = con.prepareStatement(q2);
int x1 = stm1.executeUpdate();
}
}

}
catch(Exception e){
out.print(e);
}
}
}