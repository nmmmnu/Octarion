package madsoft.server;

import java.io.*;
import java.net.*;
import java.util.Date;

import madsoft.util.MemoryManager;

/**
* Abstract client with TCP protocol
*
* @see TCPServer
*/
public abstract class TCPClient extends Thread{
   /**
   * Client socket
   */
   protected Socket client;

   /**
   * Client output character stream
   */
   protected PrintStream ps;

   /**
   * Client output stream
   */
   protected OutputStream os;

   /**
   * Client input stream
   */
   protected DataInputStream is;

//==========================================

   /**
   * Construct a TCP Client
   *
   * @param client Socket
   */
   public TCPClient(Socket client){
      super(TCPServer.ctg, "TCPClient thread");

      this.client = client;

      try{
         os = client.getOutputStream();
         ps = new PrintStream(os);
         is = new DataInputStream(client.getInputStream());
      }catch (IOException e){
         // this will throw Null pointer exception in method run()
      }
   }
//========================================

   /**
   * Destruct a TCP Client.
   *
   * same like finalize()
   */
   public void done(){   // Some like Destructor !!!
      try{
         ps.close();
      }catch (Exception e){}

      try{
         is.close();
      }catch (Exception e){}

      try{
         client.close();
      }catch (Exception e){}
   }
//========================================

   /**
   * Destruct a TCP Client
   */
   public void finalize() throws Throwable {
      try{
         done();
      }catch (Exception e){}

      super.finalize();
   }
//==========================================

   /**
   * Process a TCP Client
   */
   public void run(){
      //System.out.println("Helllllllllllooooooooo!\n");
      try{
         process();
      }catch (Exception e){
         Log.write(this,Log.ERROR,"WARNING: UNSUPPORTED EXCEPTION !!!");
      }catch (OutOfMemoryError e){
         Log.write(this,Log.ERROR,"WARNING: OUT OF MEMORY !!!");
      }

      try{
         done();
      }catch (Exception e){}
   }
//========================================

   /**
   * Real process of a TCP Client.
   *
   * U must inherit it!!!!
   */
   abstract public void process();
//========================================
}













/*
class TCPClientControler extends Thread{
   Date time = new Date();
   long timeout;

   TCPClient master;
//========================================
   public TCPClientControler(TCPClient master, long timeout){
      this.master = master;
      this.timeout = timeout;
   }
//========================================
   public void run(){
      if ((master != null) &&
         (timeout != 0  ))
         while (master.isAlive()){
            long l1 = time.getTime() + timeout;
            long l2 = (new Date()).getTime();

            //System.out.println(l1 + " , " + l2 + " , " + (l1 - l2) );

            if (l1 < l2){
               master.stop();
               master.done();
               Log.write(this,Log.EXTENDED,"Timout connection");
            }else
               yield(); //Process messages :)
         }
   }
//========================================
}

//****************************************
*/