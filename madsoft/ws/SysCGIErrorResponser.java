package madsoft.ws;

class SysCGIErrorResponser extends SysResponser{
   public SysCGIErrorResponser(HTTP http, boolean header, boolean body){
      super(http,header,HTTPCGIERROR,body,"cgierror.htm");
   }
}
