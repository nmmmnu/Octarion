package madsoft.exporter;

import java.io.*;

/**
*Exporters, Java Version 4.0 beta
*<pre>
*Copyleft 1997 / April.1998
*Autor: Nikolay Mihaylov
*eMail: nmmm@nsi.bg
*       nmmm@hotmail.com
*http : www.nsi.bg/nmmm
*http : 195.138.134.90
*
*Notes:
*======
* o Exporters avail in Delphi Verson too
* o Delphi version is first and original version. If you are Delphi
*   programmer, you can found it on my web page.
* o Delphi HTML exporter component is not free!!!
* o "Canals" are not available in Java version, it use Java streams.
* o Java Exporters hyerarchy is not that big like Delphi Exporters,
*   because there are only Internet - related format exportrs.
*</pre>
*
* Abstract exporter is base object of all exporters.
* <p>You can use it directly, because it is abstract.
* <p>See bellow for details
*/
abstract public class AbstractExporter{
   /**
   * Exporter canal
   */
   protected PrintStream canal;
//=========================================================

   /**
   * Costruct a exporter with a canal
   * @param stream a stream for creating the canal
   */
   public AbstractExporter(OutputStream stream){
      super();
      canal = new PrintStream(stream);
   }
//=========================================================

   /**
   * Output data to the canal
   * @param s the data
   */
   protected void output(String s){
      canal.print(s);
   }
//=========================================================

   /**
   * OutputLn to the canal
   */
   protected void outputLn(){
      canal.println();
   }
//=========================================================

   /**
   * OutputLn data to the canal
   * @param s the data
   */
   protected void outputLn(String s){
      output(s);
      outputLn();
   }
//=========================================================

   /**
   * Open the exporter.
   * Every exporter must be opened before use
   */
   public void open(){
   }
//=========================================================

   /**
   * Close the exporter.
   * Every exporter must be closed after use
   */
   public void close(){
      canal.close();
   }
//=========================================================

   /**
   * Begin paragraph
   */
   public void beginParagraph() {
      beginParagraph(new ParagraphFormats());
   }
//=========================================================

   /**
   * Begin paragraph with ParagraphFormats
   * @param p ParagraphFormats
   */
   public void beginParagraph(ParagraphFormats p) {}
//=========================================================

   /**
   * Paragraph output
   * @param s the data
   */
   public    void paragraph(String s) {
      output(s);
   }
//=========================================================

   /**
   * End paragraph
   */
   public void endParagraph() {}
//=========================================================

   /**
   * Begin paragraph, export data, then end paragraph with ParagraphFormats
   * @param s the data
   * @param p ParagraphFormats
   */
   public    void quickParagraph(String s, ParagraphFormats p){
      beginParagraph(p);
      paragraph(s);
      endParagraph();
   }
//=========================================================

   /**
   * Begin paragraph, export data, then end paragraph
   * @param s the data
   */
   public    void quickParagraph(String s){
      quickParagraph(s, new ParagraphFormats());
   }
//=========================================================

   /**
   * Indent plus.
   */
   public void indentPlus() {}

   /**
   * Indent minus.
   */
   public void indentMinus() {}
//=========================================================

   /**
   * Begin table
   * @param tableborder Width of table border
   */
   public void beginTable(int tableborder) {}

   /**
   * End table
   */
   public void endTable() {}
//=========================================================

   /**
   * Begin table row
   */
   public void beginRow(){
      beginParagraph();
   }
//=========================================================

   /**
   * End table row
   */
   public void endRow(){
      endParagraph();
   }
//=========================================================

   /**
   * Table cell
   * @param s the data
   */
   public void cell(String s){
     cell(s, new ParagraphFormats());
   }
//=========================================================

   /**
   * Table cell with ParagraphFormats
   * @param s the data
   * @param p ParagraphFormats
   */
   public void cell(String s, ParagraphFormats p){
      paragraph(s);
   }
//=========================================================
}
