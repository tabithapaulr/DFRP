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


@WebServlet(name = "ReviewByState", urlPatterns = {"/ReviewByState"})
public class ReviewByState extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String state = request.getParameter("state");
            
            DBsingletone db = DBsingletone.getDbSingletone();
            Connection con = db.getConnection();
            PreparedStatement psmt = con.prepareStatement(" SELECT `Accident`,`Category`,`SubCategory` FROM `predictedresult` WHERE `State`='"+state+"' ORDER BY Accident DESC LIMIT 1 ");
            ResultSet rs = psmt.executeQuery();
            if(rs.next())
            {
                out.print(" Predicted more accidents reason is "+rs.getString("Category")+"("+rs.getString("SubCategory")+") ");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
