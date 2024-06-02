import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/Insert")
public class insertPharma extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
       response.setContentType("text/html");
         PrintWriter pw = response.getWriter();
         float bill1,d1;
         String mid1 = request.getParameter("mid1");
         int m1 = Integer.parseInt(mid1);         
         String mdname1 = request.getParameter("Medicine1");           
         String rate1 = request.getParameter("rate1");
         float rt1 = Float.parseFloat(rate1); 
         String qty1 = request.getParameter("qty1");
         int qt1 = Integer.parseInt(qty1); 
         int disc1=Integer.parseInt(request.getParameter("r1"));         
         bill1=rt1*qt1;
         d1=bill1*disc1/100;
         bill1-=d1;
         //------------------------------------
         float bill2,d2;
         String mid2 = request.getParameter("mid2");
         int m2 = Integer.parseInt(mid2);         
         String mdname2 = request.getParameter("Medicine2");           
         String rate2 = request.getParameter("rate2");
         float rt2 = Float.parseFloat(rate2); 
         String qty2 = request.getParameter("qty2");
         int qt2 = Integer.parseInt(qty2); 
         int disc2=Integer.parseInt(request.getParameter("r2"));         
         bill2=rt2*qt2;
         d2=bill2*disc2/100;
         bill2-=d2;
         //---------------------------------------
         float bill3,d3;
         String mid3 = request.getParameter("mid3");
         int m3 = Integer.parseInt(mid3);         
         String mdname3 = request.getParameter("Medicine3");           
         String rate3 = request.getParameter("rate3");
         float rt3 = Float.parseFloat(rate3); 
         String qty3 = request.getParameter("qty3");
         int qt3 = Integer.parseInt(qty3); 
         int disc3=Integer.parseInt(request.getParameter("r3"));         
         bill3=rt3*qt3;
         d3=bill3*disc3/100;
         bill3-=d3;
         //---------------------------------------
         float totbill=0.0f;
         totbill=bill1+bill2+bill3;
         try
         {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletdb","root","");
            
             PreparedStatement ps = con.prepareStatement("insert into pharmabill values (?,?,?,?,?,?,?)");
             ps.setInt(1, m1);
             ps.setString(2, mdname1);
             ps.setFloat(3, rt1);
             ps.setInt(4, qt1);
             ps.setFloat(5, d1);
             ps.setFloat(6, bill1);
             ps.setFloat(7, totbill);
             int i = ps.executeUpdate();
             pw.println("<h4>" + i + " ROWS INSERTED...");
             //---------------------------
             PreparedStatement ps1 = con.prepareStatement("insert into pharmabill values (?,?,?,?,?,?,?)");
             ps.setInt(1, m2);
             ps.setString(2, mdname2);
             ps.setFloat(3, rt2);
             ps.setInt(4, qt2);
             ps.setFloat(5, d2);
             ps.setFloat(6, bill2);
             ps.setFloat(7, totbill);
             int j = ps.executeUpdate();
             pw.println("<h4>" + j + " ROWS INSERTED...");
             //---------------------------------
             PreparedStatement ps2 = con.prepareStatement("insert into pharmabill values (?,?,?,?,?,?,?)");
             ps.setInt(1, m3);
             ps.setString(2, mdname3);
             ps.setFloat(3, rt3);
             ps.setInt(4, qt3);
             ps.setFloat(5, d3);
             ps.setFloat(6, bill3);
             ps.setFloat(7, totbill);
             
             int k = ps.executeUpdate();
             pw.println("<h4>" + k + " ROWS INSERTED...");
             //response.sendRedirect("viewServlet");
             con.close();
         } catch (Exception e) {
             response.sendError(503, "PROBLEM IN DATABASE...");
         }
    }

}
