package com.users;

import com.database.util.DBsingletone;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet(name = "getAccidentRatioBetweenStateVsCountry", urlPatterns = {"/getAccidentRatioBetweenStateVsCountry"})
public class GetAccidentRatioBetweenStateVsCountry extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String state = "Karnataka";
            String ctype = "Junction";

            DBsingletone db = DBsingletone.getDbSingletone();
            Connection con = db.getConnection();
            JSONArray jaray = new JSONArray();

            String res = "";

            if (ctype.equals("Alcohol")) {
                PreparedStatement psr = con.prepareStatement(" SELECT * FROM `alcoholbasedrecords` WHERE `States`='All India' ");
                ResultSet rs = psr.executeQuery();
                JSONObject js = new JSONObject();
                if (rs.next()) {
                    int i8 = rs.getInt("Y2008");
                    int i9 = rs.getInt("Y2009");
                    int i10 = rs.getInt("Y2010");
                    int i11 = rs.getInt("Y2011");
                    int i12 = rs.getInt("Y2012");
                    int i13 = rs.getInt("Y2013");
                    int i14 = rs.getInt("Y2014");
                    int i15 = rs.getInt("Y2015");
                    int i16 = rs.getInt("Y2016");
                    int i17 = rs.getInt("Y2017");
                    int i18 = 0;

                    String sql1 = "SELECT * FROM `alcoholbasedrecords` WHERE `States`='" + state + "' ";
                    PreparedStatement psmt1 = con.prepareStatement(sql1);
                    ResultSet rss1 = psmt1.executeQuery();
                    int s8 = 0, s9 = 0, s10 = 10, s11 = 0, s12 = 0, s13 = 0, s14 = 0, s15 = 0, s16 = 0, s17 = 0;
                    int s18 = 0;
                    if (rss1.next()) {
                        s8 = rss1.getInt("Y2008");
                        s9 = rss1.getInt("Y2009");
                        s10 = rss1.getInt("Y2010");
                        s11 = rss1.getInt("Y2011");
                        s12 = rss1.getInt("Y2012");
                        s13 = rss1.getInt("Y2013");
                        s14 = rss1.getInt("Y2014");
                        s15 = rss1.getInt("Y2015");
                        s16 = rss1.getInt("Y2016");
                        s17 = rss1.getInt("Y2017");

                    }

                    getRatio gr = new getRatio();

                    js.put("2008", gr.ratio(s8, i8));
                    js.put("2009", gr.ratio(s9, i9));
                    js.put("2010", gr.ratio(s10, i10));
                    js.put("2011", gr.ratio(s11, i11));
                    js.put("2012", gr.ratio(s12, i12));
                    js.put("2013", gr.ratio(s13, i13));
                    js.put("2014", gr.ratio(s14, i14));
                    js.put("2015", gr.ratio(s15, i15));
                    js.put("2016", gr.ratio(s16, i16));
                    js.put("2017", gr.ratio(s17, i17));

                    double x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                    double y[] = {s8, s9, s10, s11, s12, s13, s14, s15, s16, s17};

                    double x1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                    double y1[] = {i8, i9, i10, i11, i12, i13, i14, i15, i16, i17};

                    LinearRegression lr = new LinearRegression(x, y);
                    LinearRegression lr1 = new LinearRegression(x1, y1);
                    s18 = (int) lr.predict(11);
                    i18 = (int) lr1.predict(11);

                    js.put("2018", gr.ratio(s18, i18));
                    js.put("cat", "Alcohol");
                    js.put("sub", "");
                    jaray.put(js);

                    out.print(jaray);

                }
            } else if (ctype.equals("Climate")) {
                List li = new ArrayList();
                li.add("Fog");
                li.add("Cloud");
                li.add("HeavyRain");
                li.add("LightRain");
                li.add("Snow");
                li.add("Hot");
                li.add("Cold");

                for (int i = 0; i < li.size(); i++) {
                    System.out.println("" + li.get(i));

                    PreparedStatement ps = con.prepareStatement(" SELECT (SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2009' ) AS Y2009,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2010' ) AS Y2010,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2011' ) AS Y2011,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2012' ) AS Y2012,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2013' ) AS Y2013,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2014' ) AS Y2014,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2015' ) AS Y2015,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2016' ) AS Y2016,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='All India' AND `Year`='2017' ) AS Y2017,'" + li.get(i) + "' AS climate ");
                    ResultSet rs = ps.executeQuery();
                    JSONObject js = new JSONObject();
                    if (rs.next()) {
                        int i8 = 0;
                        int i9 = rs.getInt("Y2009");
                        int i10 = rs.getInt("Y2010");
                        int i11 = rs.getInt("Y2011");
                        int i12 = rs.getInt("Y2012");
                        int i13 = rs.getInt("Y2013");
                        int i14 = rs.getInt("Y2014");
                        int i15 = rs.getInt("Y2015");
                        int i16 = rs.getInt("Y2016");
                        int i17 = rs.getInt("Y2017");
                        int i18 = 0;

                        PreparedStatement psmt1 = con.prepareStatement("SELECT (SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2009' ) AS Y2009,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2010' ) AS Y2010,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2011' ) AS Y2011,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2012' ) AS Y2012,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2013' ) AS Y2013,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2014' ) AS Y2014,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2015' ) AS Y2015,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2016' ) AS Y2016,(SELECT " + li.get(i) + " FROM `climatebasedrecords` WHERE `States`='" + state + "' AND `Year`='2017' ) AS Y2017,'" + li.get(i) + "' AS climate");
                        ResultSet rss1 = psmt1.executeQuery();
                        int s8 = 0, s9 = 0, s10 = 10, s11 = 0, s12 = 0, s13 = 0, s14 = 0, s15 = 0, s16 = 0, s17 = 0;
                        int s18 = 0;
                        if (rss1.next()) {
                            s9 = rss1.getInt("Y2009");
                            s10 = rss1.getInt("Y2010");
                            s11 = rss1.getInt("Y2011");
                            s12 = rss1.getInt("Y2012");
                            s13 = rss1.getInt("Y2013");
                            s14 = rss1.getInt("Y2014");
                            s15 = rss1.getInt("Y2015");
                            s16 = rss1.getInt("Y2016");
                            s17 = rss1.getInt("Y2017");

                        }

                        getRatio gr = new getRatio();

                        js.put("2008", "0:0");
                        js.put("2009", gr.ratio(s9, i9));
                        js.put("2010", gr.ratio(s10, i10));
                        js.put("2011", gr.ratio(s11, i11));
                        js.put("2012", gr.ratio(s12, i12));
                        js.put("2013", gr.ratio(s13, i13));
                        js.put("2014", gr.ratio(s14, i14));
                        js.put("2015", gr.ratio(s15, i15));
                        js.put("2016", gr.ratio(s16, i16));
                        js.put("2017", gr.ratio(s17, i17));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        double y[] = {s8, s9, s10, s11, s12, s13, s14, s15, s16, s17};

                        double x1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        double y1[] = {i8, i9, i10, i11, i12, i13, i14, i15, i16, i17};

                        LinearRegression lr = new LinearRegression(x, y);
                        LinearRegression lr1 = new LinearRegression(x1, y1);
                        s18 = (int) lr.predict(11);
                        i18 = (int) lr1.predict(11);

                        js.put("2018", gr.ratio(s18, i18));
                        js.put("cat", "Climate");
                        js.put("sub", rs.getString("climate"));
                        jaray.put(js);
                    }
                }
                out.print(jaray);
            } else if (ctype.equals("Location")) {

                String table = "locationbased";

                List li = new ArrayList();
                li.add("NearSchool");
                li.add("NearFactory");
                li.add("NearHospital");
                li.add("NearBazaar");
                li.add("NearBusStop");

                for (int i = 0; i < li.size(); i++) {
                    PreparedStatement ps = con.prepareStatement(" SELECT (SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2009' ) AS Y2009,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2010' ) AS Y2010,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2011' ) AS Y2011,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2012' ) AS Y2012,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2013' ) AS Y2013,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2014' ) AS Y2014,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2015' ) AS Y2015,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2016' ) AS Y2016,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2017' ) AS Y2017,'" + li.get(i) + "' AS climate ");
                    ResultSet rs = ps.executeQuery();
                    JSONObject js = new JSONObject();
                    if (rs.next()) {
                        int i8 = 0;
                        int i9 = rs.getInt("Y2009");
                        int i10 = rs.getInt("Y2010");
                        int i11 = rs.getInt("Y2011");
                        int i12 = rs.getInt("Y2012");
                        int i13 = rs.getInt("Y2013");
                        int i14 = rs.getInt("Y2014");
                        int i15 = rs.getInt("Y2015");
                        int i16 = rs.getInt("Y2016");
                        int i17 = rs.getInt("Y2017");
                        int i18 = 0;

                        PreparedStatement psmt1 = con.prepareStatement("SELECT (SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2009' ) AS Y2009,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2010' ) AS Y2010,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2011' ) AS Y2011,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2012' ) AS Y2012,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2013' ) AS Y2013,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2014' ) AS Y2014,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2015' ) AS Y2015,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2016' ) AS Y2016,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2017' ) AS Y2017,'" + li.get(i) + "' AS climate");
                        ResultSet rss1 = psmt1.executeQuery();
                        int s8 = 0, s9 = 0, s10 = 10, s11 = 0, s12 = 0, s13 = 0, s14 = 0, s15 = 0, s16 = 0, s17 = 0;
                        int s18 = 0;
                        if (rss1.next()) {
                            s8 = 0;
                            s9 = rss1.getInt("Y2009");
                            s10 = rss1.getInt("Y2010");
                            s11 = rss1.getInt("Y2011");
                            s12 = rss1.getInt("Y2012");
                            s13 = rss1.getInt("Y2013");
                            s14 = rss1.getInt("Y2014");
                            s15 = rss1.getInt("Y2015");
                            s16 = rss1.getInt("Y2016");
                            s17 = rss1.getInt("Y2017");

                        }

                        getRatio gr = new getRatio();

                        js.put("2008", "0:0");
                        js.put("2009", gr.ratio(s9, i9));
                        js.put("2010", gr.ratio(s10, i10));
                        js.put("2011", gr.ratio(s11, i11));
                        js.put("2012", gr.ratio(s12, i12));
                        js.put("2013", gr.ratio(s13, i13));
                        js.put("2014", gr.ratio(s14, i14));
                        js.put("2015", gr.ratio(s15, i15));
                        js.put("2016", gr.ratio(s16, i16));
                        js.put("2017", gr.ratio(s17, i17));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        double y[] = {s8, s9, s10, s11, s12, s13, s14, s15, s16, s17};

                        double x1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        double y1[] = {i8, i9, i10, i11, i12, i13, i14, i15, i16, i17};

                        LinearRegression lr = new LinearRegression(x, y);
                        LinearRegression lr1 = new LinearRegression(x1, y1);
                        s18 = (int) lr.predict(11);
                        i18 = (int) lr1.predict(11);

                        js.put("2018", gr.ratio(s18, i18));
                        js.put("cat", "Location");
                        js.put("sub", rs.getString("climate"));
                        jaray.put(js);

                    }
                }
                out.print(jaray);
            } else if (ctype.equals("Junction")) {

                String table = "junctionbased";

                List li = new ArrayList();
                li.add("TJunction");
                li.add("YJunction");
                li.add("FourArm");
                li.add("Round");
                li.add("RailCross");

                for (int i = 0; i < li.size(); i++) {
                    PreparedStatement ps = con.prepareStatement(" SELECT (SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2009' ) AS Y2009,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2010' ) AS Y2010,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2011' ) AS Y2011,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2012' ) AS Y2012,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2013' ) AS Y2013,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2014' ) AS Y2014,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2015' ) AS Y2015,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2016' ) AS Y2016,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2017' ) AS Y2017,'" + li.get(i) + "' AS climate ");
                    ResultSet rs = ps.executeQuery();
                    JSONObject js = new JSONObject();
                    if (rs.next()) {
                        int i8 = 0;
                        int i9 = rs.getInt("Y2009");
                        int i10 = rs.getInt("Y2010");
                        int i11 = rs.getInt("Y2011");
                        int i12 = rs.getInt("Y2012");
                        int i13 = rs.getInt("Y2013");
                        int i14 = rs.getInt("Y2014");
                        int i15 = rs.getInt("Y2015");
                        int i16 = rs.getInt("Y2016");
                        int i17 = rs.getInt("Y2017");
                        int i18 = 0;

                        PreparedStatement psmt1 = con.prepareStatement("SELECT (SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2009' ) AS Y2009,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2010' ) AS Y2010,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2011' ) AS Y2011,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2012' ) AS Y2012,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2013' ) AS Y2013,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2014' ) AS Y2014,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2015' ) AS Y2015,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2016' ) AS Y2016,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2017' ) AS Y2017,'" + li.get(i) + "' AS climate");
                        ResultSet rss1 = psmt1.executeQuery();
                        int s8 = 0, s9 = 0, s10 = 10, s11 = 0, s12 = 0, s13 = 0, s14 = 0, s15 = 0, s16 = 0, s17 = 0;
                        int s18 = 0;
                        if (rss1.next()) {
                            s8 = 0;
                            s9 = rss1.getInt("Y2009");
                            s10 = rss1.getInt("Y2010");
                            s11 = rss1.getInt("Y2011");
                            s12 = rss1.getInt("Y2012");
                            s13 = rss1.getInt("Y2013");
                            s14 = rss1.getInt("Y2014");
                            s15 = rss1.getInt("Y2015");
                            s16 = rss1.getInt("Y2016");
                            s17 = rss1.getInt("Y2017");

                        }

                        getRatio gr = new getRatio();

                        js.put("2008", "0:0");
                        js.put("2009", gr.ratio(s9, i9));
                        js.put("2010", gr.ratio(s10, i10));
                        js.put("2011", gr.ratio(s11, i11));
                        js.put("2012", gr.ratio(s12, i12));
                        js.put("2013", gr.ratio(s13, i13));
                        js.put("2014", gr.ratio(s14, i14));
                        js.put("2015", gr.ratio(s15, i15));
                        js.put("2016", gr.ratio(s16, i16));
                        js.put("2017", gr.ratio(s17, i17));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        double y[] = {s8, s9, s10, s11, s12, s13, s14, s15, s16, s17};

                        double x1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        double y1[] = {i8, i9, i10, i11, i12, i13, i14, i15, i16, i17};

                        LinearRegression lr = new LinearRegression(x, y);
                        LinearRegression lr1 = new LinearRegression(x1, y1);
                        s18 = (int) lr.predict(11);
                        i18 = (int) lr1.predict(11);

                        js.put("2018", gr.ratio(s18, i18));
                        js.put("cat", "Junction");
                        js.put("sub", rs.getString("climate"));
                        jaray.put(js);
                    }
                }
                out.print(jaray);
            } else if (ctype.equals("VehicleDefect")) {

                String table = "vehicledefect";

                List li = new ArrayList();
                li.add("DefectiveBrakes");
                li.add("Punctured");
                li.add("BaldTyres");
                li.add("Others");

                for (int i = 0; i < li.size(); i++) {
                    PreparedStatement ps = con.prepareStatement(" SELECT (SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2009' ) AS Y2009,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2010' ) AS Y2010,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2011' ) AS Y2011,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2012' ) AS Y2012,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2013' ) AS Y2013,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2014' ) AS Y2014,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2015' ) AS Y2015,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2016' ) AS Y2016,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='Total' AND `Year`='2017' ) AS Y2017,'" + li.get(i) + "' AS climate ");
                    ResultSet rs = ps.executeQuery();
                    JSONObject js = new JSONObject();
                    if (rs.next()) {
                        int i8 = 0;
                        int i9 = rs.getInt("Y2009");
                        int i10 = rs.getInt("Y2010");
                        int i11 = rs.getInt("Y2011");
                        int i12 = rs.getInt("Y2012");
                        int i13 = rs.getInt("Y2013");
                        int i14 = rs.getInt("Y2014");
                        int i15 = rs.getInt("Y2015");
                        int i16 = rs.getInt("Y2016");
                        int i17 = rs.getInt("Y2017");
                        int i18 = 0;

                        PreparedStatement psmt1 = con.prepareStatement("SELECT (SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2009' ) AS Y2009,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2010' ) AS Y2010,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2011' ) AS Y2011,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2012' ) AS Y2012,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2013' ) AS Y2013,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2014' ) AS Y2014,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2015' ) AS Y2015,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2016' ) AS Y2016,(SELECT " + li.get(i) + " FROM " + table + " WHERE `States`='" + state + "' AND `Year`='2017' ) AS Y2017,'" + li.get(i) + "' AS climate");
                        ResultSet rss1 = psmt1.executeQuery();
                        int s8 = 0, s9 = 0, s10 = 10, s11 = 0, s12 = 0, s13 = 0, s14 = 0, s15 = 0, s16 = 0, s17 = 0;
                        int s18 = 0;
                        if (rss1.next()) {
                            s8 = 0;
                            s9 = rss1.getInt("Y2009");
                            s10 = rss1.getInt("Y2010");
                            s11 = rss1.getInt("Y2011");
                            s12 = rss1.getInt("Y2012");
                            s13 = rss1.getInt("Y2013");
                            s14 = rss1.getInt("Y2014");
                            s15 = rss1.getInt("Y2015");
                            s16 = rss1.getInt("Y2016");
                            s17 = rss1.getInt("Y2017");

                        }

                        getRatio gr = new getRatio();

                        js.put("2008", "0:0");
                        js.put("2009", gr.ratio(s9, i9));
                        js.put("2010", gr.ratio(s10, i10));
                        js.put("2011", gr.ratio(s11, i11));
                        js.put("2012", gr.ratio(s12, i12));
                        js.put("2013", gr.ratio(s13, i13));
                        js.put("2014", gr.ratio(s14, i14));
                        js.put("2015", gr.ratio(s15, i15));
                        js.put("2016", gr.ratio(s16, i16));
                        js.put("2017", gr.ratio(s17, i17));

                        double x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        double y[] = {s8, s9, s10, s11, s12, s13, s14, s15, s16, s17};

                        double x1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                        double y1[] = {i8, i9, i10, i11, i12, i13, i14, i15, i16, i17};

                        LinearRegression lr = new LinearRegression(x, y);
                        LinearRegression lr1 = new LinearRegression(x1, y1);
                        s18 = (int) lr.predict(11);
                        i18 = (int) lr1.predict(11);

                        js.put("2018", gr.ratio(s18, i18));
                        js.put("cat", "VehicleDefect");
                        js.put("sub", rs.getString("climate"));
                        jaray.put(js);
                    }
                }
                out.print(jaray);
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
    }// </editor-fold>

}
