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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.BookBean;
import dao.BookDao;
import interfaces.IBookDao;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet(name="bookServlet", urlPatterns = {"/books","*.book"})
@MultipartConfig
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IBookDao bookDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        bookDao = new BookDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/books")) {
			List<BookBean> books = bookDao.findAll();
			request.setAttribute("books", books);
			request.getRequestDispatcher("views/books/list.jsp").forward(request, response);
		}
		else if(path.equals("/search.book")) {
			String key = request.getParameter("key");
			List <BookBean> books = bookDao.searchByTitleOrAuthor("%"+ key + "%");
			request.setAttribute("books", books);
			request.setAttribute("key", key);
			request.getRequestDispatcher("views/books/list.jsp").forward(request, response);
		}
		else if(path.equals("/new.book")) {
			request.getRequestDispatcher("views/books/form.jsp").forward(request, response);
		}
		else if(path.equals("/info.book")) {
			Long id = Long.parseLong(request.getParameter("id"));
			BookBean book = bookDao.findOne(id);
			request.setAttribute("book", book);
			request.getRequestDispatcher("views/books/info.jsp").forward(request, response);
		}
		else if(path.equals("/update.book")) {
			Long id = Long.parseLong(request.getParameter("id"));
			BookBean book = bookDao.findOne(id);
			request.setAttribute("book", book);
			request.getRequestDispatcher("views/books/form.jsp").forward(request, response);
		}
		else if(path.equals("/delete.book")) {
			Long id = Long.parseLong(request.getParameter("id"));
			bookDao.delete(id);
			response.sendRedirect("books");
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
		
		if(path.equals("/new.book")) {
			HashMap<String, String> fields = new HashMap<>();
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
						String illustration = item.getName();
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
			String title = fields.get("title");
			String description = fields.get("description");
			String author = fields.get("author");
			String publishedAt = fields.get("published_at");
			String illustration = fields.get("illustration");
			
			BookBean book = new BookBean();
			book.setTitle(title);
			book.setAuthor(author);
			book.setDescription(description);
			book.setPublishedAt(publishedAt);
			book.setIllustration(illustration);
			
			bookDao.create(book);
			
			response.sendRedirect("books");
		}
		else if(path.equals("/update.book")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String description = request.getParameter("description");
			String publishedAt = request.getParameter("published_at");
				
			BookBean book = bookDao.findOne(id);
			book.setTitle(title);
			book.setAuthor(author);
			book.setDescription(description);
			book.setPublishedAt(publishedAt);
			
			bookDao.update(book);
			response.sendRedirect("books");
		}
		else {
			response.setStatus(Response.SC_NOT_FOUND);
		}
	}

}
