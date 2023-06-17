package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import bean.AlmondBean;
import dao.AlmondDao;
import interfaces.IAlmondDao;

/**
 * Servlet implementation class AlmondServlet
 */
@WebServlet(name="almondServlet", urlPatterns = {"/almonds","*.almond"})
public class AlmondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IAlmondDao almondDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlmondServlet() {
        super();
        almondDao = new AlmondDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/almonds")) {
			List<AlmondBean> almonds = almondDao.findAll();
			request.setAttribute("almonds", almonds);
			request.getRequestDispatcher("views/almond/list.jsp").forward(request, response);	
		}
		else if(path.equals("/payed.almond")) {
			Long id = Long.parseLong(request.getParameter("id"));
			AlmondBean almond = almondDao.findOne(id);
			almond.setPayed(!almond.isPayed());
			almondDao.update(almond);
			response.sendRedirect("almonds");
		}
		else if(path.equals("/delete.almond")) {
			Long id = Long.parseLong(request.getParameter("id"));
			almondDao.delete(id);
			response.sendRedirect("almonds");
		}
		else {
			response.setStatus(Response.SC_NOT_FOUND);
		}
	}

}
