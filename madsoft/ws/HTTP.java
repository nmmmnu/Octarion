package madsoft.ws;

import java.io.*;
import java.net.*;
import madsoft.util.MixProperties;

public class HTTP{
   protected DataInputStream is;
   protected OutputStream    os;
   protected PrintStream     ps;
   protected Socket          client;
   protected MixProperties   HTTPtags;

   String HTTPversion = "1.0";
//========================================
   public HTTP (DataInputStream is, OutputStream os, Socket client, MixProperties HTTPtags){
      construct(is,os,client,HTTPtags);
   }
//========================================
   public HTTP (HTTP a){
      construct(a);
   }
//========================================
   public HTTP (){} //Default constructor
//========================================
   //Real constructor
   public void construct(DataInputStream is, OutputStream os, Socket client, MixProperties HTTPtags){
      this.is         = is;
      this.os         = os;
      this.ps         = new PrintStream(os);

      this.client     = client;

      this.HTTPtags   = HTTPtags;
   }
//========================================
   public void construct(HTTP a){
      construct(a.is,a.os,a.client,a.HTTPtags);
      
      this.HTTPversion = a.HTTPversion;
   }
//========================================
}