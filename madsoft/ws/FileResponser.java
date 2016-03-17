package madsoft.ws;

import java.io.*;
import madsoft.util.MixProperties;

class FileResponser extends AbstractFileResponser{
//========================================================
   public FileResponser(HTTP http, boolean header, boolean body, String filename){
      super(http,header,HTTPOK,body,filename);
   }
//========================================================
   void ResponceError(int code){
      if (code == 1){
         SysOutofrootResponser ws = new SysOutofrootResponser(this,header,body);
         ws.process();
      }else
      if (code == 2){
         SysFilenotfoundResponser ws = new SysFilenotfoundResponser(this,header,body);
         ws.process();
      }
   }
}

