import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class NhaCungCap {
	public void showNCC(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			String sql ="select * from NHACUNGCAP";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.println("Danh sách nhà cung cấp  ");
			System.out.println("");
			 System.out.println("|--------|----------------------------------|-----------------------------------|------------|");
			 System.out.println("| Mã NCC |             Tên NCC              |               Địa chỉ             |     SĐT    |");
			 System.out.println("|--------|----------------------------------|-----------------------------------|------------|");
			while(rs.next()) {
			 String maNCC =rs.getString("MANCC");
			 String tenNCC = rs.getString("TENNCC");
			 String diachi =rs.getString("DIACHI");
			 String sdt =rs.getString("SDT");
			 System.out.format("|%-8s|%-34s|%-35s|%-12s|",maNCC,tenNCC,diachi,sdt);
			 System.out.println("");
			}
			System.out.println("|--------|----------------------------------|-----------------------------------|------------|");
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
	}
}
