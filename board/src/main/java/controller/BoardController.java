package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String context = request.getContextPath(); // 톰캣의 Context path를 가져온다(server.xml에서 확인)
		String command = request.getServletPath();
		String site = null;

		System.out.println("getContextPath:" + request.getContextPath());
		System.out.println("getServletPath:" + request.getServletPath());
		System.out.println("command:" + command);

		switch (command) {
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
		case "/edit":
			site = getViewForEdit(request);
			break;
		case "/update":
			site = updateBoard(request);
			break;
		case "/delete":
			site = deleteBoard(request);
			break;
		}
		
		// POST 요청 처리후에는 리디렉션 방법으로 이동 할 수 있어야 함.
		if(site.startsWith("redirect:/")) {
			// redirect/ 문자열 이후 경로만 가지고 옴
			String rview = site.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			getServletContext().getRequestDispatcher("/" + site).forward(request, response);
		}

	}

	public String getList(HttpServletRequest request) {
		List<Board> list;
		try {
			list = dao.getList();
			request.setAttribute("boardList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시판 목록 생성 과정에서 문제 발생!!");
			request.setAttribute("error", "게시판 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "index.jsp";
	}
	
	public String getView(HttpServletRequest request) {
        int board_no = Integer.parseInt(request.getParameter("board_no"));
        try {
        	dao.updateViews(board_no); //조회수 증가
			Board b = dao.getView(board_no);			
			request.setAttribute("board", b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생!!");
			request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다!!");
		}

		return "view.jsp";
	}
	
	public String getViewForEdit(HttpServletRequest request) {
        int board_no = Integer.parseInt(request.getParameter("board_no"));
        try {
			Board b = dao.getViewForEdit(board_no);			
			request.setAttribute("board", b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생!!");
			request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다!!");
		}

		return "edit.jsp";
	}
	
	
	
    public String insertBoard(HttpServletRequest request) {
		Board b = new Board();
		try {						
			// 이미지 파일 저장
//	        Part part = request.getPart("file");
//	        String fileName = getFilename(part);
//	        if(fileName != null && !fileName.isEmpty()){
//	            part.write(fileName);
//	        }	        
	        // 입력값을 News 객체로 매핑
			BeanUtils.populate(b, request.getParameterMap());
			
	        // 이미지 파일 이름을 News 객체에도 저장
//	        n.setImg("/img/"+fileName);

			dao.insertBoard(b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("추가 과정에서 문제 발생!!");
			request.setAttribute("error", "게시물이 정상적으로 등록되지 않았습니다!!");
			return getList(request);
		}
		
		return "redirect:/list";
		
	}
    
	public String updateBoard(HttpServletRequest request) {
		Board b = new Board();
		try {						
			// 이미지 파일 저장
//	        Part part = request.getPart("file");
//	        String fileName = getFilename(part);
//	        if(fileName != null && !fileName.isEmpty()){
//	            part.write(fileName);
//	        }	        
	        // 입력값을 News 객체로 매핑
			BeanUtils.populate(b, request.getParameterMap());
			
	        // 이미지 파일 이름을 News 객체에도 저장
//	        n.setImg("/img/"+fileName);

			dao.updateBoard(b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("수정 과정에서 문제 발생!!");
			request.setAttribute("error", "게시물이 정상적으로 수정되지 않았습니다!!");
			return getList(request);
		}
		
		return "redirect:/view?board_no=" + b.getBoard_no();
	}

	public String deleteBoard(HttpServletRequest request) {
    	int board_no = Integer.parseInt(request.getParameter("board_no"));
		try {
			dao.deleteBoard(board_no);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("삭제 과정에서 문제 발생!!");
			request.setAttribute("error", "정상적으로 삭제되지 않았습니다!!");
			return getList(request);
		}
		return "redirect:/list";
	}

}
