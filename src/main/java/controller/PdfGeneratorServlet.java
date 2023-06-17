package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bean.LendBean;
import bean.ReaderBean;
import dao.LendDao;
import dao.ReaderDao;
import interfaces.ILendDao;
import interfaces.IReaderDao;

/**
 * Servlet implementation class PdfGeneratorServlet
 */
@WebServlet(name="pdfGeneratorServlet",urlPatterns = {"*.pdf"})
public class PdfGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private IReaderDao readerDao;
	private ILendDao lendDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfGeneratorServlet() {
        super();
        readerDao = new ReaderDao();
        lendDao = new LendDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long readerId = Long.parseLong(request.getParameter("reader"));
		ReaderBean reader = readerDao.findOne(readerId);
		String path = request.getServletPath();
		if(path.equals("/month.pdf")) {
			String month = request.getParameter("month");
			List<LendBean> lends = lendDao.findAllInAMonth(month, readerId);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachement; filename=\"liste.pdf\"");
			
			try {
				Document document = new Document();
				PdfWriter.getInstance(document, response.getOutputStream());
				
				//ouverture du document
				document.open();
				
	            // Ajouter un titre au document
	            Paragraph title = new Paragraph("Information du lecteur", new Font(FontFamily.HELVETICA, 18, Font.BOLD));
	            title.setAlignment(Element.ALIGN_CENTER);
	            title.setSpacingAfter(20);
	            document.add(title);
	            
	            Paragraph idP = new Paragraph("Numero : " + reader.getId(),new Font(FontFamily.HELVETICA,12) );
	            Paragraph nameP = new Paragraph("Nom : " + reader.getName() ,new Font(FontFamily.HELVETICA,12) );
	            Paragraph lastnameP = new Paragraph("Prenom : " + reader.getLastname(),new Font(FontFamily.HELVETICA,12) );
	            
	            idP.setAlignment(Element.ALIGN_CENTER);
	            nameP.setAlignment(Element.ALIGN_CENTER);
	            lastnameP.setAlignment(Element.ALIGN_CENTER);
	            lastnameP.setSpacingAfter(10);
	            
	            document.add(idP);
	            document.add(nameP);
	            document.add(lastnameP);
	            
	            Paragraph title2 = new Paragraph("Liste des prêts effectuer dans un mois", new Font(FontFamily.HELVETICA, 18, Font.BOLD));
	            title2.setAlignment(Element.ALIGN_CENTER);
	            title2.setSpacingAfter(20);
	            document.add(title2);
				
//	            ajouter un tableau
	            PdfPTable table = new PdfPTable(6);
	            
	            table.addCell(createCell("Id", true));
	            table.addCell(createCell("Livre", true));
	            table.addCell(createCell("Autheur", true));
	            table.addCell(createCell("Date de pret", true));
	            table.addCell(createCell("Date de retour", true));
	            table.addCell(createCell("Status", true));
	            
//	            ajouter les données du tableau
	            for (LendBean lend : lends) {
		            table.addCell(createCell(String.valueOf(lend.getId()), false));
		            table.addCell(createCell(lend.getBook().getTitle(), false));
		            table.addCell(createCell(lend.getBook().getAuthor(), false));
		            table.addCell(createCell(lend.getStartDate().toString(), false));
		            table.addCell(createCell(lend.getEndDate().toString(), false));
		            String status;
		            if(lend.isFinished()) {
		            	status = "Terminer";
		            }else {
		            	status = "En cours";
		            }
		            table.addCell(createCell(status, false));
				}
	            document.add(table);
	            
	            //fermeture du document
	            document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(path.equals("/year.pdf")) {
			String year = request.getParameter("year");
			List<LendBean> lends = lendDao.findAllInAYear(year, readerId);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachement; filename=\"liste.pdf\"");
			
			try {
				Document document = new Document();
				PdfWriter.getInstance(document, response.getOutputStream());
				
				//ouverture du document
				document.open();
	            // Ajouter un titre au document
	            Paragraph title = new Paragraph("Information du lecteur", new Font(FontFamily.HELVETICA, 18, Font.BOLD));
	            title.setAlignment(Element.ALIGN_CENTER);
	            title.setSpacingAfter(20);
	            document.add(title);
	            
	            Paragraph idP = new Paragraph("Numero : " + reader.getId(),new Font(FontFamily.HELVETICA,12) );
	            Paragraph nameP = new Paragraph("Nom : " + reader.getName() ,new Font(FontFamily.HELVETICA,12) );
	            Paragraph lastnameP = new Paragraph("Prenom : " + reader.getLastname(),new Font(FontFamily.HELVETICA,12) );
	            
	            idP.setAlignment(Element.ALIGN_CENTER);
	            nameP.setAlignment(Element.ALIGN_CENTER);
	            lastnameP.setAlignment(Element.ALIGN_CENTER);
	            lastnameP.setSpacingAfter(10);
	            
	            document.add(idP);
	            document.add(nameP);
	            document.add(lastnameP);
	            
	            Paragraph title2 = new Paragraph("Liste des prêts effectuer dans un an", new Font(FontFamily.HELVETICA, 18, Font.BOLD));
	            title2.setAlignment(Element.ALIGN_CENTER);
	            title2.setSpacingAfter(20);
	            document.add(title2);
	            
//	            ajouter un tableau
	            PdfPTable table = new PdfPTable(6);
	            
	            table.addCell(createCell("Id", true));
	            table.addCell(createCell("Livre", true));
	            table.addCell(createCell("Autheur", true));
	            table.addCell(createCell("Date de pret", true));
	            table.addCell(createCell("Date de retour", true));
	            table.addCell(createCell("Status", true));
	            
//	            ajouter les données du tableau
	            for (LendBean lend : lends) {
		            table.addCell(createCell(String.valueOf(lend.getId()), false));
		            table.addCell(createCell(lend.getBook().getTitle(), false));
		            table.addCell(createCell(lend.getBook().getAuthor(), false));
		            table.addCell(createCell(lend.getStartDate().toString(), false));
		            table.addCell(createCell(lend.getEndDate().toString(), false));
		            String status;
		            if(lend.isFinished()) {
		            	status = "Terminer";
		            }else {
		            	status = "En cours";
		            }
		            table.addCell(createCell(status, false));
				}
	            document.add(table);
	            
	            //fermeture du document
	            document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(path.equals("/between.pdf")) {
			String start = request.getParameter("start_date");
			String end = request.getParameter("end_date");
			List<LendBean> lends = lendDao.findAllBetweenTwoDate(readerId, start, end);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachement; filename=\"liste.pdf\"");
			
			try {
				Document document = new Document();
				PdfWriter.getInstance(document, response.getOutputStream());
				
				//ouverture du document
				document.open();
	            // Ajouter un titre au document
	            Paragraph title = new Paragraph("Information du lecteur", new Font(FontFamily.HELVETICA, 18, Font.BOLD));
	            title.setAlignment(Element.ALIGN_CENTER);
	            title.setSpacingAfter(20);
	            document.add(title);
	            
	            Paragraph idP = new Paragraph("Numero : " + reader.getId(),new Font(FontFamily.HELVETICA,12) );
	            Paragraph nameP = new Paragraph("Nom : " + reader.getName() ,new Font(FontFamily.HELVETICA,12) );
	            Paragraph lastnameP = new Paragraph("Prenom : " + reader.getLastname(),new Font(FontFamily.HELVETICA,12) );
	            
	            idP.setAlignment(Element.ALIGN_CENTER);
	            nameP.setAlignment(Element.ALIGN_CENTER);
	            lastnameP.setAlignment(Element.ALIGN_CENTER);
	            lastnameP.setSpacingAfter(10);
	            
	            document.add(idP);
	            document.add(nameP);
	            document.add(lastnameP);
	            
	            Paragraph title2 = new Paragraph("Liste des prêts effectuer", new Font(FontFamily.HELVETICA, 18, Font.BOLD));
	            title2.setAlignment(Element.ALIGN_CENTER);
	            title2.setSpacingAfter(20);
	            document.add(title2);
				
//	            ajouter un tableau
	            PdfPTable table = new PdfPTable(6);
	            
	            table.addCell(createCell("Id", true));
	            table.addCell(createCell("Livre", true));
	            table.addCell(createCell("Autheur", true));
	            table.addCell(createCell("Date de pret", true));
	            table.addCell(createCell("Date de retour", true));
	            table.addCell(createCell("Status", true));
	            
//	            ajouter les données du tableau
	            for (LendBean lend : lends) {
		            table.addCell(createCell(String.valueOf(lend.getId()), false));
		            table.addCell(createCell(lend.getBook().getTitle(), false));
		            table.addCell(createCell(lend.getBook().getAuthor(), false));
		            table.addCell(createCell(lend.getStartDate().toString(), false));
		            table.addCell(createCell(lend.getEndDate().toString(), false));
		            String status;
		            if(lend.isFinished()) {
		            	status = "Terminer";
		            }else {
		            	status = "En cours";
		            }
		            table.addCell(createCell(status, false));
				}
	            document.add(table);
	            
	            //fermeture du document
	            document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			response.setStatus(Response.SC_NOT_FOUND);
		}
	}
	
	private static PdfPCell createCell(String content, boolean header) {
		PdfPCell cell = new PdfPCell(new Paragraph(content));

		if(header) {
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		
		return cell;
	}

}
