package sec02.ex01;

import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member4")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws UnsupportedEncodingException{
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		doHandle(request,response);		
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		MemberDAO dao = new MemberDAO();
		
	}
	
	
	
}
