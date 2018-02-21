package shopping;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpSession;

public class database_connection {
	static String databaseURL = "jdbc:mysql://localhost:3306/shop_smart?useSSL=false";
	static String user = "root";
	static String password = "password";
	private static Connection conn = null;

	// static String imgDataBase64;
	public static final Connection getConnection() {
		try {
			if (conn == null) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(databaseURL, user, password);
			}
			if (conn != null) {
				System.out.println("Connected to the database");
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Could not find database driver class");
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("Not connected to database!");
			ex.printStackTrace();
		}
		return conn;
	}

	static final void disconnect() {
		try {
			conn.close();
		} catch (SQLException ex) {
			System.out.println("Connection Not Closed!");
		}
	}

	public static HashMap<String, String> getCategories() {
		Connection con = getConnection();
		HashMap<String, String> categories = new HashMap<String, String>();
		Statement statement = null;
		try {
			if (isConnectionValid(con)) {
				statement = con.createStatement();
				ResultSet resultset = statement.executeQuery("select Category_Name,idCategory from category");
				while (resultset.next()) {
					categories.put(resultset.getString("idCategory"), resultset.getString("Category_Name"));
				}

			}
		} catch (SQLException ex) {
			System.out.println("failed");
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return categories;
	}

	private static boolean isConnectionValid(Connection con) throws SQLException {
		return con != null && !con.isClosed();
	}

	public static HashMap<String, Products> getItems(String cid) {
		Connection con = getConnection();
		Statement statement2 = null;
		HashMap<String, Products> products = new HashMap<String, Products>();
		try {
			if (isConnectionValid(con)) {
				statement2 = con.createStatement();
				ResultSet resultset2 = statement2.executeQuery("select * from items where Category='" + cid + "'");
				while (resultset2.next()) {
					Blob image = resultset2.getBlob("Image");
					Products p1 = new Products();
					p1.setPid(resultset2.getInt("Item_ID"));
					p1.setItem_name(resultset2.getString("Item_NAME"));
					p1.setItem_price(resultset2.getInt("Item_Price"));
					byte[] imgData = null;
					imgData = image.getBytes(1, (int) image.length());
					// imgDataBase64= new
					// String(Base64.getEncoder().encode(imgData));
					p1.setImageData(imgData);
					products.put(p1.getPid() + "", p1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement2 != null)
					statement2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return products;
	}

	public static HashMap<String, Products> getCartItems(HttpSession session) {
		Connection con = getConnection();
		Statement statement3 = null;
		HashMap<String, Products> products = new HashMap<String, Products>();
		try {
			if (isConnectionValid(con)) {
				Set<CartItem> cart = (TreeSet) session.getAttribute("CART");
				StringBuffer items = new StringBuffer();
				if(cart!=null){
				for (CartItem p : cart) {
					if (items.length() != 0)
						items.append("," + p.getPid());
					else
						items.append(p.getPid());
				}
				if (items.length() != 0) {
					statement3 = con.createStatement();
					ResultSet resultset3 = statement3
							.executeQuery("select * from items where Item_ID IN (" + items.toString() + ")");
					while (resultset3.next()) {
						Blob image = resultset3.getBlob("Image");
						Products p2 = new Products();
						p2.setPid(resultset3.getInt("Item_ID"));
						p2.setItem_name(resultset3.getString("Item_NAME"));
						p2.setItem_price(resultset3.getInt("Item_Price"));
						byte[] imgData = null;
						imgData = image.getBytes(1, (int) image.length());
						p2.setImageData(imgData);
						products.put(p2.getPid() + "", p2);
					}
				}}
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement3 != null)
					statement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return products;
	}

}
