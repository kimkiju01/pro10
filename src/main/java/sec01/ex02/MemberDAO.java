package sec01.ex02;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext; //This class is the starting context for performing naming operations. 
import javax.naming.NamingException;
import javax.sql.DataSource; //실질적인 어떤 데이터인지를 연결하는 객체


									// 이름 찾기 적압 위해 사용하는 클래스

public class MemberDAO {
//	private static final String driver = "oracle.jdbc.driver.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//	private static final String user = "scott";
//	private static final String pwd = "12341234";
	
	private DataSource dataFactory;
	private Connection con;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	public MemberDAO() {
		//JNDI 방식의 연결로서 MemberDAO 객체를 초기화
		try {
			Context ctx= new  InitialContext(); //컨텍스트 작업을 위한 객체
			Context envContext= (Context)ctx.lookup("java:/comp/env");
			 dataFactory= (DataSource) envContext.lookup("jdbc/oracle");
			
		} catch (NamingException e) {
			System.out.println("톰캣의 context.xml에 정의 되어 있는 이름부분에서 정확하지 않은 에러");
//			e.printStackTrace();
		}
	}
	


	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member where id = 'hong'";
			System.out.println(query);
			pstm = con.prepareStatement(query);
			rs = pstm.executeQuery();
			
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
			pstm.close();
			con.close(); // 데이터베이스와의 연결을 관리하는 connection 인스턴스는 사용한 뒤 반납하지 않으면 계속해서 연결을 유지하므로 , 어느 시점에서는 
			 			// 데이터베이스 연결이 부족한 상황이 발생할 수 있다. 따라서 사용자가 많은 시스템일수록 커ㅔ셕 관리가 중요한데 , 이를 위해 커서
						//커넥션 풀의 사
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		return list;
		
		
		//커넥션 db이므로 미리 연결객체를 만들어 놨기 때문에 필요없다.
//		private void conDB() {
//			
//		}
		
	}
}
