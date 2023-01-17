package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import DAO.ToDoListDAO;
import DTO.ToDo;
import DTO.User;

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
          
          request.setCharacterEncoding("utf-8");  
         
          switch(command) {
          case "/list":
          	site = getList(request);			//db에 저장된 todo list불러오기
          	break;
          case "/insert":
           	site = insertTodo(request);			//새로운 todo db에 삽입하기
           	break;
          case "/view":
             	site = getView(request);		//todo 상세화면 불러오기
             	break;
          case "/edit":
           	site = getViewForEdit(request);		//todo 상세화면 불러오기
           	break;
          case "/delete":
             site = deleteTodo(request);		//todo 삭제하기
             break;
          case "/update":
        	  site = updateTodo(request);		//todo 업데이트
        	  break;   
          case "/chkupdate":
        	  site = chkUpdate(request);		//todo 업데이트
        	  break;   
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
    	  List<ToDo> list_fin;
    	  List<User> userList;
    	  
    	  //index에서 받아온 id, name저장
    	  if(request.getParameter("userid") != null && request.getParameter("username") != null) {
    		  int id = Integer.parseInt(request.getParameter("userid"));
    		  String name = request.getParameter("username");
    		  
			  HttpSession session = request.getSession();
    		  //세션에 id, name 저장
    		  session.setAttribute("id", id);
    		  session.setAttribute("name", name);
			
    	  }    
    	    
    	  try {
    		//수행 전 todo list
			list = dao.getList();
			request.setAttribute("todoList", list);
			
			//실행 후 todo list
			list_fin = dao.getListFin();
			request.setAttribute("todoListFin", list_fin);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("ToDo List 불러오는 과정에서 문제발생!");
			request.setAttribute("error", "ToDo List 불러오기가 정상적으로 처리되지 않았습니다."); 
		}

    	  return "list.jsp";
      }
      
      public String getView(HttpServletRequest request) {
    	  int todoId = Integer.parseInt(request.getParameter("todoId"));
    	  
    	  try {
    		  ToDo td = dao.getView(todoId);
    		  request.setAttribute("todo", td);
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	      ctx.log("Todo를 가져오는 과정에서 문제 발생");
    	      request.setAttribute("error", "Todo가 정상적으로 처리되지 않았습니다!");
    	  }
    	  
    	  return "view.jsp";
      }
      
      public String insertTodo(HttpServletRequest request) {
    	  ToDo td = new ToDo();
    	  
    	  try {
			td.setTodoTitle(request.getParameter("todo"));
			td.setUserId(Integer.parseInt(request.getParameter("id")));
			dao.insertTodo(td);
			
    	} catch (Exception e) {
			e.printStackTrace();
	         ctx.log("추가 과정에서 문제 발생");
	         
	         try {
	             String encodeName = URLEncoder.encode("게시물이 정상적으로 등록되지 않았습니다!", "UTF-8");
	             return "redirect:/list?error=" + encodeName;
	          } catch (UnsupportedEncodingException e1) {
	             e1.printStackTrace();
	          }
	          // 사용자 한테 에러메시지 보여주기 위해 저장ㅌㅌㅌ
	          request.setAttribute("error", "todo가 정상적으로 등록되지 않았습니다!");
	          return getList(request);
		}
    	  return "redirect:/list";
      }
      
      String getViewForEdit(HttpServletRequest request) {
    	  int todoId = Integer.parseInt(request.getParameter("todoId"));
    	  
    	  try {
    		  ToDo td = dao.getView(todoId);
    		  request.setAttribute("todo", td);
    	  }catch (Exception e) {
    		  e.printStackTrace();
    	      ctx.log("게시글을 가져오는 과정에서 문제 발생");
    	      request.setAttribute("error", "Todo가 정상적으로 처리되지 않았습니다.");
    	  }
    	  return "edit.jsp";
      }
      
      public String deleteTodo(HttpServletRequest request) {
    	  int todoId = Integer.parseInt(request.getParameter("todoId"));
    	  
    	  try {
   		   dao.deleteTodo(todoId);
   	   }catch(Exception e) {
   		   e.printStackTrace();
   		   ctx.log("Todo을 삭제하는 과정에서 문제 발생");
   		   String encodeName;
   		   try {
   			   encodeName = URLEncoder.encode("Todo가 정상적으로 삭제되지 않았습니다!","UTF-8");
   		   }catch(UnsupportedEncodingException e1) {
   			   e1.printStackTrace();
   		   }
   	   }
   	   return "redirect:/list?todoId=" + todoId;
      }
      
      public String chkUpdate(HttpServletRequest request) {
    	  int todoId = Integer.parseInt(request.getParameter("todoId"));
    	  
    	  try {
      		   dao.updateChk(todoId);
      	   }catch(Exception e) {
      		   e.printStackTrace();
      		   ctx.log("Todo을 갱신하는 과정에서 문제 발생");
      		   String encodeName;
      		   try {
      			   encodeName = URLEncoder.encode("Todo가 정상적으로 갱신되지 않았습니다!","UTF-8");
      		   }catch(UnsupportedEncodingException e1) {
      			   e1.printStackTrace();
      		   }
      	   }
      	   return "redirect:/list?todoId=" + todoId;
      }
      
      public String updateTodo(HttpServletRequest request) {
    	  try {
    		  ToDo td = new ToDo();
    		  td.setTodoTitle(request.getParameter("todoTitle"));
    		  td.setTodoMemo(request.getParameter("todoMemo"));
    		  td.setIsFinished(request.getParameter("isFinished"));
    		  td.setTodoId(Integer.parseInt(request.getParameter("todoId")));
    
    		  dao.updateTodo(td);    		  
    	  } catch(Exception e) {
    		  e.printStackTrace();
    	      ctx.log("수정 과정에서 문제 발생");
    	      try {
      	            String encodeName = URLEncoder.encode("게시물이 정상적으로 수정되지 않았습니다!", "UTF-8");
    	            return "redirect:/list &error" + encodeName;
    	         } catch (UnsupportedEncodingException e1) {
    	          
    	        	 e1.printStackTrace();
    	         }
    	  }
    	  return "redirect:/list";
      }
  
}

