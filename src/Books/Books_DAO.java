package Books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Books_DAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null; // ?있는 쿼리
	private Statement stmt = null; // ?없는 쿼리
	private ResultSet rs = null;

	ArrayList<Books> updaterentList;
	Books listBook;
	Books addBook;

	public Books_DAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // 드라이버 로드 1번만 하면 된다.
			System.out.println("드라이버 로드 성공 BDAO");
			System.out.println("-----------------------------------------");
		} catch (Exception e) {

		}
	}

	private boolean getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "system", "1111");
			System.out.println("연결성공1");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public void add(Books newB) {
		// TODO Auto-generated method stub
		if (getConnection()) {
			try {
				String y = "Y";
				String sql = ("insert into books values (no2.nextval, ?, ?,sysdate, ?) ");

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, newB.getName());
				pstmt.setString(2, newB.getAuthor());
				pstmt.setString(3, y);

				int k = pstmt.executeUpdate();
				
				
				this.addBook = newB;
				System.out.println(addBook.getNo()+ "등록된 책의번호");
				setbookNo();
				System.out.println(k + "건 입력완료");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	public Books setbookNo()	{
		if (getConnection()) {
			try {
				String sql = ("select * from books where name = ?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, addBook.getName());
				rs=pstmt.executeQuery();
				while(rs.next())	{
					addBook.setNo(rs.getInt("no"));
					addBook.setDate(String.format("%1$tY-%1$tm-%1$td", rs.getDate("reg_date")));
				}
				System.out.println(addBook.getNo()+ "등록된책의번호2");
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return addBook;
	}

	public ArrayList<Books> list() {
		ArrayList<Books> bList = new ArrayList<>();
		if (getConnection()) {
			try {
				String sql = "select * from books";
				stmt = conn.createStatement();

				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Books g = new Books();
					g.setNo(rs.getInt("no"));
					g.setName(rs.getString("name"));
					g.setAuthor(rs.getString("author"));
					g.setDate(String.format("%1$tY-%1$tm-%1$td", rs.getDate("reg_date")));

					bList.add(g);
				}
			} catch (Exception c) {

			}
		}
		return bList;

	}

	public void del(int inputNo) {
		if (getConnection()) {
			try {
				String sql = ("delete from books where no = ?");

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, inputNo);

				int k = pstmt.executeUpdate();

				System.out.println(k + "건 삭제완료");

			} catch (Exception e) {

			}

		}

	}

	public Books search(Books searchBook) {
		if (getConnection()) {
			try {
				String sql = ("select * from books where name = ?");
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchBook.getName());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					Books b = new Books();
					b.setName(rs.getString("name"));

					return b;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Books> searchList(String inputName) {
		ArrayList<Books> searchList = new ArrayList<>();
		System.out.println(inputName);
		if (getConnection()) {
			try {
				String sql = "select * from books where name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, inputName);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Books g = new Books();
					g.setNo(rs.getInt("no"));
					g.setName(rs.getString("name"));
					g.setAuthor(rs.getString("author"));
					g.setDate(String.format("%1$tY-%1$tm-%1$td", rs.getDate("reg_date")));

					searchList.add(g);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return searchList;
	}

	public ArrayList<Books> rentList() {
		ArrayList<Books> rentList = new ArrayList<>();
		if (getConnection()) {
			try {
				String sql = "select * from books";
				stmt = conn.createStatement();

				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Books g = new Books();
					g.setNo(rs.getInt("no"));
					g.setName(rs.getString("name"));
					g.setAuthor(rs.getString("author"));
					g.setDate(String.format("%1$tY-%1$tm-%1$td", rs.getDate("reg_date")));
					g.setRent(rs.getString("rent"));

					rentList.add(g);

				}
				this.updaterentList = rentList;
			} catch (Exception c) {

			}
		}
		return rentList;

	}

	public void updaterentList(int rbNo) {
		ArrayList<Books> updateRentList = this.rentList();
		if (getConnection()) {
			try {
				System.out.println(rbNo);
				String sql = "update books set rent = 'N' where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rbNo);

				int k = pstmt.executeUpdate();
				System.out.println(k + "건 업데이트");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}

	public void updaterentList2(int rbNo) {
		ArrayList<Books> updateRentList = this.rentList();
		if (getConnection()) {
			try {
				System.out.println(rbNo);
				String sql = "update books set rent = 'Y' where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rbNo);

				int k = pstmt.executeUpdate();
				System.out.println(k + "건 업데이트");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
		}

	}

	

}
