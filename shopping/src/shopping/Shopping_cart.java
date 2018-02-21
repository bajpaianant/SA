package shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import java.util.*;
/**
 * Servlet implementation class Shopping_cart
 */
@WebServlet("/Shopping_cart")
public class Shopping_cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
            ArrayList<Products> cart=new ArrayList<Products>();	

        	
        // TODO Auto-generated constructor stub
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success=false;
		String opt= request.getParameter("option");
		String key=request.getParameter("key");
		//TODO: verify the product key
		int qty=0;
		try{
			qty = Integer.parseInt(request.getParameter("qty"));
		}catch(NumberFormatException e){
			
		}catch(Exception e){
			
		}
		HttpSession session = request.getSession();
		String uname = (String)session.getAttribute("UNAME");
		if(uname==null)
			session.setAttribute("UNAME", "GUEST");
		Set<CartItem> cart = (TreeSet)session.getAttribute("CART");
		if(cart==null)
			cart = new TreeSet<CartItem>();
		if(opt.equals("1") && qty!=0)
			success = cart.add(new CartItem(key,qty));
		if(opt.equals("0"))
			success = cart.remove(new CartItem(key,qty));
		if(success)
			session.setAttribute("CART", cart);
		PrintWriter writer = response.getWriter();
		writer.print("{operation:"+success+"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
class CartItem implements Serializable, Comparable<CartItem>{
	private String pid;
	private int qty;
	public CartItem(String key, int qty) {
		pid=key;
		this.qty=qty;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	@Override
	public boolean equals(Object obj) {
		CartItem temp = (CartItem)obj;
		return temp.pid.equals(pid);
	}
	@Override
	public String toString() {
		return "PID : "+pid+" QTY : "+qty;
	}
	@Override
	public int compareTo(CartItem o) {
		System.out.println("here");
		return o.pid.compareTo(pid);
	}
	
}
