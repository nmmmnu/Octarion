package madsoft.server;

import java.io.*;
import java.net.*;
import java.util.*;

import madsoft.command.CMDShell;
import madsoft.command.CMDExecutor;
import madsoft.command.CMD;

/**
* Telnet client provide a client part of telnet server
*
* <p>First it support a login with one (and only one) password without any
* username (server will ask pass in all cases)
* <p>Second it use CMDExecutor and CMDShell objects to provide a telnet sesion
*
* @see CMDShell
* @see CMDExecutor
* @see TCPServer
* @see TCPClient
*/
public class TelnetClient extends TCPClient{
   /**
   * CMDExecutor object
   */
   protected CMDExecutor cmd;

   /**
   * Password.
   *
   * It is not encripted, but it is on server, not on client :)
   */
   String pass;

   /**
   * Hello message
   */
   String hello;

//=======================================

   /**
   * Construct Telnet client
   *
   * @param client Socket
   * @param hello Hello message
   * @param cmd CMDExecutor
   */
   public TelnetClient(Socket client,
                       String hello,
                       String pass,
                       CMDExecutor cmd){
      super(client);
      this.pass = pass;
      this.hello = hello;

      this.cmd = cmd;

      setName("Telnet thread");
   }
//=======================================

   /**
   * Telnet process
   */
   public void process(){
      try{
         String s = new String();

         ps.println(hello);
         ps.println();
         ps.print("AdminKeyword : ");
         s = is.readLine();

         ps.println();

         if (s.compareTo(pass) != 0){
            Log.write(this,Log.EXTENDED,"Admin login failed");
            ps.println("Admin login failed.");
            done();
            return;

         }
      }catch (Exception e){
         return;
      }

      Log.write(this,Log.EXTENDED,"Admin login");

      try{
         CMDShell shell = new CMDShell(is,ps,cmd);
         shell.process();
      }catch (IOException e){
         Log.write(this,Log.DEBUG,"Error while responding");
      }catch (NullPointerException e){
         Log.write(this,Log.DEBUG,"Connection reset by peer");
      }

      Log.write(this,Log.EXTENDED,"Admin logout");

      done();
   }
//========================================
}

