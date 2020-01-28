package mainApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.IBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

@SuppressWarnings("unused")
public class Export {
Export(ArrayList<Bibliotheque.Livre> books) throws IOException{	
    String path = "Bibliotheque.docx";
	try(FileOutputStream out = new FileOutputStream(new File(path)))  {
	      //Blank Document
	      XWPFDocument document = new XWPFDocument(); 
	      
	      CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		  XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
		  
		//write header content
			CTP ctpHeader = CTP.Factory.newInstance();
		    CTR ctrHeader = ctpHeader.addNewR();
			CTText ctHeader = ctrHeader.addNewT();
			
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
			Date date = new Date();
			String tctDate = format.format(date);
			
			String headerText = tctDate+"  Bibliotheque ";
			ctHeader.setStringValue(headerText);	
			XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
		    XWPFParagraph[] parsHeader = new XWPFParagraph[1];
		    parsHeader[0] = headerParagraph;
		    policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
		   	    
	      XWPFParagraph para1 = document.createParagraph();
	      XWPFRun run1 = para1.createRun();
	  		
	  		
	  		
	      run1.setText("Bibliotheque");
	      run1.setFontSize(100);
	      para1.setAlignment(ParagraphAlignment.CENTER);
	      para1.setBorderBottom(Borders.BASIC_THIN_LINES);
	      para1.setBorderTop(Borders.BASIC_THIN_LINES);
	      para1.setPageBreak(true);
	      
	      
	      XWPFParagraph para2 = document.createParagraph();
	      XWPFRun run2 = para2.createRun();
	      run2.setText("Sommaire");
	      run2.setFontSize(16);
	      para2.setAlignment(ParagraphAlignment.CENTER);
	      para2.setPageBreak(true);
	      
	    //TABLEAU
          XWPFParagraph paragraphOutTitreTab = document.createParagraph();
          paragraphOutTitreTab.setAlignment(ParagraphAlignment.CENTER);
          XWPFRun titreTab = paragraphOutTitreTab.createRun();
          titreTab.isBold();
          titreTab.setFontSize(20);
          titreTab.setText("Livres prêtés");
          
          XWPFTable table = document.createTable();
          table.setWidth(8000);
          XWPFTableRow tableRowOne = table.getRow(0);
          tableRowOne.setHeight(1000);
          table.setTableAlignment(TableRowAlign.CENTER);

          tableRowOne.getCell(0).setText("Livre");
          tableRowOne.addNewTableCell().setText("Nom");

          //create second row 
          for (int i = 0; i < books.size(); i++) {
              if (books.get(i).etat.compareTo("Prêté") == 0
                      || books.get(i).etat.compareTo("Emprunté") == 0) {
                  XWPFTableRow tableRowTwo = table.createRow();
                  tableRowTwo.setHeight(1000);
                  tableRowTwo.getCell(0).setText(books.get(i).titre);
                  tableRowTwo.getCell(1).setText(books.get(i).personne);
              }
          }

          XWPFParagraph sautDePage = document.createParagraph();
          XWPFRun saut = sautDePage.createRun();
          saut.addBreak(BreakType.PAGE);
	     
   
	      //Write the Document in file system
//			XWPFRun imgrun = imgPg.createRun();
//			try {
//				String url = AuthorsInfo.get(4);
//				InputStream is = new URL(url).openStream();
//	    	    imgrun.addBreak();
//				imgrun.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(200), Units.toEMU(200));
//			} catch (InvalidFormatException | IOException  e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} 

			
			XWPFParagraph paras = document.createParagraph();
			XWPFRun run = paras.createRun();
			for (int i = 0; i <books.size(); i++ ) {
				
				run.setText(books.get(i).titre);
				run.addCarriageReturn();
				run.setText(books.get(i).auteur.nom + " " + books.get(i).auteur.prenom);
				run.addCarriageReturn();
				run.setText(books.get(i).presentation);
				run.addCarriageReturn();
				run.setText(books.get(i).url);
				run.addCarriageReturn();
				run.addCarriageReturn();
				run.addCarriageReturn();
				String url = books.get(i).url;
				InputStream is = new URL(url).openStream();
				run.addBreak();
				run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(200), Units.toEMU(200));
				run.addBreak();
			}
			
			document.write(out);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	
}
