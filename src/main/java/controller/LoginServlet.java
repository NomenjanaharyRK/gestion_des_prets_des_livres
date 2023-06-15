package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LoginBean;
import dao.LoginDao;
import interfaces.ILoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="loginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ILoginDao loginDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        loginDao = new LoginDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int logout = 0;
		if(request.getParameter("logout")!= null) {
			logout = Integer.parseInt(request.getParameter("logout"));
		}
		
		if(logout == 1) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i =0; i < cookies.length; i++) {
					if(cookies[i].getName().equals("username")) {
						Cookie cookie = cookies[i];
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						response.sendRedirect("login");
					}
				}
			}
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);									
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		boolean isUserValid = loginDao.loginUser(loginBean);
		
		if(isUserValid) {
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(3600*4);
			cookie.setPath("/");
			response.addCookie(cookie);
			response.sendRedirect("books");
		}else {
			request.setAttribute("message", "Information invalide");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
