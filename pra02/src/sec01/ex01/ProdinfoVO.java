package sec01.ex01;

public class ProdinfoVO {

	private String Prod_id;
	private String Prod_name;
	private String Prod_price;
	private String Prod_desc;
	private String Vend_name;
	
	public ProdinfoVO() {
		System.out.println("CustinfoVO 생성자 호출");
	}

	public String getVend_name() {
		return Vend_name;
	}

	public void setVend_name(String vend_name) {
		Vend_name = vend_name;
	}

	public String getProd_id() {
		return Prod_id;
	}

	public void setProd_id(String prod_id) {
		Prod_id = prod_id;
	}

	public String getProd_name() {
		return Prod_name;
	}

	public void setProd_name(String prod_name) {
		Prod_name = prod_name;
	}

	public String getProd_price() {
		return Prod_price;
	}

	public void setProd_price(String prod_price) {
		Prod_price = prod_price;
	}

	public String getProd_desc() {
		return Prod_desc;
	}

	public void setProd_desc(String prod_desc) {
		Prod_desc = prod_desc;
	}



}
