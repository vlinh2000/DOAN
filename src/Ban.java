import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ban {
	public void showBan(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			String sql ="select * from BAN a, KHUVUC b where a.MAKV=b.MAKV";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.println("Danh sách bàn  ");
			System.out.println("");
			 System.out.println("|--------|--------|-----------|");
			 System.out.println("| Mã Bàn | Tên KV |  Tên Bàn  |");
			 System.out.println("|--------|--------|-----------|");
			
			while(rs.next()) {
			 String maBan =rs.getString("MABAN");
			 String tenKv = rs.getString("TENKV");
			 String tenBan =rs.getString("TENBAN");

			 System.out.format("|%-8s|%-8s|%-11s|",maBan,tenKv,tenBan);
			 System.out.println("");
			}
			System.out.println("|--------|--------|-----------|");
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
	}
}
