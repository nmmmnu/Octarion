package madsoft.ws;

import java.io.*;
import java.net.*;
import java.util.*;

import madsoft.server.*;
import madsoft.util.Trans;

public class WWWClient extends TCPClient{
//=======================================
   WWWClient(Socket client){
      super(client);
      setName("WWW thread");
   }
//=======================================
   public void process(){
      try{
         WebResponser  wr;

         String line = is.readLine();

         StringTokenizer st = new StringTokenizer(line);

         String method = "";
         if (st.hasMoreTokens())
            method = st.nextToken();

         String filename = "";
         if (st.hasMoreTokens())
            filename = st.nextToken();

         String version = "";
         if (st.hasMoreTokens())
            version = st.nextToken();

         boolean header = (version != "");

         Log.write(Log.WWW, Trans.ip2str(client.getInetAddress()) + Log.DELIMITER + method + Log.DELIMITER + filename);

         //----------------------------------
         if (method == "") //No given method ???
            wr = (WebResponser) new SysBadrecuestResponser(new HTTP(is,os,client,null),header,true);
         else{
            HTTPMethod httpmethod = getMethod(method);
            if (httpmethod != null){
               httpmethod.methodConstruct(line,is,os,client,header);
               wr = (WebResponser) httpmethod.getResponser();
            }else //Not supported method
               wr = (WebResponser) new SysNotimplementedResponser(new HTTP(is,os,client,null),header,true);
         }
         //----------------------------------
         wr.process();

      }catch (IOException e){
         Log.write(this,Log.DEBUG,"Error while responding");
      }catch (NullPointerException e){
         Log.write(this,Log.DEBUG,"Connection reset by peer");
      }

      done();
   }
//=======================================
   static Hashtable methods = new Hashtable();
//=======================================
   static public void addMethod(HTTPMethod x){
      methods.put(x.name(), x.getClass().getName() );
      Log.write(null,Log.NORMAL,"HTTP Method " + x.name() + " is implemented");
   }
//=======================================
   public HTTPMethod getMethod(String s){
      if (!methods.containsKey(s))
         return null;

      try{
         Class a = Class.forName((String) methods.get(s));

         return (HTTPMethod) a.newInstance();
      }catch (Exception e){
         return null;
      }
   }
//=======================================
}