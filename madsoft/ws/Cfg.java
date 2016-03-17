package madsoft.ws;

import java.io.*;

import madsoft.util.*;     

public class Cfg{
   public final static String SERVERNAME = "WS";
   public final static String LONGSERVERNAME = "Octarion";
   public final static String VERSION = "0.999";

   public final static String CFGFILENAME = SERVERNAME + ".ini";

   static MixProperties mpr;
//===================================================
   private static Cfg scfg = new Cfg();
//===================================================
   public Cfg(){
      super();

      MixProperties pr = new MixProperties();

      pr.put("ADMINKEY",         "passroot");

      pr.put("SERVERPORT",       "88");
      pr.put("MAXBACKLOG",       "100");
      pr.put("MAXTHREADS",       "200");
      pr.put("TIMEOUT",          "900000"); //900 Sec = 15 min       
      pr.put("ADMINPORT",        "90");

      pr.put("MINMEMORY",        "400");

      pr.put("DOCROOT",          "/www");
      
      pr.put("CGIROOT",          "/cgi-bin");
      pr.put("SYSDOCROOT",       "/system");
      pr.put("PROCROOT",         "/proc");

      pr.put("DEFAULTDOC",       "index.html");

      pr.put("IPRESTRICT",       "1");

      pr.put("LOGLEVEL",         "255");

      pr.put("UNICODELANGUAGE",  "0");     //848 za Bulgaria

      mpr = new MixProperties(pr);
   }
//=================================================
   synchronized public static void load(){
      Log.write(null,Log.NORMAL,"Loading server configuration.");

      try{
         mpr.load(new FileInputStream(CFGFILENAME));
      }catch (IOException e){
         Log.write(null,Log.ERROR,"Unable to read server configuration.");
      }
   }
//=================================================
   /*synchronized public static void save(){
      Log.write(null,0,"Saving server configuration.");
      try{
         mpr.save(new FileOutputStream(CFGFILENAME) ,
                  SERVERNAME + " web server configuration");
      }catch (IOException e){
         Log.write(null,1,"Unable to save server configuration.");
      }
   }*/
//=================================================
   public static String getProp(String a, String b){
      return mpr.getProperty(a, b);
   }
//=================================================
   public static String getProp(String a){
      return mpr.getProperty(a);
   }
//=================================================
   public static int getIntProp(String a){
      return mpr.getIntProperty(a);
   }
//=================================================
   public static int getIntProp(String a, int b){
      return mpr.getIntProperty(a, b);
   }
//=================================================
}
