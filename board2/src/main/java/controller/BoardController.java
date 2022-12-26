package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.BoardDAO;
import DTO.Board;

@WebServlet("/")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO dao;
	private ServletContext ctx;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	dao = new BoardDAO();
    	ctx = getServletContext();
    	//init은 서불릿 객체 생성시 딱 한번만 실행됨
    	
    }

    public BoardController() {
       super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}
	
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;
		
		switch(command) {
		case "/list":
			site = getList(request);
			break;
		case "/view":
			site = getView(request);
			break;
		case "/write":
			site = "write.jsp";
			break;
		case "/insert":
			site = insertBoard(request);
			break;
			
		}
		/*
		redirect :URL의 변화 O, 객체의 재사용 X (request, response객)
		DB에 변화가 생기는 요청에 사용(글쓰기, 회원가입) insert, update, delete
		
		forward : URL의 변화 X, 객체의 재사용 O 
		 * */
		if(site.startsWith("redirect:/")) {		//redirect
			String rview = site.substring("redirect:/".length());
			System.out.println(rview);
			response.sendRedirect(rview);
		}else {		//forward
			ctx.getRequestDispatcher("/" + site).forward(request, response);
		}
	}
	
	public String getList(HttpServletRequest request) {
		List<Board> list;
		try {
			list = dao.getList();
			request.setAttribute("boardList", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시판 목록 생성과정에서 문제 발생");
			request.setAttribute("error", "개시판 목록이 정상적으러 처리되지 않았습니다!");
		}
		
		return "index.jsp"; 
	}
	
	public String getView(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		try {
			dao.updateViews(board_no);
			Board b = dao.getView(board_no);
			request.setAttribute("board", b);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다");
		}
		
		return "view.jsp"; 
	}
	
	
	public String insertBoard(HttpServletRequest request) {
	      Board b = new Board();
	      // 이런 과정 생략 가능
//	      b.setUser_id(request.getParameter("user_id");

	      try {
	         BeanUtils.populate(b, request.getParameterMap());
	         dao.insertBoard(b);

	      } catch (Exception e) {
	         e.printStackTrace();
	         ctx.log("추가 과정에서 문제 발생");

	         // 사용자 한테 에러메시지 보여주기 위해 저장
	         request.setAttribute("error", "게시글을 정상적으로 등록되지 않았습니다!");
	         return getList(request);
	      }
	      return "redirect:/list";

	   }
	
}
