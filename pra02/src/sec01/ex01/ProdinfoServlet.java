package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec01.ex01.ProdinfoDAO;
import sec01.ex01.ProdinfoVO;


@WebServlet("/prodinfo")
public class ProdinfoServlet extends HttpServlet 
{  
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doHandle(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ProdinfoDAO dao = new ProdinfoDAO();
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		
		String cust_id= request.getParameter("id");
		System.out.println("고객 아이디    : " + cust_id);

		
		if(command != null && command.equals("addProdinfo")) 
		{
			String _id = request.getParameter("id");
			String _name = request.getParameter("name");
			String _price = request.getParameter("price");
			String _desc = request.getParameter("desc");
			String _vname = request.getParameter("vname");
			ProdinfoVO vo = new ProdinfoVO();
			vo.setProd_id(_id);
			vo.setProd_name(_name);
			vo.setProd_price(_price);
			vo.setProd_desc(_desc);
			vo.setVend_name(_vname);
			dao.addProdinfo(vo);
		}else if(command != null && command.equals("delProdinfo")) {
				  String id = request.getParameter("id");
				  dao.delMember(id);
		}
		
		List list = dao.listCustinfo(cust_id);
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>제품번호</td><td>제품명</td><td>제품가격</td><td>제품설명</td><td>공급업체명</td><td>삭제</td></tr>");
		
		for(int i = 0; i <list.size(); i++)
		{
			ProdinfoVO prodinfoVO = (ProdinfoVO) list.get(i);
			String id = prodinfoVO.getProd_id();
			String name = prodinfoVO.getProd_name();
			String price = prodinfoVO.getProd_price();
			String desc = prodinfoVO.getProd_desc();	
			String vname = prodinfoVO.getVend_name();
			out.print("<tr><td>" + id + "</td><td>" + name + "</td><td>" + price + "</td><td>" + desc + "</td><td>" + vname + "</td><td>" 
			+ "<a href='/pra02/Prodinfo?command=delProdinfo&prod_id=" + id + "'> 삭제 </a></td></tr>");
		}
		out.print("</table></body></html>");
		}
	
	}

	