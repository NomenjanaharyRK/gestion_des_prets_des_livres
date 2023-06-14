package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.BookBean;
import bean.ReaderBean;
import dao.ReaderDao;
import interfaces.IReaderDao;

/**
 * Servlet implementation class ReaderServlet
 */
@WebServlet(name="readerServlet", urlPatterns = {"/readers","*.reader"})
public class ReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IReaderDao readerDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReaderServlet() {
        super();
        readerDao = new ReaderDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/readers")) {
			List<ReaderBean> readers = readerDao.findAll();
			request.setAttribute("readers", readers);
			request.getRequestDispatcher("views/reader/list.jsp").forward(request, response);
		}
		else if(path.equals("/new.reader")) {
			request.getRequestDispatcher("views/reader/form.jsp").forward(request, response);
		}
		else if(path.equals("/update.reader")) {
			Long id = Long.parseLong(request.getParameter("id"));
			ReaderBean reader = readerDao.findOne(id);
			request.setAttribute("reader", reader);
			request.getRequestDispatcher("views/reader/form.jsp").forward(request, response);
		}
		else if (path.equals("/info.reader")){
			Long id = Long.parseLong(request.getParameter("id"));
			ReaderBean reader = readerDao.findOne(id);
			request.setAttribute("reader", reader);
			request.getRequestDispatcher("views/reader/info.jsp").forward(request, response);			
		}
		else if(path.equals("/delete.reader")) {
			Long id = Long.parseLong(request.getParameter("id"));
			readerDao.delete(id);
			response.sendRedirect("readers");
		}
		else {
			response.setStatus(Response.SC_NOT_FOUND);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/new.reader")) {
			HashMap<String, String> fields = new HashMap<>();
			String illustration;
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletContext servletContext = this.getServletConfig().getServletContext();
				File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				factory.setRepository(repository);
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				
				while(iter.hasNext()) {
					FileItem item = iter.next();
					
					if(item.isFormField()) {
						fields.put(item.getFieldName(), item.getString());
					}
					else {
						illustration = item.getName();
						fields.put("illustration", illustration);
						if(illustration == null || illustration.equals("")) {
							break;
						}else {
				    		Path filePath = Paths.get(illustration);
				    		String storePath = this.getServletContext().getRealPath("/uploads");
				    		File uploadFile = new File(storePath + "/" + filePath.getFileName());
				    		item.write(uploadFile);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String name = fields.get("name");
			String lastname = fields.get("lastname");
			String address = fields.get("address");
			String phone = fields.get("phone");
			String email = fields.get("email");
			String cin = fields.get("cin");
			illustration = fields.get("illustration");
			
			ReaderBean reader = new ReaderBean(name,lastname,email,phone, address, illustration, cin);
			
			readerDao.create(reader);
				
			response.sendRedirect("readers");
		}
		else if(path.equals("/update.reader")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String name = request.getParameter("name");
			String lastname = request.getParameter("lastname");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String cin = request.getParameter("cin");
			
			ReaderBean reader = readerDao.findOne(id);
			reader.setName(name);
			reader.setLastname(lastname);
			reader.setAddress(address);
			reader.setPhone(phone);
			reader.setEmail(email);
			reader.setCin(cin);
			
			readerDao.update(reader);
			response.sendRedirect("readers");
		}
		else {
			response.setStatus(Response.SC_NOT_FOUND);
		}
	}

}
