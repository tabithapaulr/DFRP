package com.users;

import com.database.util.DBsingletone;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "getOverallReview", urlPatterns = {"/getOverallReview"})
public class GetOverallReview extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String state = request.getParameter("state");
            String ctype = request.getParameter("ctype");
            
            DBsingletone db = DBsingletone.getDbSingletone();
            Connection con = db.getConnection();
            
            if(ctype.equals("Alcohol"))
            {
                String sql = "select * from (SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='-1' AND `State`='"+state+"') ";
                out.print("Result Not Found");
            }
            else if(ctype.equals("Climate"))
            {
                String sql1 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='Fog' AND `State`='"+state+"') as Fog ";
                String sql2 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='Cloud' AND `State`='"+state+"') as Cloud  ";
                String sql3 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='HeavyRain' AND `State`='"+state+"') as HeavyRain  ";
                String sql4 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='LightRain' AND `State`='"+state+"') as LightRain  ";
                String sql5 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='Snow' AND `State`='"+state+"') as Snow  ";
                String sql6 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='Cold' AND `State`='"+state+"') as Cold  ";
                String sql7 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='Hot' AND `State`='"+state+"') as Hot ";
                
                PreparedStatement ps = con.prepareStatement(" select "+sql1+","+sql2+","+sql3+","+sql4+","+sql5+","+sql6+","+sql7+" ");
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    double Fog = Double.parseDouble(rs.getString("Fog"));
                    double Cloud = Double.parseDouble(rs.getString("Cloud"));
                    double HeavyRain = Double.parseDouble(rs.getString("HeavyRain"));
                    double LightRain = Double.parseDouble(rs.getString("LightRain"));
                    double Snow = Double.parseDouble(rs.getString("Snow"));
                    double Cold = Double.parseDouble(rs.getString("Cold"));
                    double Hot = Double.parseDouble(rs.getString("Hot"));
                    
                    ArrayList<Double> arrayList = new ArrayList<Double>();
                    arrayList.add(Fog);
                    arrayList.add(Cloud);
                    arrayList.add(HeavyRain);
                    arrayList.add(LightRain);
                    arrayList.add(Snow);
                    arrayList.add(Cold);
                    arrayList.add(Hot);
                    
                    Double i = Collections.max(arrayList);
                    String res = "";
                    
                    if(i == Fog)
                    {
                        res = "Fog";
                    }
                    else if(i == Cloud)
                    {
                        res = "Cloud";
                    }
                    else if(i == HeavyRain)
                    {
                        res = "HeavyRain";
                    }
                    else if(i == LightRain)
                    {
                        res = "LightRain";
                    }
                    else if(i == Snow)
                    {
                        res = "Snow";
                    }
                    else if(i == Cold)
                    {
                        res = "Cold";
                    }
                    else if(i == Hot)
                    {
                        res = "Hot";
                    }
                    
                    out.print(res+" Climate is reason for maximum accidents");
                    
                }
            }
            else if(ctype.equals("Location"))
            {
                String sql1 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='NearSchool' AND `State`='"+state+"') as NearSchool ";
                String sql2 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='NearFactory' AND `State`='"+state+"') as NearFactory  ";
                String sql3 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='NearBazaar' AND `State`='"+state+"') as NearBazaar  ";
                String sql4 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='NearHospital' AND `State`='"+state+"') as NearHospital  ";
                String sql5 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='NearBusStop' AND `State`='"+state+"') as NearBusStop  ";
                
                PreparedStatement ps = con.prepareStatement(" select "+sql1+","+sql2+","+sql3+","+sql4+","+sql5+" ");
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    double NearSchool = Double.parseDouble(rs.getString("NearSchool"));
                    double NearFactory = Double.parseDouble(rs.getString("NearFactory"));
                    double NearBazaar = Double.parseDouble(rs.getString("NearBazaar"));
                    double NearHospital = Double.parseDouble(rs.getString("NearHospital"));
                    double NearBusStop = Double.parseDouble(rs.getString("NearBusStop"));
                    
                    ArrayList<Double> arrayList = new ArrayList<Double>();
                    arrayList.add(NearSchool);
                    arrayList.add(NearFactory);
                    arrayList.add(NearBazaar);
                    arrayList.add(NearHospital);
                    arrayList.add(NearBusStop);
                    
                    Double i = Collections.max(arrayList);
                    String res = "";
                    
                    if(i == NearSchool)
                    {
                        res = "NearSchool";
                    }
                    else if(i == NearFactory)
                    {
                        res = "NearFactory";
                    }
                    else if(i == NearBazaar)
                    {
                        res = "NearBazaar";
                    }
                    else if(i == NearHospital)
                    {
                        res = "NearHospital";
                    }
                    else if(i == NearBusStop)
                    {
                        res = "NearBusStop";
                    }
                    
                    out.print("Location "+res+" is reason for maximum accidents");
                    
                }
            }
            else if(ctype.equals("VehicleDefect"))
            {
                String sql1 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='DefectiveBrakes' AND `State`='"+state+"') as DefectiveBrakes ";
                String sql2 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='Punctured' AND `State`='"+state+"') as Punctured  ";
                String sql3 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='BaldTyres' AND `State`='"+state+"') as BaldTyres  ";
                String sql4 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='Others' AND `State`='"+state+"') as Others  ";
                
                PreparedStatement ps = con.prepareStatement(" select "+sql1+","+sql2+","+sql3+","+sql4+" ");
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    double DefectiveBrakes = Double.parseDouble(rs.getString("DefectiveBrakes"));
                    double Punctured = Double.parseDouble(rs.getString("Punctured"));
                    double BaldTyres = Double.parseDouble(rs.getString("BaldTyres"));
                    double Others = Double.parseDouble(rs.getString("Others"));
                    
                    ArrayList<Double> arrayList = new ArrayList<Double>();
                    arrayList.add(DefectiveBrakes);
                    arrayList.add(Punctured);
                    arrayList.add(BaldTyres);
                    arrayList.add(Others);
                    
                    Double i = Collections.max(arrayList);
                    String res = "";
                    
                    if(i == DefectiveBrakes)
                    {
                        res = "DefectiveBrakes";
                    }
                    else if(i == Punctured)
                    {
                        res = "Punctured";
                    }
                    else if(i == BaldTyres)
                    {
                        res = "BaldTyres";
                    }
                    else if(i == Others)
                    {
                        res = "Others";
                    }
                    
                    out.print("Vehicle defect like "+res+" is reason for maximum accidents");
                    
                }
            }
            else
            {
                String sql1 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='TJunction' AND `State`='"+state+"') as TJunction ";
                String sql2 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='YJunction' AND `State`='"+state+"') as YJunction  ";
                String sql3 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='FourArm' AND `State`='"+state+"') as FourArm  ";
                String sql4 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='Round' AND `State`='"+state+"') as Round  ";
                String sql5 = "(SELECT `Accident` FROM `predictedresult` WHERE `Category`='"+ctype+"' AND `SubCategory`='RailCross' AND `State`='"+state+"') as RailCross  ";
                
                System.out.println(" select "+sql1+","+sql2+","+sql3+","+sql4+","+sql5+" ");
                PreparedStatement ps = con.prepareStatement(" select "+sql1+","+sql2+","+sql3+","+sql4+","+sql5+" ");
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    double TJunction = Double.parseDouble(rs.getString("TJunction"));
                    double YJunction = Double.parseDouble(rs.getString("YJunction"));
                    double FourArm = Double.parseDouble(rs.getString("FourArm"));
                    double Round = Double.parseDouble(rs.getString("Round"));
                    double RailCross = Double.parseDouble(rs.getString("RailCross"));
                    
                    ArrayList<Double> arrayList = new ArrayList<Double>();
                    arrayList.add(TJunction);
                    arrayList.add(YJunction);
                    arrayList.add(FourArm);
                    arrayList.add(Round);
                    arrayList.add(RailCross);
                    
                    Double i = Collections.max(arrayList);
                    String res = "";
                    
                    if(i == TJunction)
                    {
                        res = "TJunction";
                    }
                    else if(i == YJunction)
                    {
                        res = "YJunction";
                    }
                    else if(i == FourArm)
                    {
                        res = "FourArm";
                    }
                    else if(i == Round)
                    {
                        res = "Round";
                    }
                    else if(i == RailCross)
                    {
                        res = "RailCross";
                    }
                    
                    out.print("Junction like "+res+" is reason for maximum accidents");
                    
                }
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
