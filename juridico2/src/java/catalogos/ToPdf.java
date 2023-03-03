/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalogos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.FontFactory;
import java.io.StringReader;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author karlos
 */
public class ToPdf {

    public static final String RESULT = "D:\\carlos\\vidal\\";
    Long id;
    String texto;
    int expano;
    int expro;
    String usuario;
    String ruta = "";
    
    private static Font fontBold = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
private static Font fontNormal = new Font(Font.FontFamily.COURIER, 5, Font.NORMAL);
    Font[] FONT = {
        new Font(FontFamily.HELVETICA, 24),
        new Font(FontFamily.HELVETICA, 18),
        new Font(FontFamily.HELVETICA, 14),
        new Font(FontFamily.HELVETICA, 12),
        new Font(FontFamily.HELVETICA, 12, Font.BOLD)
    };

    public ToPdf(Long id, String texto,int expano,int expro,String usuario) {
        this.id = id;
        this.texto = texto;
        this.ruta = RESULT + id + ".pdf";
        this.expano=expano;
        this.expro=expro;
        this.usuario=usuario;
        System.out.println("Aqui va el usuario: "+this.usuario);
        System.out.println("Aqui va el año: "+this.expano);
        System.out.println("Aqui va el progresivo: "+this.expro);
    }

    public void generarPdf() {
        //String oficialia="";
        
        texto=texto.substring(0,(texto.length()-7));
        texto+=usuario+"</p>";

    String oficialiaentrega="";
    String jefearchivo="";
    String jefejuridico="";
    int donde=0;
    int copias=0;
         Connection miConexion;
         try {
            miConexion=GetConnection();
             if(miConexion!=null)
        {
            
            System.out.println("Conexion realizada Correctamente");
            ResultSet rs = null;
            
            ResultSet rs3 = null;
            Statement cmd = null;
            cmd = miConexion.createStatement();

    rs = cmd.executeQuery("select * from scasol where expano="+expano+" and expro="+expro+";");   
    while (rs.next()) {
    
    System.out.println(rs.getString("ofi_id"));
        System.out.println("Arriva va el id de la oficalia");
        System.out.println("select o.descrip as oficialia,d.descc as distrito from scaofi o, scadto d where d.clv=o.clv_id and o.id="+rs.getString("ofi_id")+";");
      ResultSet rs2 = null;
            cmd = null;
            cmd = miConexion.createStatement();
        rs2=cmd.executeQuery("select o.descrip as oficialia,d.descc as distrito from scaofi o, scadto d where d.clv=o.clv_id and o.id="+rs.getString("ofi_id")+";");
while(rs2.next())
    {
        System.out.println("Aqui abajo va el resultado");
        System.out.println(rs2.getString("oficialia")+", "+rs2.getString("distrito"));
        oficialiaentrega=rs2.getString("oficialia")+", "+rs2.getString("distrito");
         
    }
   rs2.close();
    }
    
    rs.close();
            System.out.println("select donde from scaerr where expano="+this.expano+" and expro=(select id from scasol where expano="+this.expano+" and expro="+this.expro+") limit 0,1;");
            try {
                rs3=cmd.executeQuery("select donde from scaerr where expano="+this.expano+" and expro=(select id from scasol where expano="+this.expano+" and expro="+this.expro+") limit 0,1;");
        while(rs3.next())
    {
    donde=rs3.getInt("donde");
    }
    rs3.close();
                
                
            } catch (Exception e) {
                e.printStackTrace();
                    ResultSet rs10=null;
            try {
                rs10=cmd.executeQuery("select donde_id from scaact where expano="+this.expano+" and exppro=(select id from scasol where expano="+this.expano+" and expro="+this.expro+") limit 0,1;");
    while(rs10.next())
    {
    donde=rs10.getInt("donde_id");
    }
    rs10.close();
            } catch (Exception o) {
                o.printStackTrace();
            }
            }
            //rs2=cmd.executeQuery("select o.descrip as oficialia,d.descc as distrito from scaofi o, scadto d where d.clv=o.clv_id and o.id="+rs.getString("ofi_id")+";");


    
    ResultSet rs4=null;
    rs4=cmd.executeQuery("select j.titulo tituloj,j.nombre nombrej,j.ape_pat apepatj,j.ape_mat apematj,a.titulo tituloa,a.nombre nombrea,a.ape_pat apepata,a.ape_mat apemata  from jefearchivo a, jefejuridico j where a.activo=true and j.activo=true;");
    
    while(rs4.next())
    {
        System.out.println("Si va a buscar a los jefes");
        jefearchivo=rs4.getString("tituloa")+" "+rs4.getString("nombrea")+" "+rs4.getString("apepata")+" "+rs4.getString("apemata");
        jefejuridico=rs4.getString("tituloj")+" "+rs4.getString("nombrej")+" "+rs4.getString("apepatj")+" "+rs4.getString("apematj");
    }
    rs4.close();
    miConexion.close();
            System.out.println(jefearchivo);
            System.out.println(jefejuridico);
    //rs.
    //rs.close();
//rs2.close();
            System.out.println("Aqui va el donde para ver a donde se envia "+ donde);
//rs.close();
    
    
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            String k = texto;
            
            k=k.replaceAll("line-height: 1em","line-height: .8em");
           // k=k.replaceAll("8pt", "5pt");
           
            Document document = new Document(PageSize.LETTER, 20, 20, 5, 20);           
            PdfWriter pw=PdfWriter.getInstance(document, new FileOutputStream(RESULT + id + ".pdf"));            
            document.open();
            switch(donde)
            {
                case 1:                    
                    copias=2;
                    break;
                case 2:                     
                    copias=2;
                    break;
                case 3: 
                    copias=3;
                    break;
                default :
                    copias=3;
                    break;
                   
            }
            for(int i=0;i<copias;i++)
            {
            document.add(getBarcode(pw, expro, expano));
            Paragraph parrafo2 = new Paragraph("Expediente No.: "+expro+"/"+expano+"",FontFactory.getFont("arial",8));
    parrafo2.setAlignment(2);
    document.add(parrafo2);
           
            HTMLWorker htmlWorker = new HTMLWorker(document);
    htmlWorker.parse(new StringReader(k));    
            document.newPage();
                
               
            }
            /* String [] datos =k.split("\\.\\.\\.");
                System.out.println(datos.length);
                System.out.println(datos[1]);
                
                if(donde==1){
                Paragraph parrafo0 = new Paragraph("\n"
             + "",FontFactory.getFont("arial",10));
    parrafo0.setAlignment(0);
    document.add(parrafo0);
                Paragraph parrafo2 = new Paragraph("Expediente No.: "+expro+"/"+expano+"  "+oficialiaentrega,FontFactory.getFont("arial",10));
    parrafo2.setAlignment(0);
    document.add(parrafo2);
     Paragraph parrafo3 = new Paragraph("\n"
             + "",FontFactory.getFont("arial",10));
    parrafo3.setAlignment(0);
    document.add(parrafo3);          
    datos[1]="<p style=\"text-align: justify; line-height: 0.8em; font-size: 8pt\">"+"\"..."+datos[1]+"...\""+"</p>";        
    datos[1]=datos[1].replaceAll("font-size: 8pt", "font-size: 10pt");
    HTMLWorker htmlWorker = new HTMLWorker(document);
    htmlWorker.parse(new StringReader(datos[1]));
    document.add(new Paragraph("\n",
                FontFactory.getFont("arial",10)));
    
    document.add(new Paragraph("Comprobante pago de derechos:",
                FontFactory.getFont("arial",10)));           
                document.add(new Paragraph("Revisó: "+this.usuario,
                FontFactory.getFont("arial",10)));
                document.add(new Paragraph("Comprobante pago de derechos:",
                FontFactory.getFont("arial",10)));            
                document.add(new Paragraph("EL JEFE DE LA UNIDAD JURIDICA.                  "+"EL OFICIAL DEL REGISTRO CIVIL DE "+oficialiaentrega,
                FontFactory.getFont("arial",9)));
                document.add(new Paragraph("\n"
                        + jefejuridico+"\n"+"** Esta papeleta solamente es válida adherida al libro original **",
                FontFactory.getFont("arial",9)));
                document.newPage();
              }  
               if(donde==2)
               {
                               Paragraph parrafo01 = new Paragraph("\n"
             + "",FontFactory.getFont("arial",10));
    parrafo01.setAlignment(0);
    document.add(parrafo01);
                Paragraph parrafo21 = new Paragraph("Expediente No.: "+expro+"/"+expano+"  "+oficialiaentrega,FontFactory.getFont("arial",10));
    parrafo21.setAlignment(0);
    document.add(parrafo21);
     Paragraph parrafo31 = new Paragraph("\n"
             + "",FontFactory.getFont("arial",10));
    parrafo31.setAlignment(0);
    document.add(parrafo31);          
    HTMLWorker htmlWorker1 = new HTMLWorker(document);
    htmlWorker1.parse(new StringReader(datos[1]));
    document.add(new Paragraph("\n",
                FontFactory.getFont("arial",10)));
    document.add(new Paragraph("Comprobante pago de derechos:",
                FontFactory.getFont("arial",10)));             // color
                document.add(new Paragraph("Revisó: "+this.usuario,
                FontFactory.getFont("arial",10)));
                document.add(new Paragraph("Comprobante pago de derechos:",
                FontFactory.getFont("arial",10)));   
                PdfPTable table = new PdfPTable(3);
                table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                table.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.setWidthPercentage(100);  
                PdfPCell celda =new PdfPCell (new Paragraph("EL JEFE DE LA UNIDAD JURIDICA",
                FontFactory.getFont("arial",9)));                
                celda.setColspan(2);
                celda.setBorder(PdfPCell.NO_BORDER);                
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);               
                table.addCell(celda);
                PdfPCell celda2 =new PdfPCell (new Paragraph("EL JEFE DE ARCHIVO CENTRAL",
                FontFactory.getFont("arial",9)));
                
                celda2.setBorder(PdfPCell.NO_BORDER);               
                celda2.setHorizontalAlignment(Element.ALIGN_CENTER);               
                table.addCell(celda2);
                
                PdfPCell celda3 =new PdfPCell (new Paragraph("\n"
                        + "",
                FontFactory.getFont("arial",9)));
               
                celda3.setColspan(3);
                celda3.setBorder(PdfPCell.NO_BORDER);                
                table.addCell(celda3);
                
                PdfPCell celda4 =new PdfPCell (new Paragraph(jefejuridico+"\n ** Esta papeleta solamnete es válida adherida al libro original **",
                FontFactory.getFont("arial",9)));               
                celda4.setColspan(2);
                celda4.setBorder(PdfPCell.NO_BORDER);
                celda4.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(celda4);
                PdfPCell celda5 =new PdfPCell (new Paragraph(""+jefearchivo,
                FontFactory.getFont("arial",9)));
                
                celda5.setBorder(PdfPCell.NO_BORDER);
                celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
                //celda.se
                table.addCell(celda5);
                
             document.add(table);
               }
               if(donde==3)
               {
                    Paragraph parrafo0 = new Paragraph("\n"
             + "",FontFactory.getFont("arial",10));
    parrafo0.setAlignment(0);
    document.add(parrafo0);
                Paragraph parrafo2 = new Paragraph("Expediente No.: "+expro+"/"+expano+"  "+oficialiaentrega,FontFactory.getFont("arial",10));
    parrafo2.setAlignment(0);
    document.add(parrafo2);
     Paragraph parrafo3 = new Paragraph("\n"
             + "",FontFactory.getFont("arial",10));
    parrafo3.setAlignment(0);
    document.add(parrafo3);          
    datos[1]="<p style=\"text-align: justify; line-height: 0.8em; font-size: 8pt\">"+"\"..."+datos[1]+"...\""+"</p>";        
    datos[1]=datos[1].replaceAll("font-size: 8pt", "font-size: 10pt");
    HTMLWorker htmlWorker = new HTMLWorker(document);
    htmlWorker.parse(new StringReader(datos[1]));
    document.add(new Paragraph("\n",
                FontFactory.getFont("arial",10)));
   
    document.add(new Paragraph("Comprobante pago de derechos:",
                FontFactory.getFont("arial",10)));             // color
                document.add(new Paragraph("Revisó: "+this.usuario,
                FontFactory.getFont("arial",10)));
                document.add(new Paragraph("Comprobante pago de derechos:",
                FontFactory.getFont("arial",10)));
                document.add(new Paragraph("EL JEFE DE LA UNIDAD JURIDICA.                  "+"EL OFICIAL DEL REGISTRO CIVIL DE "+oficialiaentrega,
                FontFactory.getFont("arial",9)));
                document.add(new Paragraph("\n"
                        + jefejuridico+"\n"+"** Esta papeleta solamente es válida adherida al libro original **",
                FontFactory.getFont("arial",9)));
                document.newPage();
                
                
                                               Paragraph parrafo01 = new Paragraph("\n"
             + "",FontFactory.getFont("arial",10));
    parrafo01.setAlignment(0);
    document.add(parrafo01);
                Paragraph parrafo21 = new Paragraph("Expediente No.: "+expro+"/"+expano+"  "+oficialiaentrega,FontFactory.getFont("arial",10));
    parrafo21.setAlignment(0);
    document.add(parrafo21);
     Paragraph parrafo31 = new Paragraph("\n"
             + "",FontFactory.getFont("arial",10));
    parrafo31.setAlignment(0);
    document.add(parrafo31);          
    HTMLWorker htmlWorker1 = new HTMLWorker(document);
    htmlWorker1.parse(new StringReader(datos[1]));
    document.add(new Paragraph("\n",
                FontFactory.getFont("arial",10)));   
    document.add(new Paragraph("Comprobante pago de derechos:",
                FontFactory.getFont("arial",10)));
                document.add(new Paragraph("Revisó: "+this.usuario,
                FontFactory.getFont("arial",10)));
                document.add(new Paragraph("Comprobante pago de derechos:",
                FontFactory.getFont("arial",10)));               
                
                PdfPTable table = new PdfPTable(3);
                table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                table.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.setWidthPercentage(100);
                            
                
                PdfPCell celda =new PdfPCell (new Paragraph("EL JEFE DE LA UNIDAD JURIDICA",
                FontFactory.getFont("arial",9)));
               
                celda.setColspan(2);
                celda.setBorder(PdfPCell.NO_BORDER);
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);               
                table.addCell(celda);
                PdfPCell celda2 =new PdfPCell (new Paragraph("EL JEFE DE ARCHIVO CENTRAL",
                FontFactory.getFont("arial",9)));
                
                celda2.setBorder(PdfPCell.NO_BORDER);               
                celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celda2);                
                PdfPCell celda3 =new PdfPCell (new Paragraph("\n"
                        + "",
                FontFactory.getFont("arial",9)));                
                celda3.setColspan(3);
                celda3.setBorder(PdfPCell.NO_BORDER);               
                table.addCell(celda3);
                
                PdfPCell celda4 =new PdfPCell (new Paragraph(jefejuridico+"\n ** Esta papeleta solamnete es válida adherida al libro original **",
                FontFactory.getFont("arial",9)));
               
                celda4.setColspan(2);
                celda4.setBorder(PdfPCell.NO_BORDER);                
                celda4.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                table.addCell(celda4);
                PdfPCell celda5 =new PdfPCell (new Paragraph(""+jefearchivo,
                FontFactory.getFont("arial",9)));
                
                celda5.setBorder(PdfPCell.NO_BORDER);
                celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(celda5);
             document.add(table);
               
               }*/
                
            document.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
 
    public String getRuta() {
        return ruta;
    }

    public String buscarTitulo() {
        return texto.substring(texto.indexOf("<h1>") + 4, texto.indexOf("</h1>"));
    }

    public String[] getParrafos() {
        String temp = texto.substring(texto.indexOf("<p>"));
        return temp.split("<p>");
    }
    
    

    
    
     private static Image getBarcode( PdfWriter pdfWriter, int expro,int  expano){
     PdfContentByte cimg = pdfWriter.getDirectContent();
     Barcode128 code128 = new Barcode128();
     code128.setCode(expro +"/"+expano);
     code128.setCodeType(Barcode128.CODE128);
     //code128.set
  //code128.setTextAlignment(Element.ALIGN_CENTER);
  Image image = code128.createImageWithBarcode(cimg, null, BaseColor.WHITE);
  //float scaler = ((document.getPageSize().getWidth() - document.leftMargin()  - document.rightMargin() - 0) / image.getWidth()) * 70;
  //image.scalePercent(scaler);
  image.scaleToFit(50,50);
  image.setAlignment(Element.ALIGN_RIGHT);
  return image;
 }
     
     
     
     
     
     
     
     
     
     
     public static Connection GetConnection()
    {
        Connection conexion=null;
      
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://172.19.1.26/juridico";
            String usuarioDB="juridico";
            String passwordDB="c0mp4t1bl3";
            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        }
        catch(ClassNotFoundException ex)
        {
            //JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            System.out.println("Error1 en la Conexión con la BD");
            conexion=null;
        }
        catch(SQLException ex)
        {
           // JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
             System.out.println("Error2 en la Conexión con la BD");
            conexion=null;
        }
        catch(Exception ex)
        {
            //JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            System.out.println("Error3 en la Conexión con la BD");
            conexion=null;
        }
        finally
        {
            return conexion;
        }
    }
     
     
     
}
