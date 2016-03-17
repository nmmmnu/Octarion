package madsoft.ws;

class SysBadrecuestResponser extends SysResponser{
   public SysBadrecuestResponser(HTTP http, boolean header, boolean body){
      super(http,header,HTTPBADRECUEST,body,"badrecuest.htm");
   }
}
