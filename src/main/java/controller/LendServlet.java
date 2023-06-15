package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import bean.AlmondBean;
import bean.BookBean;
import bean.LendBean;
import bean.ReaderBean;
import dao.AlmondDao;
import dao.BookDao;
import dao.LendDao;
import dao.ReaderDao;
import interfaces.IAlmondDao;
import interfaces.IBookDao;
import interfaces.ILendDao;
import interfaces.IReaderDao;

@WebServlet(name="lendServlet", urlPatterns = {"/lends","*.lend"})
public class LendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int FIFTEEN_DAYS = 15;
	private static double AMOUNT = 5000;
	
	private ILendDao lendDao;
	private IBookDao bookDao;
	private IReaderDao readerDao;
	private IAlmondDao almondDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LendServlet() {
        super();
        lendDao = new LendDao();
        bookDao = new BookDao();
        readerDao = new ReaderDao();
        almondDao = new AlmondDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/lends")) {
			List<LendBean> lends = lendDao.findAll();
			request.setAttribute("lends", lends);
			request.getRequestDispatcher("views/lend/list.jsp").forward(request, response);
		}
		else if(path.equals("/new.lend")) {
			List<BookBean> books = bookDao.findByStatus(true);
			List<ReaderBean> readers = readerDao.findAllWhoCanLendABook();
			
			request.setAttribute("books", books);
			request.setAttribute("readers", readers);
			request.getRequestDispatcher("views/lend/form.jsp").forward(request, response);
		}
		else if(path.equals("/finished.lend")) {
			Long id = Long.parseLong(request.getParameter("id"));
			LendBean lend = lendDao.findOne(id);
			
			java.util.Date utilDate = new java.util.Date();
			Date todayDate = new Date(utilDate.getTime());
			
			long todayDateInMillisecond = todayDate.getTime();
			long lendEndDateInMillisecond = lend.getEndDate().getTime();
			
			long differenceInMillisecond = todayDateInMillisecond - lendEndDateInMillisecond;
			long differenceInDays = differenceInMillisecond / (24* 60*60*1000);
			
			BookBean book = bookDao.findOne(lend.getBook().getId());
			book.setStatus(!book.isStatus());
			
			ReaderBean reader = readerDao.findOne(lend.getReader().getId());
			reader.setNbPretActuel(reader.getNbPretActuel() - 1);
			
			lend.setFinished(!lend.isFinished());			

			lendDao.update(lend);
			bookDao.update(book);
			readerDao.update(reader);
			
			if(differenceInDays>7) {
				AlmondBean almond = new AlmondBean();
				almond.setAmount(AMOUNT);
				almond.setReader(lend.getReader());
				almondDao.create(almond);
				response.sendRedirect("almonds");
			}else {
				response.sendRedirect("lends");
			}
		}
		else if(path.equals("/delete.lend")) {
			Long id = Long.parseLong(request.getParameter("id"));
			lendDao.delete(id);
			response.sendRedirect("lends");
		}
		else {
			response.setStatus(Response.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long bookId = Long.parseLong(request.getParameter("book"));
		Long readerId = Long.parseLong(request.getParameter("reader"));
		Date startDate = Date.valueOf(request.getParameter("start_date"));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_YEAR,FIFTEEN_DAYS);
		
		Date endDate = new Date(calendar.getTimeInMillis());
		
		BookBean book = bookDao.findOne(bookId);
		ReaderBean reader = readerDao.findOne(readerId);
		
		LendBean lend = new LendBean(book, reader, startDate, endDate);
		lendDao.create(lend);
		
		book.setStatus(!book.isStatus());
		book.setNbPret(book.getNbPret() + 1);
		bookDao.update(book);
		
		reader.setNbPretActuel(reader.getNbPretActuel() + 1);
		reader.setNbPretTotal(reader.getNbPretTotal() + 1);
		readerDao.update(reader);
		
		response.sendRedirect("lends?updated=1");
	}

}
