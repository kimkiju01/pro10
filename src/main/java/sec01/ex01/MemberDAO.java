package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String user = "scott";
	private static final String pwd = "12341234";
	
	
	private Connection con;
	private PreparedStatement stmt; //sql문을 미리 만들어 두는 방식
	ResultSet rs;

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			connDB();
			String query = "select * from t_member where id = 'hong'";
			System.out.println(query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			// resultset 은 데이터베이스 내부적으로 수행된 sql문의 처리 결과를 jdbc에서 쉽게 관리 할수 있도록 해줌
			// resultset 은 next() 메서드를 이용해서 다음 로우(row)로 이동할 수 있다.
			//커서를 최소 데이터 위치로 이동 시키려면 , resultset 을 사용하기 전에 rs.next(); 메소드를 한 번 추출해줄 필요가 있다.
			//대부분의 경우 exercuteQuery() 메서드를 수행한 후 while(rs.next) 와 같이 더 이상 로우가 없을때까지 추출한다.
			
			
			
//			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();  //resultset 의 사용이 끝났으면 rs.close() 메소드를 아용해 resultset 을 닫아주도록 함
			stmt.close();
			con.close(); // 데이터베이스와의 연결을 관리하는 connection 인스턴스는 사용한 뒤 반납하지 않으면 계속해서 연결을 유지하므로 , 어느 시점에서는 
			 			// 데이터베이스 연결이 부족한 상황이 발생할 수 있다. 따라서 사용자가 많은 시스템일수록 커ㅔ셕 관리가 중요한데 , 이를 위해 커서
						//커넥션 풀의 사용여부와의 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
//			stmt = con.createStatement();
			System.out.println("Statement 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
