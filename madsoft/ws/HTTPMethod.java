package madsoft.ws;

import  java.io.*;
import  java.util.*;
import  java.net.*;
import  madsoft.util.MixProperties;

abstract class HTTPMethod extends HTTP{
   final static char   SLASH = '/';
   final static String SLASHstr = SLASH + "";

   protected String filename;
   protected String querystr;

   protected String          line;
   protected boolean         header;
//==================================
  public HTTPMethod(){
     super();
  }
//==================================
  public HTTPMethod(String line, DataInputStream is, OutputStream os, Socket client, boolean header){
     this();
     methodConstruct(line, is, os, client, header);
  }
//==================================
  public void methodConstruct(String line, DataInputStream is, OutputStream os, Socket client, boolean header){
     this.line = line;
     this.is = is;
     this.os = os;
     this.client = client;
     this.header = header;
  }
//==================================
   abstract WebResponser getResponserProcess();
//==================================
   public WebResponser getResponser(){
      try{
         processInput();
         getInputLines(is,header);
         //--------------
         return getResponserProcess();
         //---------------
      }catch (IOException e){
         return (WebResponser) new SysHTTPerrorResponser(this,header,true);
      }
   }
//==================================
   abstract public String name();
//==================================
   protected void processInput() throws IOException{
      StringTokenizer st = new StringTokenizer(line);

      String s = st.nextToken(); //It's method name and not need

      if (st.hasMoreTokens())
         filename = st.nextToken();
      else
         filename = "";

      //Query string processing
      int x = filename.indexOf("?");
      querystr=null;
      if (x != -1){
         if (x < filename.length())
            querystr=filename.substring(x + 1);

         if (x > 0)
            filename=filename.substring(0,x);
      }
      //end

      if ((filename == null    )   ||
         (filename.length() == 0))
         filename = SLASHstr;

      if (filename.endsWith(SLASHstr))
         filename = filename + Cfg.getProp("DEFAULTDOC");

      if (filename.charAt(0) != SLASH) //Fixing hack bug
         filename = SLASH + filename;

      filename = Cfg.getProp("DOCROOT") + filename;

      filename = filename.replace(SLASH,File.separatorChar);

      Log.write(this,Log.DEBUG,"Recuest file: " + filename);

      if (st.hasMoreTokens()) {
         s = st.nextToken(); //It's HTTP version name and not need
      }
   }
//==================================
   protected void getInputLines(DataInputStream is, boolean header) throws IOException{
      String s;

      HTTPtags = new MixProperties();

      HTTPtags.clear();

      if (header)
         while ((s = is.readLine()) != null) { // loop through the rest of the input lines
            if (s.trim().equals(""))
               break;

            int x = s.indexOf(": ");
            if (x == -1)
               HTTPtags.put(s,"");
            else
               HTTPtags.put(s.substring(0,x),
                            s.substring(x + 2));

         }
   }
//==================================
}