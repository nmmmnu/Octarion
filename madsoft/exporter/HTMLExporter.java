package madsoft.exporter;

import java.io.*;

/**
* This class provide export to real HTML format
* @see TextHTMLExporter
*/
public class HTMLExporter extends AbstractExporter{
   /**
   * constant for HTML language
   */
   public final static String BULGARIA = "windows-1251";   //Bulgaria BDS

   /**
   * constant for HTML language
   */
   public final static String RUSSIA   = "koi8-r";         //Russia KOI-8

   /**
   * constant for HTML language
   */
   public final static String WESTERN  = "iso-8859-1";     //Western

   /**
   * constant for HTML language
   */
   public final static String EUROPE   = "windows-1250";   //Central Europe
//=========================================================

   /**
   * HTML Name
   */
   String HTMLFileName = null;

   /**
   * HTML background image
   */
   String BackGround = null;

   /**
   * HTML language
   */
   String CharSet = null;

   /**
   * HTML Base URL
   */
   String BaseURL = null;

   /**
   * HTML target frame
   */
   String TargetFrame = null;

   /**
   * HTML Colors
   */
   HTMLColors colors = new HTMLColors();
//=========================================================

   /**
   * For internal, non private use
   */
   ParagraphFormats ep = null;
//=========================================================

   /**
   * Export a String with HTML encoding
   */
   protected void outputstr(String s){
      String s1 = "";

      for (int i = 0; i < s.length(); i++){
         char c = s.charAt(i);

         switch (c){
            case '&' : s1 = s1 + "&amp;"; break;
            case '<' : s1 = s1 + "&lt;";  break;
            case '>' : s1 = s1 + "&gt;";  break;
            default  : s1 = s1 + c;
         }
      }

      output(s1.toString());
   }
//=========================================================

   /**
   * Construct an exporter
   *
   * @param stream Canal stream
   */
   public HTMLExporter(OutputStream stream){
      super(stream);
   }
//=========================================================

   /**
   * Construct an exporter
   *
   * @param stream Canal stream
   * @param HTMLFileName HTML name
   * @param BackGround HTML background image
   */
   public HTMLExporter(OutputStream stream,
                       String HTMLFileName, String BackGround){
      this(stream);

      this.HTMLFileName = HTMLFileName;
      this.BackGround = BackGround;
   }
//=========================================================

   /**
   * Construct an exporter
   *
   * @param stream Canal stream
   * @param HTMLFileName HTML name
   * @param BackGround HTML background image
   * @param CharSet HTML language
   */
   public HTMLExporter(OutputStream stream,
                       String HTMLFileName, String BackGround,
                       String CharSet){
      this(stream, HTMLFileName, BackGround);

      this.CharSet = CharSet;
   }
//=========================================================

   /**
   * Construct an exporter
   *
   * @param stream Canal stream
   * @param HTMLFileName HTML name
   * @param BackGround HTML background image
   * @param CharSet HTML language
   * @param colors HTML colors
   * @param BaseURL HTML base URL
   * @param TargetFrame HTML target frame
   */
   public HTMLExporter(OutputStream stream,
                       String HTMLFileName, String BackGround,
                       String CharSet,
                       HTMLColors colors,
                       String BaseURL, String TargetFrame){
      this(stream, HTMLFileName, BackGround, CharSet);

      this.colors = colors;
      this.BaseURL = BaseURL;
      this.TargetFrame = TargetFrame;
   }
//=========================================================

   /**
   * Open exporter
   */
    public void open(){
       super.open();

      /*=================== HTML Secton ===================*/

       outputLn("<HTML>");

      //Please!!!!! Do not remove this :o)
      remark("This is an automat. generated file. Do Not Edit!!");
      remark("Generated with HTML Exporter (c) 1997,98 MAD Soft");
      remark("Http://WWW.NSI.BG/NMMM/home.htm");
      remark("E_Mail:NMMM@NSI.BG");

      /*=================== HEAD Secton ===================*/

      outputLn("<Head>");

      if (CharSet != null)
         outputLn("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=\"" + CharSet+ "\">");

      if (HTMLFileName != null)
         outputLn("<TITLE>" + HTMLFileName + "</TITLE>");

      if ((BaseURL != null    ) ||
         (TargetFrame != null)){
         output("<Base");

         if (BaseURL != null)
            output(" HRef=\"" + BaseURL + "\"");
         if (TargetFrame != null)
            output(" Target=\"" + TargetFrame + "\"");

         outputLn(">");
      }

      outputLn("</Head>");

      /*=================== BODY Secton ===================*/

      output("<Body");

      if (BackGround != null)
         output(" Background=\"" + BackGround + "\"");

      outputLn(colors.tag());

      outputLn(">");
    }
//=========================================================

   /**
   * Close exporter
   */
    public void close(){
       outputLn("</Body></HTML>");
       super.close();
    }
//=========================================================

   /**
   * Begin paragraph
   */
    public void beginParagraph(ParagraphFormats p) {
       ep = p;

       if (p == null) return;

       output(ep.tag(true));
    }
//=========================================================

   /**
   * Export paragraph
   */
    public    void paragraph(String s) {
       outputstr(s);
    }
//=========================================================

   /**
   * End paragraph
   */
    public void endparagraph() {
       if (ep != null)
          outputLn(ep.tag(false));
       ep = null;
    }
//=========================================================

   /**
   * Indent plus
   */
    public void indentPlus() {
       outputLn("<BlockQuote>");
    }
//=========================================================

   /**
   * Indent minus
   */
    public void indentMinus() {
       outputLn("</BlockQuote>");
    }
//=========================================================

   /**
   * Begin table.
   * Table is always with 100% width
   */
    public void beginTable(int tableborder) {
       outputLn("<TABLE Border=" + tableborder + " Width=100%>");
    }
//=========================================================

   /**
   * End table.
   */
    public void endTable() {
       outputLn("</TABLE>");
    }
//=========================================================

   /**
   * Begin table row
   */
    public void beginRow(){
       output("<TR>");
    }
//=========================================================

   /**
   * End table row
   */
    public void endRow(){
       outputLn("</TR>");
    }
//=========================================================

   /**
   * Begin table cell
   */
    public void begincell(){
       begincell(new ParagraphFormats());
    }
//=========================================================

   /**
   * Begin table cell
   */
    public void begincell(ParagraphFormats p){
       if (p == null) return;

       output("<TD" + p.atag() + ">" + p.fontstyle.tag(true));
    }
//=========================================================

   /**
   * End table cell
   */
    public void endcell(ParagraphFormats p){
       if (p == null) return;

       output(p.fontstyle.tag(false) + "</TD>");
    }
//=========================================================

   /**
   * Table cell
   */
    public void cell(String s, ParagraphFormats p){
       begincell(p);

       if (s == null)
          output("&nbsp;");
       else
          outputstr(s);

       endcell(p);
    }
//=========================================================

   /**
   * HTML Carriage return (Break)
   */
   public void CR(){
      outputLn("<Br>");
   }
//=========================================================

   /**
   * HTML Horizontal line
   */
   public void horizontalLine(){
      outputLn("<Hr>");
   }
//=========================================================

   /**
   * HTML hyper link
   */
   public void hyperLink(String text, String link, String target){
      output("<A");

      if (link != null)
         output(" HRef=\"" + link + "\"");

      if (target != null)
         output(" Name=\"" + target + "\"");

      output(">");
      outputstr(text);
      outputLn("</A>");
   }
//=========================================================

   /**
   * HTML remark
   */
   public void remark(String text){
      output("<!-- ");
      outputstr(text);
      outputLn(" -->");
   }
//=========================================================

   /**
   * HTML image
   */
   public void image(String image){
      output("<Img Src=\"" + image + "\">");
   }
//=========================================================

   /**
   * Begin HTML font
   */
   public void beginfont(HTMLFont a){
      if (a == null) return;

      output(a.tag(true));
   }
//=========================================================

   /**
   * End HTML font
   */
   public void endfont(HTMLFont a){
      if (a == null) return;

      output(a.tag(false));
   }
//=========================================================
}