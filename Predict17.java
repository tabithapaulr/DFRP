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


@WebServlet(name = "predict17", urlPatterns = {"/predict17"})
public class Predict17 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String state = request.getParameter("state");
            String ctype = request.getParameter("ctype");

            DBsingletone db = DBsingletone.getDbSingletone();
            Connection con = db.getConnection();
            
            String ratio = "";
            
            if (ctype.equals("Alcohol")) {
                PreparedStatement psm = con.prepareStatement(" SELECT * FROM `alcoholbasedrecords` WHERE `States`='" + state + "' ");
                ResultSet rs = psm.executeQuery();
                JSONArray jarray = new JSONArray();
                JSONArray jarray1 = new JSONArray();
                if (rs.next()) {
                    JSONObject json1 = new JSONObject();
                    JSONObject json2 = new JSONObject();
                    JSONObject json3 = new JSONObject();
                    JSONObject json4 = new JSONObject();
                    JSONObject json5 = new JSONObject();
                    JSONObject json6 = new JSONObject();
                    JSONObject json7 = new JSONObject();
                    JSONObject json8 = new JSONObject();
                    JSONObject json9 = new JSONObject();
                    //JSONObject json10 = new JSONObject();
                    JSONObject json11 = new JSONObject();

                    json1.put("name", "2008");
                    json1.put("y", Float.parseFloat(rs.getString("Y2008")));

                    json2.put("name", "2009");
                    json2.put("y", Float.parseFloat(rs.getString("Y2009")));

                    json3.put("name", "2010");
                    json3.put("y", Float.parseFloat(rs.getString("Y2010")));

                    json4.put("name", "2011");
                    json4.put("y", Float.parseFloat(rs.getString("Y2011")));

                    json5.put("name", "2012");
                    json5.put("y", Float.parseFloat(rs.getString("Y2012")));

                    json6.put("name", "2013");
                    json6.put("y", Float.parseFloat(rs.getString("Y2013")));

                    json7.put("name", "2014");
                    json7.put("y", Float.parseFloat(rs.getString("Y2014")));

                    json8.put("name", "2015");
                    json8.put("y", Float.parseFloat(rs.getString("Y2015")));

                    json9.put("name", "2016");
                    json9.put("y", Float.parseFloat(rs.getString("Y2016")));

                    //json10.put("name", "2017");
                    //json10.put("y", Float.parseFloat(rs.getString("Y2017")));

                    float data1, data2, data3, data4, data5, data6, data7, data8, data9;

                    data1 = Float.parseFloat(rs.getString("Y2008"));
                    data2 = Float.parseFloat(rs.getString("Y2009"));
                    data3 = Float.parseFloat(rs.getString("Y2010"));
                    data4 = Float.parseFloat(rs.getString("Y2011"));
                    data5 = Float.parseFloat(rs.getString("Y2012"));
                    data6 = Float.parseFloat(rs.getString("Y2013"));
                    data7 = Float.parseFloat(rs.getString("Y2014"));
                    data8 = Float.parseFloat(rs.getString("Y2015"));
                    data9 = Float.parseFloat(rs.getString("Y2016"));
                    //data10 = Float.parseFloat(rs.getString("Y2017"));

                    double x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                    double y[] = {data1, data2, data3, data4, data5, data6, data7, data8, data9};

                    LinearRegression lr = new LinearRegression(x, y);
                    json11.put("name", "2017");
                    json11.put("y", lr.predict(10));
                    
                    PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Alcohol' and State='"+state+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `climatebasedrecords` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,PYear,Accident,State) values('Alcohol','2017','"+lr.predict(10)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }

                    jarray.put(json1);
                    jarray.put(json2);
                    jarray.put(json3);
                    jarray.put(json4);
                    jarray.put(json5);
                    jarray.put(json6);
                    jarray.put(json7);
                    jarray.put(json8);
                    jarray.put(json9);
                    //jarray.put(json10);
                    jarray.put(json11);

                }

                JSONObject json = new JSONObject();
                json.put("name", state);
                json.put("data", jarray);

                jarray1.put(json);

                out.print(jarray1);
            } else if (ctype.equals("Climate")) {
                JSONArray jaray = new JSONArray();
                try {
                    PreparedStatement psm = con.prepareStatement(" SELECT (SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `Fog` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs = psm.executeQuery();
                    JSONArray jarray = new JSONArray();
                    if (rs.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                      //  JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9;

                        data2 = Float.parseFloat(rs.getString("Y2009"));
                        data3 = Float.parseFloat(rs.getString("Y2010"));
                        data4 = Float.parseFloat(rs.getString("Y2011"));
                        data5 = Float.parseFloat(rs.getString("Y2012"));
                        data6 = Float.parseFloat(rs.getString("Y2013"));
                        data7 = Float.parseFloat(rs.getString("Y2014"));
                        data8 = Float.parseFloat(rs.getString("Y2015"));
                        data9 = Float.parseFloat(rs.getString("Y2016"));
                        //data10 = Float.parseFloat(rs.getString("Y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Climate' and SubCategory='Fog' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `climatebasedrecords` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Climate','Fog','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }

                        jarray.put(json2);
                        jarray.put(json3);
                        jarray.put(json4);
                        jarray.put(json5);
                        jarray.put(json6);
                        jarray.put(json7);
                        jarray.put(json8);
                        jarray.put(json9);
                        //jarray.put(json10);
                        jarray.put(json11);

                    }

                    PreparedStatement psm1 = con.prepareStatement(" SELECT (SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `Cloud` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs1 = psm1.executeQuery();
                    JSONArray jarray1 = new JSONArray();
                    if (rs1.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs1.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs1.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs1.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs1.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs1.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs1.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs1.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs1.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs1.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9;

                        data2 = Float.parseFloat(rs1.getString("Y2009"));
                        data3 = Float.parseFloat(rs1.getString("Y2010"));
                        data4 = Float.parseFloat(rs1.getString("Y2011"));
                        data5 = Float.parseFloat(rs1.getString("Y2012"));
                        data6 = Float.parseFloat(rs1.getString("Y2013"));
                        data7 = Float.parseFloat(rs1.getString("Y2014"));
                        data8 = Float.parseFloat(rs1.getString("Y2015"));
                        data9 = Float.parseFloat(rs1.getString("Y2016"));
                        //data10 = Float.parseFloat(rs1.getString("Y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }

                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Climate' and SubCategory='Cloud' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `climatebasedrecords` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Climate','Cloud','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray1.put(json2);
                        jarray1.put(json3);
                        jarray1.put(json4);
                        jarray1.put(json5);
                        jarray1.put(json6);
                        jarray1.put(json7);
                        jarray1.put(json8);
                        jarray1.put(json9);
                        //jarray1.put(json10);
                        jarray1.put(json11);

                    }

                    PreparedStatement psm2 = con.prepareStatement(" SELECT (SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `HeavyRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs2 = psm2.executeQuery();
                    JSONArray jarray2 = new JSONArray();
                    if (rs2.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs2.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs2.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs2.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs2.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs2.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs2.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs2.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs2.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs2.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs2.getString("y2009"));
                        data3 = Float.parseFloat(rs2.getString("y2010"));
                        data4 = Float.parseFloat(rs2.getString("y2011"));
                        data5 = Float.parseFloat(rs2.getString("y2012"));
                        data6 = Float.parseFloat(rs2.getString("y2013"));
                        data7 = Float.parseFloat(rs2.getString("y2014"));
                        data8 = Float.parseFloat(rs2.getString("y2015"));
                        data9 = Float.parseFloat(rs2.getString("y2016"));
                        //data10 = Float.parseFloat(rs2.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }

                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Climate' and SubCategory='HeavyRain' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `climatebasedrecords` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Climate','HeavyRain','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray2.put(json2);
                        jarray2.put(json3);
                        jarray2.put(json4);
                        jarray2.put(json5);
                        jarray2.put(json6);
                        jarray2.put(json7);
                        jarray2.put(json8);
                        jarray2.put(json9);
                        //jarray2.put(json10);
                        jarray2.put(json11);

                    }

                    PreparedStatement psm3 = con.prepareStatement(" SELECT (SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `LightRain` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs3 = psm3.executeQuery();
                    JSONArray jarray3 = new JSONArray();
                    if (rs3.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs3.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs3.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs3.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs3.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs3.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs3.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs3.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs3.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs3.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs3.getString("y2009"));
                        data3 = Float.parseFloat(rs3.getString("y2010"));
                        data4 = Float.parseFloat(rs3.getString("y2011"));
                        data5 = Float.parseFloat(rs3.getString("y2012"));
                        data6 = Float.parseFloat(rs3.getString("y2013"));
                        data7 = Float.parseFloat(rs3.getString("y2014"));
                        data8 = Float.parseFloat(rs3.getString("y2015"));
                        data9 = Float.parseFloat(rs3.getString("y2016"));
                        //data10 = Float.parseFloat(rs3.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Climate' and SubCategory='LightRain' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `climatebasedrecords` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Climate','LightRain','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        
                        jarray3.put(json2);
                        jarray3.put(json3);
                        jarray3.put(json4);
                        jarray3.put(json5);
                        jarray3.put(json6);
                        jarray3.put(json7);
                        jarray3.put(json8);
                        jarray3.put(json9);
                        //jarray3.put(json10);
                        jarray3.put(json11);

                    }

                    PreparedStatement psm4 = con.prepareStatement(" SELECT (SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `Snow` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs4 = psm4.executeQuery();
                    JSONArray jarray4 = new JSONArray();
                    if (rs4.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs4.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs4.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs4.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs4.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs4.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs4.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs4.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs4.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs4.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs4.getString("y2009"));
                        data3 = Float.parseFloat(rs4.getString("y2010"));
                        data4 = Float.parseFloat(rs4.getString("y2011"));
                        data5 = Float.parseFloat(rs4.getString("y2012"));
                        data6 = Float.parseFloat(rs4.getString("y2013"));
                        data7 = Float.parseFloat(rs4.getString("y2014"));
                        data8 = Float.parseFloat(rs4.getString("y2015"));
                        data9 = Float.parseFloat(rs4.getString("y2016"));
                        //data10 = Float.parseFloat(rs4.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Climate' and SubCategory='Snow' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `climatebasedrecords` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Climate','Snow','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        

                        jarray4.put(json2);
                        jarray4.put(json3);
                        jarray4.put(json4);
                        jarray4.put(json5);
                        jarray4.put(json6);
                        jarray4.put(json7);
                        jarray4.put(json8);
                        jarray4.put(json9);
                        //jarray4.put(json10);
                        jarray4.put(json11);

                    }

                    PreparedStatement psm5 = con.prepareStatement(" SELECT (SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `Hot` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs5 = psm5.executeQuery();
                    JSONArray jarray5 = new JSONArray();
                    if (rs5.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs5.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs5.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs5.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs5.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs5.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs5.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs5.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs5.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs5.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs5.getString("y2009"));
                        data3 = Float.parseFloat(rs5.getString("y2010"));
                        data4 = Float.parseFloat(rs5.getString("y2011"));
                        data5 = Float.parseFloat(rs5.getString("y2012"));
                        data6 = Float.parseFloat(rs5.getString("y2013"));
                        data7 = Float.parseFloat(rs5.getString("y2014"));
                        data8 = Float.parseFloat(rs5.getString("y2015"));
                        data9 = Float.parseFloat(rs5.getString("y2016"));
                        //data10 = Float.parseFloat(rs5.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Climate' and SubCategory='Hot' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `climatebasedrecords` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Climate','Hot','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }

                        jarray5.put(json2);
                        jarray5.put(json3);
                        jarray5.put(json4);
                        jarray5.put(json5);
                        jarray5.put(json6);
                        jarray5.put(json7);
                        jarray5.put(json8);
                        jarray5.put(json9);
                        //jarray5.put(json10);
                        jarray5.put(json11);

                    }

                    PreparedStatement psm6 = con.prepareStatement(" SELECT (SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `Cold` FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs6 = psm6.executeQuery();
                    JSONArray jarray6 = new JSONArray();
                    if (rs6.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs6.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs6.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs6.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs6.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs6.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs6.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs6.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs6.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs6.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs6.getString("y2009"));
                        data3 = Float.parseFloat(rs6.getString("y2010"));
                        data4 = Float.parseFloat(rs6.getString("y2011"));
                        data5 = Float.parseFloat(rs6.getString("y2012"));
                        data6 = Float.parseFloat(rs6.getString("y2013"));
                        data7 = Float.parseFloat(rs6.getString("y2014"));
                        data8 = Float.parseFloat(rs6.getString("y2015"));
                        data9 = Float.parseFloat(rs6.getString("y2016"));
                        //data10 = Float.parseFloat(rs6.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Climate' and SubCategory='Cold' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `climatebasedrecords` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Climate','Cold','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        
                        jarray6.put(json2);
                        jarray6.put(json3);
                        jarray6.put(json4);
                        jarray6.put(json5);
                        jarray6.put(json6);
                        jarray6.put(json7);
                        jarray6.put(json8);
                        jarray6.put(json9);
                        //jarray6.put(json10);
                        jarray6.put(json11);

                    }

                    JSONObject json = new JSONObject();
                    json.put("name", "Fog");
                    json.put("data", jarray);

                    JSONObject json1 = new JSONObject();
                    json1.put("name", "Cloud");
                    json1.put("data", jarray1);

                    JSONObject json2 = new JSONObject();
                    json2.put("name", "HeavyRain");
                    json2.put("data", jarray2);

                    JSONObject json3 = new JSONObject();
                    json3.put("name", "LightRain");
                    json3.put("data", jarray3);

                    JSONObject json4 = new JSONObject();
                    json4.put("name", "Snow");
                    json4.put("data", jarray4);

                    JSONObject json5 = new JSONObject();
                    json5.put("name", "Hot");
                    json5.put("data", jarray5);

                    JSONObject json6 = new JSONObject();
                    json6.put("name", "Cold");
                    json6.put("data", jarray6);

                    jaray.put(json);
                    jaray.put(json1);
                    jaray.put(json2);
                    jaray.put(json3);
                    jaray.put(json4);
                    jaray.put(json5);
                    jaray.put(json6);

                    out.print(jaray);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else if (ctype.equals("Location")) {
                JSONArray jaray = new JSONArray();
                try {
                    PreparedStatement psm = con.prepareStatement(" SELECT (SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `NearSchool` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs = psm.executeQuery();
                    JSONArray jarray = new JSONArray();
                    if (rs.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs.getString("Y2009"));
                        data3 = Float.parseFloat(rs.getString("Y2010"));
                        data4 = Float.parseFloat(rs.getString("Y2011"));
                        data5 = Float.parseFloat(rs.getString("Y2012"));
                        data6 = Float.parseFloat(rs.getString("Y2013"));
                        data7 = Float.parseFloat(rs.getString("Y2014"));
                        data8 = Float.parseFloat(rs.getString("Y2015"));
                        data9 = Float.parseFloat(rs.getString("Y2016"));
                        //data10 = Float.parseFloat(rs.getString("Y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Location' and SubCategory='NearSchool' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Location','NearSchool','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                           
                        jarray.put(json2);
                        jarray.put(json3);
                        jarray.put(json4);
                        jarray.put(json5);
                        jarray.put(json6);
                        jarray.put(json7);
                        jarray.put(json8);
                        jarray.put(json9);
                        //jarray.put(json10);
                        jarray.put(json11);

                    }

                    PreparedStatement psm1 = con.prepareStatement(" SELECT (SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `NearFactory` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs1 = psm1.executeQuery();
                    JSONArray jarray1 = new JSONArray();
                    if (rs1.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs1.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs1.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs1.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs1.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs1.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs1.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs1.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs1.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs1.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs1.getString("Y2009"));
                        data3 = Float.parseFloat(rs1.getString("Y2010"));
                        data4 = Float.parseFloat(rs1.getString("Y2011"));
                        data5 = Float.parseFloat(rs1.getString("Y2012"));
                        data6 = Float.parseFloat(rs1.getString("Y2013"));
                        data7 = Float.parseFloat(rs1.getString("Y2014"));
                        data8 = Float.parseFloat(rs1.getString("Y2015"));
                        data9 = Float.parseFloat(rs1.getString("Y2016"));
                        //data10 = Float.parseFloat(rs1.getString("Y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Location' and SubCategory='NearFactory' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Location','NearFactory','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray1.put(json2);
                        jarray1.put(json3);
                        jarray1.put(json4);
                        jarray1.put(json5);
                        jarray1.put(json6);
                        jarray1.put(json7);
                        jarray1.put(json8);
                        jarray1.put(json9);
                        //jarray1.put(json10);
                        jarray1.put(json11);

                    }

                    PreparedStatement psm2 = con.prepareStatement(" SELECT (SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `NearHospital` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs2 = psm2.executeQuery();
                    JSONArray jarray2 = new JSONArray();
                    if (rs2.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs2.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs2.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs2.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs2.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs2.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs2.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs2.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs2.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs2.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs2.getString("y2009"));
                        data3 = Float.parseFloat(rs2.getString("y2010"));
                        data4 = Float.parseFloat(rs2.getString("y2011"));
                        data5 = Float.parseFloat(rs2.getString("y2012"));
                        data6 = Float.parseFloat(rs2.getString("y2013"));
                        data7 = Float.parseFloat(rs2.getString("y2014"));
                        data8 = Float.parseFloat(rs2.getString("y2015"));
                        data9 = Float.parseFloat(rs2.getString("y2016"));
                        //data10 = Float.parseFloat(rs2.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Location' and SubCategory='NearHospital' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Location','NearHospital','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }

                        jarray2.put(json2);
                        jarray2.put(json3);
                        jarray2.put(json4);
                        jarray2.put(json5);
                        jarray2.put(json6);
                        jarray2.put(json7);
                        jarray2.put(json8);
                        jarray2.put(json9);
                        //jarray2.put(json10);
                        jarray2.put(json11);

                    }

                    PreparedStatement psm3 = con.prepareStatement(" SELECT (SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `NearBusStop` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs3 = psm3.executeQuery();
                    JSONArray jarray3 = new JSONArray();
                    if (rs3.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs3.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs3.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs3.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs3.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs3.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs3.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs3.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs3.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs3.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs3.getString("y2009"));
                        data3 = Float.parseFloat(rs3.getString("y2010"));
                        data4 = Float.parseFloat(rs3.getString("y2011"));
                        data5 = Float.parseFloat(rs3.getString("y2012"));
                        data6 = Float.parseFloat(rs3.getString("y2013"));
                        data7 = Float.parseFloat(rs3.getString("y2014"));
                        data8 = Float.parseFloat(rs3.getString("y2015"));
                        data9 = Float.parseFloat(rs3.getString("y2016"));
                        //data10 = Float.parseFloat(rs3.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Location' and SubCategory='NearBazaar' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Location','NearBazaar','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                         
                        
                        jarray3.put(json2);
                        jarray3.put(json3);
                        jarray3.put(json4);
                        jarray3.put(json5);
                        jarray3.put(json6);
                        jarray3.put(json7);
                        jarray3.put(json8);
                        jarray3.put(json9);
                        //jarray3.put(json10);
                        jarray3.put(json11);

                    }

                    PreparedStatement psm4 = con.prepareStatement(" SELECT (SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `NearBazaar` FROM `locationbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs4 = psm4.executeQuery();
                    JSONArray jarray4 = new JSONArray();
                    if (rs4.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs4.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs4.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs4.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs4.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs4.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs4.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs4.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs4.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs4.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs4.getString("y2009"));
                        data3 = Float.parseFloat(rs4.getString("y2010"));
                        data4 = Float.parseFloat(rs4.getString("y2011"));
                        data5 = Float.parseFloat(rs4.getString("y2012"));
                        data6 = Float.parseFloat(rs4.getString("y2013"));
                        data7 = Float.parseFloat(rs4.getString("y2014"));
                        data8 = Float.parseFloat(rs4.getString("y2015"));
                        data9 = Float.parseFloat(rs4.getString("y2016"));
                        //data10 = Float.parseFloat(rs4.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Location' and SubCategory='NearBusStop' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Location','NearBusStop','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray4.put(json2);
                        jarray4.put(json3);
                        jarray4.put(json4);
                        jarray4.put(json5);
                        jarray4.put(json6);
                        jarray4.put(json7);
                        jarray4.put(json8);
                        jarray4.put(json9);
                        //jarray4.put(json10);
                        jarray4.put(json11);

                    }

                    JSONObject json = new JSONObject();
                    json.put("name", "NearSchool");
                    json.put("data", jarray);

                    JSONObject json1 = new JSONObject();
                    json1.put("name", "NearFactory");
                    json1.put("data", jarray1);

                    JSONObject json2 = new JSONObject();
                    json2.put("name", "NearHospital");
                    json2.put("data", jarray2);

                    JSONObject json3 = new JSONObject();
                    json3.put("name", "NearBusStop");
                    json3.put("data", jarray3);

                    JSONObject json4 = new JSONObject();
                    json4.put("name", "NearBazaar");
                    json4.put("data", jarray4);


                    jaray.put(json);
                    jaray.put(json1);
                    jaray.put(json2);
                    jaray.put(json3);
                    jaray.put(json4);

                    System.out.println("data: "+jaray);
                    
                    out.print(jaray);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else if (ctype.equals("VehicleDefect")) {
                JSONArray jaray = new JSONArray();
                try {
                    PreparedStatement psm = con.prepareStatement(" SELECT (SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `DefectiveBrakes` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs = psm.executeQuery();
                    JSONArray jarray = new JSONArray();
                    if (rs.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs.getString("Y2009"));
                        data3 = Float.parseFloat(rs.getString("Y2010"));
                        data4 = Float.parseFloat(rs.getString("Y2011"));
                        data5 = Float.parseFloat(rs.getString("Y2012"));
                        data6 = Float.parseFloat(rs.getString("Y2013"));
                        data7 = Float.parseFloat(rs.getString("Y2014"));
                        data8 = Float.parseFloat(rs.getString("Y2015"));
                        data9 = Float.parseFloat(rs.getString("Y2016"));
                        //data10 = Float.parseFloat(rs.getString("Y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='VehicleDefect' and SubCategory='DefectiveBrakes' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('VehicleDefect','DefectiveBrakes','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }

                        jarray.put(json2);
                        jarray.put(json3);
                        jarray.put(json4);
                        jarray.put(json5);
                        jarray.put(json6);
                        jarray.put(json7);
                        jarray.put(json8);
                        jarray.put(json9);
                        //jarray.put(json10);
                        jarray.put(json11);

                    }

                    PreparedStatement psm1 = con.prepareStatement(" SELECT (SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `Punctured` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs1 = psm1.executeQuery();
                    JSONArray jarray1 = new JSONArray();
                    if (rs1.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs1.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs1.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs1.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs1.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs1.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs1.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs1.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs1.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs1.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs1.getString("Y2009"));
                        data3 = Float.parseFloat(rs1.getString("Y2010"));
                        data4 = Float.parseFloat(rs1.getString("Y2011"));
                        data5 = Float.parseFloat(rs1.getString("Y2012"));
                        data6 = Float.parseFloat(rs1.getString("Y2013"));
                        data7 = Float.parseFloat(rs1.getString("Y2014"));
                        data8 = Float.parseFloat(rs1.getString("Y2015"));
                        data9 = Float.parseFloat(rs1.getString("Y2016"));
                        //data10 = Float.parseFloat(rs1.getString("Y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='VehicleDefect' and SubCategory='Punctured' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('VehicleDefect','Punctured','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray1.put(json2);
                        jarray1.put(json3);
                        jarray1.put(json4);
                        jarray1.put(json5);
                        jarray1.put(json6);
                        jarray1.put(json7);
                        jarray1.put(json8);
                        jarray1.put(json9);
                        //jarray1.put(json10);
                        jarray1.put(json11);

                    }

                    PreparedStatement psm2 = con.prepareStatement(" SELECT (SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `BaldTyres` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs2 = psm2.executeQuery();
                    JSONArray jarray2 = new JSONArray();
                    if (rs2.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs2.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs2.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs2.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs2.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs2.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs2.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs2.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs2.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs2.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs2.getString("y2009"));
                        data3 = Float.parseFloat(rs2.getString("y2010"));
                        data4 = Float.parseFloat(rs2.getString("y2011"));
                        data5 = Float.parseFloat(rs2.getString("y2012"));
                        data6 = Float.parseFloat(rs2.getString("y2013"));
                        data7 = Float.parseFloat(rs2.getString("y2014"));
                        data8 = Float.parseFloat(rs2.getString("y2015"));
                        data9 = Float.parseFloat(rs2.getString("y2016"));
                        //data10 = Float.parseFloat(rs2.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='VehicleDefect' and SubCategory='BaldTyres' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('VehicleDefect','BaldTyres','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray2.put(json2);
                        jarray2.put(json3);
                        jarray2.put(json4);
                        jarray2.put(json5);
                        jarray2.put(json6);
                        jarray2.put(json7);
                        jarray2.put(json8);
                        jarray2.put(json9);
                        //jarray2.put(json10);
                        jarray2.put(json11);

                    }

                    PreparedStatement psm3 = con.prepareStatement(" SELECT (SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `Others` FROM `vehicledefect` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs3 = psm3.executeQuery();
                    JSONArray jarray3 = new JSONArray();
                    if (rs3.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs3.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs3.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs3.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs3.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs3.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs3.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs3.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs3.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs3.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs3.getString("y2009"));
                        data3 = Float.parseFloat(rs3.getString("y2010"));
                        data4 = Float.parseFloat(rs3.getString("y2011"));
                        data5 = Float.parseFloat(rs3.getString("y2012"));
                        data6 = Float.parseFloat(rs3.getString("y2013"));
                        data7 = Float.parseFloat(rs3.getString("y2014"));
                        data8 = Float.parseFloat(rs3.getString("y2015"));
                        data9 = Float.parseFloat(rs3.getString("y2016"));
                        //data10 = Float.parseFloat(rs3.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='VehicleDefect' and SubCategory='Others' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('VehicleDefect','Others','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray3.put(json2);
                        jarray3.put(json3);
                        jarray3.put(json4);
                        jarray3.put(json5);
                        jarray3.put(json6);
                        jarray3.put(json7);
                        jarray3.put(json8);
                        jarray3.put(json9);
                        //jarray3.put(json10);
                        jarray3.put(json11);

                    }

                    JSONObject json = new JSONObject();
                    json.put("name", "DefectiveBrakes");
                    json.put("data", jarray);

                    JSONObject json1 = new JSONObject();
                    json1.put("name", "Punctured");
                    json1.put("data", jarray1);

                    JSONObject json2 = new JSONObject();
                    json2.put("name", "BaldTyres");
                    json2.put("data", jarray2);

                    JSONObject json3 = new JSONObject();
                    json3.put("name", "Others");
                    json3.put("data", jarray3);

                    jaray.put(json);
                    jaray.put(json1);
                    jaray.put(json2);
                    jaray.put(json3);

                    System.out.println("data: "+jaray);
                    
                    out.print(jaray);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else  {
                JSONArray jaray = new JSONArray();
                try {
                    PreparedStatement psm = con.prepareStatement(" SELECT (SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `TJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs = psm.executeQuery();
                    JSONArray jarray = new JSONArray();
                    if (rs.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs.getString("Y2009"));
                        data3 = Float.parseFloat(rs.getString("Y2010"));
                        data4 = Float.parseFloat(rs.getString("Y2011"));
                        data5 = Float.parseFloat(rs.getString("Y2012"));
                        data6 = Float.parseFloat(rs.getString("Y2013"));
                        data7 = Float.parseFloat(rs.getString("Y2014"));
                        data8 = Float.parseFloat(rs.getString("Y2015"));
                        data9 = Float.parseFloat(rs.getString("Y2016"));
                        //data10 = Float.parseFloat(rs.getString("Y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Junction' and SubCategory='TJunction' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Junction','TJunction','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray.put(json2);
                        jarray.put(json3);
                        jarray.put(json4);
                        jarray.put(json5);
                        jarray.put(json6);
                        jarray.put(json7);
                        jarray.put(json8);
                        jarray.put(json9);
                        //jarray.put(json10);
                        jarray.put(json11);

                    }

                    PreparedStatement psm1 = con.prepareStatement(" SELECT (SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `YJunction` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs1 = psm1.executeQuery();
                    JSONArray jarray1 = new JSONArray();
                    if (rs1.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        //JSONObject json10 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs1.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs1.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs1.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs1.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs1.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs1.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs1.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs1.getString("y2016")));

                        //json10.put("name", "2017");
                        //json10.put("y", Float.parseFloat(rs1.getString("y2017")));

                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs1.getString("Y2009"));
                        data3 = Float.parseFloat(rs1.getString("Y2010"));
                        data4 = Float.parseFloat(rs1.getString("Y2011"));
                        data5 = Float.parseFloat(rs1.getString("Y2012"));
                        data6 = Float.parseFloat(rs1.getString("Y2013"));
                        data7 = Float.parseFloat(rs1.getString("Y2014"));
                        data8 = Float.parseFloat(rs1.getString("Y2015"));
                        data9 = Float.parseFloat(rs1.getString("Y2016"));
                        //data10 = Float.parseFloat(rs1.getString("Y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                        
                        PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Junction' and SubCategory='YJunction' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Junction','YJunction','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray1.put(json2);
                        jarray1.put(json3);
                        jarray1.put(json4);
                        jarray1.put(json5);
                        jarray1.put(json6);
                        jarray1.put(json7);
                        jarray1.put(json8);
                        jarray1.put(json9);
                        //jarray1.put(json10);
                        jarray1.put(json11);

                    }

                    PreparedStatement psm2 = con.prepareStatement(" SELECT (SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `FourArm` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs2 = psm2.executeQuery();
                    JSONArray jarray2 = new JSONArray();
                    if (rs2.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();
                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs2.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs2.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs2.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs2.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs2.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs2.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs2.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs2.getString("y2016")));


                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs2.getString("y2009"));
                        data3 = Float.parseFloat(rs2.getString("y2010"));
                        data4 = Float.parseFloat(rs2.getString("y2011"));
                        data5 = Float.parseFloat(rs2.getString("y2012"));
                        data6 = Float.parseFloat(rs2.getString("y2013"));
                        data7 = Float.parseFloat(rs2.getString("y2014"));
                        data8 = Float.parseFloat(rs2.getString("y2015"));
                        data9 = Float.parseFloat(rs2.getString("y2016"));


                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Junction' and SubCategory='FourArm' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Junction','FourArm','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray2.put(json2);
                        jarray2.put(json3);
                        jarray2.put(json4);
                        jarray2.put(json5);
                        jarray2.put(json6);
                        jarray2.put(json7);
                        jarray2.put(json8);
                        jarray2.put(json9);
                        //jarray2.put(json10);
                        jarray2.put(json11);

                    }

                    PreparedStatement psm3 = con.prepareStatement(" SELECT (SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `Round` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs3 = psm3.executeQuery();
                    JSONArray jarray3 = new JSONArray();
                    if (rs3.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();

                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs3.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs3.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs3.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs3.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs3.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs3.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs3.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs3.getString("y2016")));


                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs3.getString("y2009"));
                        data3 = Float.parseFloat(rs3.getString("y2010"));
                        data4 = Float.parseFloat(rs3.getString("y2011"));
                        data5 = Float.parseFloat(rs3.getString("y2012"));
                        data6 = Float.parseFloat(rs3.getString("y2013"));
                        data7 = Float.parseFloat(rs3.getString("y2014"));
                        data8 = Float.parseFloat(rs3.getString("y2015"));
                        data9 = Float.parseFloat(rs3.getString("y2016"));
                        //data10 = Float.parseFloat(rs3.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Junction' and SubCategory='Round' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Junction','Round','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        
                        jarray3.put(json2);
                        jarray3.put(json3);
                        jarray3.put(json4);
                        jarray3.put(json5);
                        jarray3.put(json6);
                        jarray3.put(json7);
                        jarray3.put(json8);
                        jarray3.put(json9);
                        //jarray3.put(json10);
                        jarray3.put(json11);

                    }

                    PreparedStatement psm4 = con.prepareStatement(" SELECT (SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2009') AS y2009,(SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2010') AS y2010,(SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2011') AS y2011,(SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2012') AS y2012,(SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2013') AS y2013,(SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2014') AS y2014,(SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2015') AS y2015,(SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2016') AS y2016,(SELECT `RailCross` FROM `junctionbased` WHERE `States`='" + state + "' AND `Year`='2017') AS y2017  ");
                    ResultSet rs4 = psm4.executeQuery();
                    JSONArray jarray4 = new JSONArray();
                    if (rs4.next()) {
                        JSONObject json2 = new JSONObject();
                        JSONObject json3 = new JSONObject();
                        JSONObject json4 = new JSONObject();
                        JSONObject json5 = new JSONObject();
                        JSONObject json6 = new JSONObject();
                        JSONObject json7 = new JSONObject();
                        JSONObject json8 = new JSONObject();
                        JSONObject json9 = new JSONObject();

                        JSONObject json11 = new JSONObject();

                        json2.put("name", "2009");
                        json2.put("y", Float.parseFloat(rs4.getString("y2009")));

                        json3.put("name", "2010");
                        json3.put("y", Float.parseFloat(rs4.getString("y2010")));

                        json4.put("name", "2011");
                        json4.put("y", Float.parseFloat(rs4.getString("y2011")));

                        json5.put("name", "2012");
                        json5.put("y", Float.parseFloat(rs4.getString("y2012")));

                        json6.put("name", "2013");
                        json6.put("y", Float.parseFloat(rs4.getString("y2013")));

                        json7.put("name", "2014");
                        json7.put("y", Float.parseFloat(rs4.getString("y2014")));

                        json8.put("name", "2015");
                        json8.put("y", Float.parseFloat(rs4.getString("y2015")));

                        json9.put("name", "2016");
                        json9.put("y", Float.parseFloat(rs4.getString("y2016")));


                        float data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;

                        data2 = Float.parseFloat(rs4.getString("y2009"));
                        data3 = Float.parseFloat(rs4.getString("y2010"));
                        data4 = Float.parseFloat(rs4.getString("y2011"));
                        data5 = Float.parseFloat(rs4.getString("y2012"));
                        data6 = Float.parseFloat(rs4.getString("y2013"));
                        data7 = Float.parseFloat(rs4.getString("y2014"));
                        data8 = Float.parseFloat(rs4.getString("y2015"));
                        data9 = Float.parseFloat(rs4.getString("y2016"));
                        //data10 = Float.parseFloat(rs4.getString("y2017"));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8};
                        double y[] = {data2, data3, data4, data5, data6, data7, data8, data9};

                        LinearRegression lr = new LinearRegression(x, y);
                        json11.put("name", "2017");
                        if (lr.predict(9) < 0) {
                            json11.put("y", 0.0);
                        } else {
                            json11.put("y", lr.predict(9));
                        }
                         PreparedStatement psss1 = con.prepareStatement(" select * from predict2017 where Category='Junction' and SubCategory='RailCross' and State='"+state+"' and Accident='"+lr.predict(9)+"' and PYear=(SELECT (SELECT DISTINCT `Year` FROM `locationbased` ORDER BY `Year` DESC LIMIT 1)+1 AS `Year`) ");
                    ResultSet rsss1 = psss1.executeQuery();
                    if(rsss1.next())
                    { 
                    }
                    else
                    {
                        PreparedStatement psss2 = con.prepareStatement(" insert into predict2017(Category,SubCategory,PYear,Accident,State) values('Junction','RailCross','2017','"+lr.predict(9)+"','"+state+"') ");
                        int k = psss2.executeUpdate();
                        if(k > 0)
                        {
                        }
                    }
                        jarray4.put(json2);
                        jarray4.put(json3);
                        jarray4.put(json4);
                        jarray4.put(json5);
                        jarray4.put(json6);
                        jarray4.put(json7);
                        jarray4.put(json8);
                        jarray4.put(json9);
                        //jarray4.put(json10);
                        jarray4.put(json11);

                    }

                    JSONObject json = new JSONObject();
                    json.put("name", "TJunction");
                    json.put("data", jarray);

                    JSONObject json1 = new JSONObject();
                    json1.put("name", "YJunction");
                    json1.put("data", jarray1);

                    JSONObject json2 = new JSONObject();
                    json2.put("name", "FourArm");
                    json2.put("data", jarray2);

                    JSONObject json3 = new JSONObject();
                    json3.put("name", "Round");
                    json3.put("data", jarray3);

                    JSONObject json4 = new JSONObject();
                    json4.put("name", "RailCross");
                    json4.put("data", jarray4);


                    jaray.put(json);
                    jaray.put(json1);
                    jaray.put(json2);
                    jaray.put(json3);
                    jaray.put(json4);

                    System.out.println("data: "+jaray);
                    
                    out.print(jaray);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
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
