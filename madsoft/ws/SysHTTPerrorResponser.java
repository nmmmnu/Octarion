package madsoft.ws;

class SysHTTPerrorResponser extends SysResponser{
   public SysHTTPerrorResponser(HTTP http, boolean header, boolean body){
      super(http,header,HTTPERROR,body,"httperror.htm");
   }
}
