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


@WebServlet(name = "analize", urlPatterns = {"/analize"})
public class analize extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String year = request.getParameter("year");
            String ctype = request.getParameter("ctype");
            String csubtype = request.getParameter("csubtype");
            
            System.out.println(year+" "+ctype+" "+csubtype);
            
            DBsingletone db = DBsingletone.getDbSingletone();
            Connection con = db.getConnection();
            JSONObject js = new JSONObject();
            JSONObject js1 = new JSONObject();
            
            if(ctype.equals("Alcohol"))
            {
                JSONArray jaray = new JSONArray();
                PreparedStatement ps = con.prepareStatement(" SELECT `States`,`Y2017` as yer FROM `alcoholbasedrecords` WHERE `States`!='All India' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                JSONArray jarray3 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    jarray1.put(Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    
                    PreparedStatement psmt = con.prepareStatement(" SELECT `Y2009`,`Y2010`,`Y2011`,`Y2012`,`Y2013`,`Y2014`,`Y2015`,`Y2016` FROM `alcoholbasedrecords` WHERE `States`='"+rs.getString("States")+"'  ");
                    ResultSet rsm = psmt.executeQuery();
                    if(rsm.next())
                    {
                        
                       double x[] = {1,2,3,4,5,6,7,8};
                       double y[] = {Float.parseFloat(rsm.getString("Y2009")),Float.parseFloat(rsm.getString("Y2010")),Float.parseFloat(rsm.getString("Y2011")),Float.parseFloat(rsm.getString("Y2012")),Float.parseFloat(rsm.getString("Y2013")),Float.parseFloat(rsm.getString("Y2014")),Float.parseFloat(rsm.getString("Y2015")),Float.parseFloat(rsm.getString("Y2016"))};
                    
                       LinearRegression li = new LinearRegression(x, y);
                       System.out.println("predict: "+li.predict1(9)+" "+rs.getString("States"));
                       jarray3.put(li.predict1(9));
                       
                    PreparedStatement pss1=con.prepareStatement("select * from analysis where ctype='Alcohol' and state='"+rs.getString("states")+"' and csubtype='' and Year='2017' ");
                    ResultSet rss1=pss1.executeQuery();
                    if(rss1.next())
                    {
                    }
                    else
                    {
                        PreparedStatement pss2=con.prepareStatement("insert into analysis(ctype,csubtype,Year,rsquare,state)values('Alcohol','','2017','"+li.R2()+"','"+rs.getString("States")+"')");
                        int k=pss2.executeUpdate();
                    }
                    
                }
                }
                
                JSONObject json = new JSONObject();
                json.put("name", "Actual");
                json.put("data", jarray1);

                JSONObject json1 = new JSONObject();
                json1.put("name", "Predicted");
                json1.put("data", jarray3);
                
                jarray.put(json);
                jarray.put(json1);
                
                js.put("cat", jarray2);
                js.put("dat", jarray);
                
                out.print(js);
                
            }
            else if(ctype.equals("Climate"))
            {
                PreparedStatement ps = con.prepareStatement(" SELECT "+csubtype+" as yer,States FROM `climatebasedrecords` WHERE `Year`='"+year+"' and States!='All India' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                JSONArray jarray3 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    jarray1.put(Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    
                    PreparedStatement psmt = con.prepareStatement(" SELECT (SELECT IFNULL(AVG("+csubtype+"),0) FROM climatebasedrecords WHERE `Year`='2009' and States='"+rs.getString("States")+"' ) AS Y2009,(SELECT IFNULL(AVG("+csubtype+"),0) FROM climatebasedrecords WHERE `Year`='2010' and States='"+rs.getString("States")+"') AS Y2010,(SELECT IFNULL(AVG("+csubtype+"),0) FROM climatebasedrecords WHERE `Year`='2011' and States='"+rs.getString("States")+"' ) AS Y2011,(SELECT IFNULL(AVG("+csubtype+"),0) FROM climatebasedrecords WHERE `Year`='2012' and States='"+rs.getString("States")+"' ) AS Y2012,(SELECT IFNULL(AVG("+csubtype+"),0) FROM climatebasedrecords WHERE `Year`='2013' and States='"+rs.getString("States")+"' ) AS Y2013,(SELECT IFNULL(AVG("+csubtype+"),0) FROM climatebasedrecords WHERE `Year`='2014' and States='"+rs.getString("States")+"' ) AS Y2014,(SELECT IFNULL(AVG("+csubtype+"),0) FROM climatebasedrecords WHERE `Year`='2015' and States='"+rs.getString("States")+"' ) AS Y2015,(SELECT IFNULL(AVG("+csubtype+"),0) FROM climatebasedrecords WHERE `Year`='2016' and States='"+rs.getString("States")+"' ) AS Y2016 ");
                    ResultSet rsm = psmt.executeQuery();
                    if(rsm.next())
                    {
                        
                       double x[] = {1,2,3,4,5,6,7,8};
                       double y[] = {Float.parseFloat(rsm.getString("Y2009")),Float.parseFloat(rsm.getString("Y2010")),Float.parseFloat(rsm.getString("Y2011")),Float.parseFloat(rsm.getString("Y2012")),Float.parseFloat(rsm.getString("Y2013")),Float.parseFloat(rsm.getString("Y2014")),Float.parseFloat(rsm.getString("Y2015")),Float.parseFloat(rsm.getString("Y2016"))};
                    
                       LinearReg li = new LinearReg(x, y);
                        System.out.println("predict: "+li.predict()+" "+rs.getString("States"));
                       jarray3.put(li.predict());
                       
                       
                        PreparedStatement pss1=con.prepareStatement("select * from analysis where ctype='Climate' and state='"+rs.getString("states")+"' and csubtype='"+csubtype+"' and Year='2017' ");
                    ResultSet rss1=pss1.executeQuery();
                    if(rss1.next())
                    {
                    }
                    else
                    {
                        PreparedStatement pss2=con.prepareStatement("insert into analysis(ctype,csubtype,Year,rsquare,state)values('Climate','"+csubtype+"','2017','"+li.R2()+"','"+rs.getString("States")+"')");
                        int k=pss2.executeUpdate();
                    }
                    }
                }
                
                JSONObject json = new JSONObject();
                json.put("name", "Actual");
                json.put("data", jarray1);

                JSONObject json1 = new JSONObject();
                json1.put("name", "Predicted");
                json1.put("data", jarray3);
                
                jarray.put(json);
                jarray.put(json1);
                
                js.put("cat", jarray2);
                js.put("dat", jarray);
                
                out.print(js);
            }
            else if(ctype.equals("Location"))
            {
                PreparedStatement ps = con.prepareStatement(" SELECT "+csubtype+" as yer,States FROM `locationbased` WHERE `Year`='"+year+"' and States!='All India' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                JSONArray jarray3 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    jarray1.put(Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    
                    PreparedStatement psmt = con.prepareStatement(" SELECT (SELECT AVG("+csubtype+") FROM locationbased WHERE `Year`='2009' and States='"+rs.getString("States")+"' ) AS Y2009,(SELECT AVG("+csubtype+") FROM locationbased WHERE `Year`='2010' and States='"+rs.getString("States")+"') AS Y2010,(SELECT AVG("+csubtype+") FROM locationbased WHERE `Year`='2011' and States='"+rs.getString("States")+"' ) AS Y2011,(SELECT AVG("+csubtype+") FROM locationbased WHERE `Year`='2012' and States='"+rs.getString("States")+"' ) AS Y2012,(SELECT AVG("+csubtype+") FROM locationbased WHERE `Year`='2013' and States='"+rs.getString("States")+"' ) AS Y2013,(SELECT AVG("+csubtype+") FROM locationbased WHERE `Year`='2014' and States='"+rs.getString("States")+"' ) AS Y2014,(SELECT AVG("+csubtype+") FROM locationbased WHERE `Year`='2015' and States='"+rs.getString("States")+"' ) AS Y2015,(SELECT AVG("+csubtype+") FROM locationbased WHERE `Year`='2016' and States='"+rs.getString("States")+"' ) AS Y2016 ");
                    ResultSet rsm = psmt.executeQuery();
                    if(rsm.next())
                    {
                        
                       double x[] = {1,2,3,4,5,6,7,8};
                       double y[] = {Float.parseFloat(rsm.getString("Y2009")),Float.parseFloat(rsm.getString("Y2010")),Float.parseFloat(rsm.getString("Y2011")),Float.parseFloat(rsm.getString("Y2012")),Float.parseFloat(rsm.getString("Y2013")),Float.parseFloat(rsm.getString("Y2014")),Float.parseFloat(rsm.getString("Y2015")),Float.parseFloat(rsm.getString("Y2016"))};
                    
                       LinearReg li = new LinearReg(x, y);
                       jarray3.put(li.predict());
                       
                    PreparedStatement pss1=con.prepareStatement("select * from analysis where ctype='Location' and state='"+rs.getString("states")+"' and csubtype='"+csubtype+"' and Year='2017' ");
                    ResultSet rss1=pss1.executeQuery();
                    if(rss1.next())
                    {
                    }
                    else
                    {
                        PreparedStatement pss2=con.prepareStatement("insert into analysis(ctype,csubtype,Year,rsquare,state)values('Location','"+csubtype+"','2017','"+li.R2()+"','"+rs.getString("States")+"')");
                        int k=pss2.executeUpdate();
                    }
                    }
                }
                
                JSONObject json = new JSONObject();
                json.put("name", "Actual");
                json.put("data", jarray1);

                JSONObject json1 = new JSONObject();
                json1.put("name", "Predicted");
                json1.put("data", jarray3);
                
                jarray.put(json);
                jarray.put(json1);
                
                js.put("cat", jarray2);
                js.put("dat", jarray);
                
                out.print(js);
            }
            else if(ctype.equals("Junction"))
            {
                PreparedStatement ps = con.prepareStatement(" SELECT "+csubtype+" as yer,States FROM `junctionbased` WHERE `Year`='"+year+"' and States!='Total' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                JSONArray jarray3 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    jarray1.put(Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    
                    PreparedStatement psmt = con.prepareStatement(" SELECT (SELECT AVG("+csubtype+") FROM junctionbased WHERE `Year`='2009' and States='"+rs.getString("States")+"' ) AS Y2009,(SELECT AVG("+csubtype+") FROM junctionbased WHERE `Year`='2010' and States='"+rs.getString("States")+"') AS Y2010,(SELECT AVG("+csubtype+") FROM junctionbased WHERE `Year`='2011' and States='"+rs.getString("States")+"' ) AS Y2011,(SELECT AVG("+csubtype+") FROM junctionbased WHERE `Year`='2012' and States='"+rs.getString("States")+"' ) AS Y2012,(SELECT AVG("+csubtype+") FROM junctionbased WHERE `Year`='2013' and States='"+rs.getString("States")+"' ) AS Y2013,(SELECT AVG("+csubtype+") FROM junctionbased WHERE `Year`='2014' and States='"+rs.getString("States")+"' ) AS Y2014,(SELECT AVG("+csubtype+") FROM junctionbased WHERE `Year`='2015' and States='"+rs.getString("States")+"' ) AS Y2015,(SELECT AVG("+csubtype+") FROM junctionbased WHERE `Year`='2016' and States='"+rs.getString("States")+"' ) AS Y2016 ");
                    ResultSet rsm = psmt.executeQuery();
                    if(rsm.next())
                    {
                        
                       double x[] = {1,2,3,4,5,6,7,8};
                       double y[] = {Float.parseFloat(rsm.getString("Y2009")),Float.parseFloat(rsm.getString("Y2010")),Float.parseFloat(rsm.getString("Y2011")),Float.parseFloat(rsm.getString("Y2012")),Float.parseFloat(rsm.getString("Y2013")),Float.parseFloat(rsm.getString("Y2014")),Float.parseFloat(rsm.getString("Y2015")),Float.parseFloat(rsm.getString("Y2016"))};
                    
                       LinearReg li = new LinearReg(x, y);
                       jarray3.put(li.predict());
                       
                        PreparedStatement pss1=con.prepareStatement("select * from analysis where ctype='Junction' and state='"+rs.getString("states")+"' and csubtype='"+csubtype+"' and Year='2017' ");
                    ResultSet rss1=pss1.executeQuery();
                    if(rss1.next())
                    {
                    }
                    else
                    {
                        PreparedStatement pss2=con.prepareStatement("insert into analysis(ctype,csubtype,Year,rsquare,state)values('Junction','"+csubtype+"','2017','"+li.R2()+"','"+rs.getString("States")+"')");
                        int k=pss2.executeUpdate();
                    }
                    }
                }
                
                JSONObject json = new JSONObject();
                json.put("name", "Actual");
                json.put("data", jarray1);

                JSONObject json1 = new JSONObject();
                json1.put("name", "Predicted");
                json1.put("data", jarray3);
                
                jarray.put(json);
                jarray.put(json1);
                
                js.put("cat", jarray2);
                js.put("dat", jarray);
                
                out.print(js);
            }
            else
            {
                PreparedStatement ps = con.prepareStatement(" SELECT "+csubtype+" as yer,States FROM `vehicledefect` WHERE `Year`='"+year+"' and States!='Total' ");
                ResultSet rs = ps.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                JSONArray jarray2 = new JSONArray();
                JSONArray jarray3 = new JSONArray();
                while(rs.next())
                {
                    JSONObject json1 = new JSONObject();
                    json1.put("name", rs.getString("States"));
                    jarray1.put(Integer.parseInt(rs.getString("yer")));
                    jarray2.put(rs.getString("States"));
                    
                    PreparedStatement psmt = con.prepareStatement(" SELECT (SELECT AVG("+csubtype+") FROM vehicledefect WHERE `Year`='2009' and States='"+rs.getString("States")+"' ) AS Y2009,(SELECT AVG("+csubtype+") FROM vehicledefect WHERE `Year`='2010' and States='"+rs.getString("States")+"') AS Y2010,(SELECT AVG("+csubtype+") FROM vehicledefect WHERE `Year`='2011' and States='"+rs.getString("States")+"' ) AS Y2011,(SELECT AVG("+csubtype+") FROM vehicledefect WHERE `Year`='2012' and States='"+rs.getString("States")+"' ) AS Y2012,(SELECT AVG("+csubtype+") FROM vehicledefect WHERE `Year`='2013' and States='"+rs.getString("States")+"' ) AS Y2013,(SELECT AVG("+csubtype+") FROM vehicledefect WHERE `Year`='2014' and States='"+rs.getString("States")+"' ) AS Y2014,(SELECT AVG("+csubtype+") FROM vehicledefect WHERE `Year`='2015' and States='"+rs.getString("States")+"' ) AS Y2015,(SELECT AVG("+csubtype+") FROM vehicledefect WHERE `Year`='2016' and States='"+rs.getString("States")+"' ) AS Y2016 ");
                    ResultSet rsm = psmt.executeQuery();
                    if(rsm.next())
                    {
                        
                       double x[] = {1,2,3,4,5,6,7,8};
                       double y[] = {Float.parseFloat(rsm.getString("Y2009")),Float.parseFloat(rsm.getString("Y2010")),Float.parseFloat(rsm.getString("Y2011")),Float.parseFloat(rsm.getString("Y2012")),Float.parseFloat(rsm.getString("Y2013")),Float.parseFloat(rsm.getString("Y2014")),Float.parseFloat(rsm.getString("Y2015")),Float.parseFloat(rsm.getString("Y2016"))};
                    
                       LinearReg li = new LinearReg(x, y);
                       jarray3.put(li.predict());
                       
                       PreparedStatement pss1=con.prepareStatement("select * from analysis where ctype='VehicleDefect' and state='"+rs.getString("states")+"' and csubtype='"+csubtype+"' and Year='2017' ");
                    ResultSet rss1=pss1.executeQuery();
                    if(rss1.next())
                    {
                    }
                    else
                    {
                        PreparedStatement pss2=con.prepareStatement("insert into analysis(ctype,csubtype,Year,rsquare,state)values('VehicleDefect','"+csubtype+"','2017','"+li.R2()+"','"+rs.getString("States")+"')");
                        int k=pss2.executeUpdate();
                    }
                    }
                    
                }
                
                JSONObject json = new JSONObject();
                json.put("name", "Actual");
                json.put("data", jarray1);

                JSONObject json1 = new JSONObject();
                json1.put("name", "Predicted");
                json1.put("data", jarray3);
                
                jarray.put(json);
                jarray.put(json1);
                
                js.put("cat", jarray2);
                js.put("dat", jarray);
                
                System.out.println("data: "+js);
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
    }

}
