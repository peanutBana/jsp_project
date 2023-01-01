package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.ToDoListDAO;
import DTO.ToDo;


@WebServlet("/")
public class ToDoListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ToDoListDAO dao;
	private ServletContext ctx;

    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new ToDoListDAO();
		ctx = getServletContext(); 
	}

	public ToDoListController() {
        super();
    }

      protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8"); // request 객체에 한글 깨짐 방지
         doPro(request, response);
      }

      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         doPro(request, response);
      }

      protected void doPro(HttpServletRequest request, HttpServletResponse response)
    	         throws ServletException, IOException{
    	  
    	  String context = request.getContextPath();
          String command = request.getServletPath();
          String site = null;
          
          switch(command) {
          case "/list":
          	site = getList(request);
          	break;
//          case "/insert":
//            	site = insertTodo(request);
//            	break;
          }
          
          if (site.startsWith("redirect:/")) {
              String rview = site.substring("redirect:/".length());
              System.out.println(rview);
              response.sendRedirect(rview);
           } else {
              ctx.getRequestDispatcher("/" + site).forward(request, response);
           }
      }
      
      public String getList(HttpServletRequest request) {
    	  List<ToDo> list;
    	  
    	  try {
			list = dao.getList();
			request.setAttribute("todoList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("ToDo List 불러오는 과정에서 문제발생!");
			request.setAttribute("error", "ToDo List 불러오기가 정상적으로 처리되지 않았습니다."); 
		}

    	  return "list.jsp";
      }
      
//      public String insertTodo(HttpServletRequest request) {
//    	  ToDo td = new ToDo();
//    	  dao.insertTodo();
//    	  
//    	  try {
//			BeanUtils.populate(td, request.getParameterMap());
//			
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
//      }
          
}
