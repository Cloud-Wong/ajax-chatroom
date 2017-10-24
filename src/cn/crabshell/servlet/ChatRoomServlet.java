package cn.crabshell.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChatRoomServlet
 */
@WebServlet("/ChatRoomServlet")
public class ChatRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> OnlineUserList = new ArrayList<String>();
	static List<String> strSendContentList = new ArrayList<String>();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strAction = request.getParameter("action");
		String strName = request.getParameter("name");
		String strPass = request.getParameter("pass");
		String strContent = request.getParameter("content");
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if("Login".equals(strAction)){
			if(strPass.equals("111")) {
				session.setAttribute("LoginUser", strName);
				System.out.println("登陆成功!");
				OnlineUserList.add(strName);
				out.print("yes");
			}
			else {out.print("no");}
			out.close();
		}
		
		if("ChatList".equals(strAction)){
			String result1 = AllChatList();
			out.print(result1);
			out.close();
		}
		
		if("SendContent".equals(strAction)){
			boolean result2 = addSendContent(strContent,session);
			if(result2){
				out.print(1);
				System.out.println("发送!");
			}
			else{
				out.print(2);
				System.out.println("请登陆!");
			}
			out.close();
		}
		
		if("OnlineList".equals(strAction)){
			System.out.println(11);
			String result3 = getOnlineUserList(session);
			out.print(result3);
			out.close();
		}
	}
	
	public String AllChatList(){
		String result="";
		if(strSendContentList.size()==0){
			return "目前无聊天记录";
		}
		else{
			Iterator<String> it = strSendContentList.iterator();
			while(it.hasNext()){
				result +=it.next()+"</br>";
			}
		}
		return result;
	}
	
	public boolean addSendContent(String strContent,HttpSession session){
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		String user = (String)session.getAttribute("LoginUser");
		if(user==null) return false;
		String strSendContent = user+ "(" + dateString + "): " + strContent;
		if(strSendContentList.size()==0){
			strSendContentList = new ArrayList<String>();
		}
		strSendContentList.add(strSendContent);
		return true;
	}
	
	public String getOnlineUserList(HttpSession session){
		Iterator<String> it = OnlineUserList.iterator();
		String result = session.getAttribute("LoginUser") + "</br>";
		String userName;
		while(it.hasNext()){
			userName = it.next();
			if(userName.equals(session.getAttribute("LoginUser"))) continue;
			result += userName + "</br>";
			System.out.println(1);
		}
		return result;
	}

}
