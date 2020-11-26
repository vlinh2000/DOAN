import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ban {
	public void showBan(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			String sql ="select * from BAN a, KHUVUC b where a.MAKV=b.MAKV";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
			 String maBan =rs.getString("MABAN");
			 String tenKv = rs.getString("TENKV");
			 String tenBan =rs.getString("TENBAN");
			 Boolean tt =rs.getBoolean("TRANGTHAI");
			System.out.println(maBan +"\t"+tenKv+"\t"+tenBan+"\t"+tt);
			}
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
	}
}
