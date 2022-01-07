package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/UserFriendsServlet")
public class UserFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String fromID = (String) session.getAttribute("userID");
		String toID = request.getParameter("toID");
		UserDAO userDAO = new UserDAO();
		int check = userDAO.friendsCheck(fromID, toID);
		if(check == 0) {
			request.getSession().setAttribute("messageType", "���� �޽���");
			request.getSession().setAttribute("messageContent", "�̹� ģ���߰��� �Ǿ��ִ� ȸ���Դϴ�.");
			response.sendRedirect("index2.jsp");
			return;
		} else {
			int result = userDAO.friendsAdd(fromID, toID);
			if(result == -1) {
				request.getSession().setAttribute("messageType", "���� �޽���");
				request.getSession().setAttribute("messageContent", "�����ͺ��̽� ������ �߻��߽��ϴ�.");
				response.sendRedirect("index2.jsp");
				return;
				} else {
					request.getSession().setAttribute("messageType", "���� �޽���");
					request.getSession().setAttribute("messageContent", "ģ���߰��� �Ϸ��߽��ϴ�.");
					response.sendRedirect("index2.jsp");
					}
			}
		}

}
