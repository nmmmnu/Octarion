package madsoft.ws;

class SysFilenotfoundResponser extends SysResponser{
   public SysFilenotfoundResponser(HTTP http, boolean header, boolean body){
      super(http,header,HTTPFILENOTFOUND,body,"filenotfound.htm");
   }
}
