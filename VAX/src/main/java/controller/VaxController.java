package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.VaxDAO;

@WebServlet("/")
public class VaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   public VaxController() {
	      super();
	   }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	      //한글을 정확히 출력하기 위해 UTF-8을 사용하였다
	      request.setCharacterEncoding("UTF-8");
	      doPro(request, response);
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	      //한글을 정확히 출력하기 위해 UTF-8을 사용하였다
	      request.setCharacterEncoding("UTF-8");
	      doPro(request, response);
	   }

	   // get OR post => doPro 실헹
	   protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      String context = request.getContextPath(); // 톰캣의 context path를 가져온다. (server.xml에서 확인 가능)
		      String command = request.getServletPath(); // 경로의 맨 끝 파일명을 가져온다.
		      String site = null;
		      System.out.println(context + ", " + command);
		      
		      VaxDAO vax = new VaxDAO();
		      
		      switch(command) {
		      case "/home":
		    	  site = "index.jsp"; break;
		      case "/reserve":
		    	  site = "reserve,jsp"; break;
		      case "/insert":
		    	 int result = vax.insert(request, response); 
		    	 response.setContentType("text/html; charset=UTF-8");
		         PrintWriter out = response.getWriter();
		         
		         if(result == 1) {
			            out.println("<script>");
			            out.println(" alert('접종예약정보가 등록 되었습니다.'); location.href='" + context + "';");
			            out.println("</script>");
			            out.flush();
			         } else {
			            out.println("<script>");
			            out.println(" alert('투표정보가 정상적으로 등록되지 않았습니다'); location.href='" + context + "';");
			            out.println("</script>");
			            out.flush();
			         } 
			    	break;	         
		      case "/list":
		    	  site = vax.list(request, response); break;
		      case "/statics":
		    	  site = vax.statics(request, response); break;
		      }
		      
		      getServletContext().getRequestDispatcher("/" + site).forward(request, response);
	   }
}
