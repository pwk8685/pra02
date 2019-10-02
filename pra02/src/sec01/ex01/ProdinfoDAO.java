package sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sec01.ex01.ProdinfoVO;

public class ProdinfoDAO {
//	private static final String driver= "oracle.jdbc.driver.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	private static final String user ="scott";
//	private static final String pwd = "tiger";
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public ProdinfoDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java://comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List listCustinfo(String prod_id) {
		List list = new ArrayList();
		try
			{
//			connDB();
			con = dataFactory.getConnection();
			String query =  "SELECT prod_id, prod_name, prod_price,prod_desc,vend_name"
				   		+	" FROM products p ,vendors v"
				   		+	" WHERE v.vend_id = p.vend_id";
			System.out.println("prepareStatememt" + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String id = rs.getString("prod_id");
				String name = rs.getString("prod_name");
				String price = rs.getString("prod_price");
				String desc = rs.getString("prod_desc");
				String vname = rs.getString("vend_name");
				ProdinfoVO vo= new ProdinfoVO();
				vo.setProd_id(id);
				vo.setProd_name(name);
				vo.setProd_price(price);
				vo.setProd_desc(desc);
				vo.setVend_name(vname);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
		
	
	public void addProdinfo(ProdinfoVO prodinfoVO)
	{
		try
		{
			Connection con =dataFactory.getConnection();
			
			String id = prodinfoVO.getProd_id();
			String name = prodinfoVO.getProd_name();
			String price = prodinfoVO.getProd_price();
			String desc = prodinfoVO.getProd_desc();
			String vname = prodinfoVO.getVend_name();
			
			
			String query = "insert into prodinfo";
			query +=" (id,name,price,desc,vname)";
			query +=" values(?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, price);
			pstmt.setString(4, desc);
			pstmt.setString(4, vname);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
public void delMember(String id) 
{
	try
	{
		Connection con = dataFactory.getConnection();
		
		String query = "delete from cust_id" +  " where id=?";
		System.out.println("prepareStatememt:" + query);
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
		pstmt.close();
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	
}
}


