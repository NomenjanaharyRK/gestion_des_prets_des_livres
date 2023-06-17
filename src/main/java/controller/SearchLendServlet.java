package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import bean.LendBean;
import bean.ReaderBean;
import dao.LendDao;
import dao.ReaderDao;
import interfaces.ILendDao;
import interfaces.IReaderDao;

/**
 * Servlet implementation class SearchLendServlet
 */
@WebServlet(name="searchLendServlet", urlPatterns = {"/search","*.search"})
public class SearchLendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IReaderDao readerDao;
	private ILendDao lendDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLendServlet() {
        super();
        readerDao = new ReaderDao();
        lendDao = new LendDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		List<ReaderBean> readers = readerDao.findAll();
		request.setAttribute("readers", readers);
		if(path.equals("/search")) {
			request.getRequestDispatcher("views/lend/search.jsp").forward(request, response);
		}
		else if(path.equals("/month.search")) {
			Long readerId = Long.parseLong(request.getParameter("reader"));
			String month = request.getParameter("month");
			ReaderBean reader = readerDao.findOne(readerId);
			List<LendBean> lends = lendDao.findAllInAMonth(month, readerId);
			
			request.setAttribute("reader", reader);
			request.setAttribute("month", month);
			request.setAttribute("lends", lends);
			request.getRequestDispatcher("views/lend/search.jsp").forward(request, response);
		}
		else if(path.equals("/year.search")) {
			Long readerId = Long.parseLong(request.getParameter("reader"));
			String year = request.getParameter("year");
			ReaderBean reader = readerDao.findOne(readerId);
			List<LendBean> lends = lendDao.findAllInAYear(year, readerId);
			
			request.setAttribute("reader", reader);
			request.setAttribute("year", year);
			request.setAttribute("lends", lends);
			request.getRequestDispatcher("views/lend/search.jsp").forward(request, response);
		}
		else if(path.equals("/between_date.search")) {
			Long readerId = Long.parseLong(request.getParameter("reader"));
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			ReaderBean reader = readerDao.findOne(readerId);
			List<LendBean> lends = lendDao.findAllBetweenTwoDate(readerId, start_date, end_date);
			
			request.setAttribute("reader", reader);
			request.setAttribute("start_date", start_date);
			request.setAttribute("end_date", end_date);
			request.setAttribute("lends", lends);
			request.getRequestDispatcher("views/lend/search.jsp").forward(request, response);
		}
		else {
			response.setStatus(Response.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
