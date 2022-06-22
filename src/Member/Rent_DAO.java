package Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Rent_DAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null; // ?있는 쿼리
	private Statement stmt = null; // ?없는 쿼리
	private ResultSet rs = null;

	public Rent_DAO() {

		try {
			Class.forName("oracle.jdbc.OracleDriver"); // 드라이버 로드 1번만 하면 된다.
			System.out.println("드라이버 로드 성공 RDAO");
			System.out.println("-----------------------------------------");
		} catch (Exception e) {

		}
	}

	private boolean getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "system", "1111");
			System.out.println("연결성공2");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public void rent(int no, int inputNo) {
		if (getConnection()) {
			try {
				String sql = ("insert into rent values ( ?, ?, sysdate)");

				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, inputNo);
				pstmt.setInt(2, no);

				int k = pstmt.executeUpdate();

				System.out.println(k + "건 입력완료");

			} catch (Exception e) {
				// TODO: handle exception

				e.printStackTrace();
			}
		}

	}

	public ArrayList<Rent> rentlist() {
		ArrayList<Rent> rList = new ArrayList<>();
		if (getConnection()) {
			try {
				String sql = ("select books.no, books.name, member.id, rent.rent_date from books, member, rent where books.no= rent.b_id and member.no = rent.m_id");

				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					Rent rent = new Rent();
					rent.setbNo(rs.getInt("no"));
					rent.setbName(rs.getString("name"));
					rent.setmID(rs.getString("id"));
					rent.setDate(String.format("%1$tY-%1$tm-%1$td", rs.getDate("rent_date")));
					rList.add(rent);
					
					
				}
				

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return rList;
	}

	public void returnbook(int returnBNo, int i) {
		if (getConnection()) {
			try {
				String sql = ("delete from rent where b_id = ? and m_id=?");
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1,returnBNo);
				pstmt.setInt(2, i);
				
				int k = pstmt.executeUpdate();
				
				System.out.println(k+"건 반납 완료");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
}
