package com.qhit.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Comuntiltime {
   public static double calc(String starttime,String endtime) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = simpleDateFormat.parse(starttime);
        Date end = simpleDateFormat.parse(endtime);
        double h =1.0*(end.getTime()-start.getTime())/(1000*60*60);
        return h;
    }
    public  static double WaterCount(){
        DecimalFormat df = new DecimalFormat("#.00");
     return   Double.parseDouble(df.format(Math.random()*300+1));
    }
    public static double DianCount(){
        DecimalFormat df = new DecimalFormat("#.00");
        return   Double.parseDouble(df.format(Math.random()*10+1));
    }
    public  static double OilCount(){
        DecimalFormat df = new DecimalFormat("#.00");
        return  Double.parseDouble(df.format( Math.random()*40+10));
    }
}
