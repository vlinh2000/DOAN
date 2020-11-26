import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class NhaCungCap {
	public void showNCC(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			String sql ="select * from NHACUNGCAP";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
			 String maNCC =rs.getString("MANCC");
			 String tenNCC = rs.getString("TENNCC");
			 String diachi =rs.getString("DIACHI");
			 String sdt =rs.getString("SDT");
			System.out.println(maNCC +"\t"+tenNCC+"\t"+diachi+"\t"+sdt); 
			}
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
	}
}
