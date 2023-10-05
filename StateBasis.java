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


@WebServlet(name = "StateBasis", urlPatterns = {"/StateBasis"})
public class StateBasis extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String year = request.getParameter("year");
            String ctype = request.getParameter("ctype");
            String csubtype = request.getParameter("csubtype");
            
            
            DBsingletone db = DBsingletone.getDbSingletone();
            Connection con = db.getConnection();
            JSONObject js = new JSONObject();
            
            if(ctype.equals("Alcohol"))
            {
                PreparedStatement ps = con.prepareStatement(" SELECT `States`,`Y"+year+"` as yer FROM `alcoholbasedrecords` WHERE `States`!='All India' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    json1.put("y", Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    jarray.put(json1);
                }
                
                JSONObject json = new JSONObject();
                json.put("name", year+" - "+ctype);
                json.put("data", jarray);

                jarray1.put(json);
                js.put("aray1", jarray1);
                js.put("aray2", jarray2);
                
                out.print(js);
             }
            else if(ctype.equals("Climate"))
            {
                PreparedStatement ps = con.prepareStatement(" SELECT "+csubtype+" as yer,States FROM `climatebasedrecords` WHERE `Year`='"+year+"' and States!='All India' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    json1.put("y", Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    jarray.put(json1);
                }
                
                JSONObject json = new JSONObject();
                json.put("name", year+" - "+ctype+" - "+csubtype);
                json.put("data", jarray);

                jarray1.put(json);
                js.put("aray1", jarray1);
                js.put("aray2", jarray2);
                
                out.print(js);
            }
            else if(ctype.equals("Location"))
            {
                PreparedStatement ps = con.prepareStatement(" SELECT "+csubtype+" as yer,States FROM `locationbased` WHERE `Year`='"+year+"' and States!='All India' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    json1.put("y", Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    jarray.put(json1);
                }
                
                JSONObject json = new JSONObject();
                json.put("name", year+" - "+ctype+" - "+csubtype);
                json.put("data", jarray);

                jarray1.put(json);
                js.put("aray1", jarray1);
                js.put("aray2", jarray2);
                
                out.print(js);
            }
            else if(ctype.equals("Junction"))
            {
                PreparedStatement ps = con.prepareStatement(" SELECT "+csubtype+" as yer,States FROM `junctionbased` WHERE `Year`='"+year+"' and States!='Total' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    json1.put("y", Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    jarray.put(json1);
                }
                
                JSONObject json = new JSONObject();
                json.put("name", year+" - "+ctype+" - "+csubtype);
                json.put("data", jarray);

                jarray1.put(json);
                js.put("aray1", jarray1);
                js.put("aray2", jarray2);
                
                out.print(js);
            }
            else
            {
                PreparedStatement ps = con.prepareStatement(" SELECT "+csubtype+" as yer,States FROM `vehicledefect` WHERE `Year`='"+year+"' and States!='Total' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    json1.put("y", Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    jarray.put(json1);
                }
                
                JSONObject json = new JSONObject();
                json.put("name", year+" - "+ctype+" - "+csubtype);
                json.put("data", jarray);

                jarray1.put(json);
                js.put("aray1", jarray1);
                js.put("aray2", jarray2);
                
                out.print(js);
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
