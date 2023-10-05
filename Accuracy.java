package com.users;

import com.database.util.DBsingletone;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet(name = "Accuracy", urlPatterns = {"/Accuracy"})
public class Accuracy extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String ctype =request.getParameter("ctype");
            DBsingletone db = DBsingletone.getDbSingletone();
            Connection con = db.getConnection();
            JSONObject js = new JSONObject();
            
            if(ctype.equals("Alcohol"))
            {
                PreparedStatement ps = con.prepareStatement(" SELECT SUM(rsquare)/9 as yer,ctype FROM `analysis` WHERE ctype='Alcohol'");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("ctype"));
                    json1.put("y", (Double.parseDouble(rs.getString("yer"))));
                    jarray2.put(rs.getString("ctype"));
                    jarray.put(json1);
                }
                
                JSONObject json = new JSONObject();
                json.put("name", ctype);
                json.put("data", jarray);

                jarray1.put(json);
                js.put("aray1", jarray1);
                js.put("aray2", jarray2);
                
                out.print(js);
                                
            }
           else
            {
                PreparedStatement ps1 = con.prepareStatement(" select distinct csubtype from analysis where ctype='"+ctype+"'");
                ResultSet rs1 = ps1.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                while(rs1.next())
                {
                PreparedStatement ps = con.prepareStatement(" SELECT SUM(rsquare)/9 as yer,'"+rs1.getString("csubtype")+"' as csubtype FROM `analysis` WHERE ctype='"+ctype+"' and csubtype='"+rs1.getString("csubtype")+"' ");
                ResultSet rs = ps.executeQuery();
                 
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("csubtype"));
                    json1.put("y", (Double.parseDouble(rs.getString("yer"))));
                    jarray2.put(rs.getString("csubtype"));
                    jarray.put(json1);
                }
                }
                JSONObject json = new JSONObject();
                json.put("cat",jarray2);
                json.put("dat", jarray);

               
                
                out.print(json);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
