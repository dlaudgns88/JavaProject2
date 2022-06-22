package Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Member_DAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null; // ?있는 쿼리
	private Statement stmt = null; // ?없는 쿼리
	private ResultSet rs = null;

	public Member_DAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // 드라이버 로드 1번만 하면 된다.
			System.out.println("드라이버 로드 성공 MDAO");
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

	public void add(Member m) {
		// TODO Auto-generated method stub
		if (getConnection()) {
			try {

				String sql = ("insert into member values (no.nextval, ?, ?, ?, ? ,sysdate) ");

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, m.getID());
				pstmt.setString(2, m.getPW());
				pstmt.setString(3, m.getName());
				pstmt.setString(4, m.getTel());

				int k = pstmt.executeUpdate();

				System.out.println(k + "건 입력완료");

			} catch (Exception e) {
				// TODO: handle exception
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

	public Member loginProceess(Member loginuser) {
		if (getConnection()) {
			try {

				String sql = ("select * from member where id =? and pwd =? ");

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, loginuser.getID());
				pstmt.setString(2, loginuser.getPW());
				rs = pstmt.executeQuery();

				if (rs.next()) {

					loginuser.setID(rs.getString("id"));
					loginuser.setPw(rs.getString("pwd"));
					return loginuser;
				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	public Member selectone(Member loginuser) {
		// TODO Auto-generated method stub
		if (getConnection()) {
			try {
				String sql =("select * from member where id = ?");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, loginuser.getID());
				rs=pstmt.executeQuery();
				if(rs.next()) {
					loginuser.setNo(rs.getInt("no"));
					loginuser.setID(rs.getString("id"));
					
					return loginuser;
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
	public ArrayList<Member> memberList()	{
		ArrayList <Member>	mlist = new ArrayList<>();
		if (getConnection()) {
			try {
				String sql =("select * from member");
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next())	{
					Member m = new Member();
					m.setNo(rs.getInt("no"));
					m.setID(rs.getString("id"));
					m.setPw(rs.getString("pwd"));
					m.setName(rs.getString("name"));
					m.setTel(rs.getString("tel"));
					m.setDate(String.format("%1$tY-%1$tm-%1$td", rs.getDate("reg_date")));
					
					mlist.add(m);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}return mlist;
	}
}
