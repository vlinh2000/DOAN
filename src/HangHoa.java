import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HangHoa {
	private String tenHH;
	private String tenLH;
	private String gia;
	

	public void showHH(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			String sql ="select a.TENHH,b.TENLH,a.GIA from hanghoa a, loaihang b where a.MALH = b.MALH";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.println("Danh sách hàng hóa theo loại  ");
			System.out.println("");
			 System.out.println("|--------------|------------------|-----------|");
			 System.out.println("|    Tên HH    |      Tên LH      |    Giá    |");
			 System.out.println("|--------------|------------------|-----------|");
			while(rs.next()) {
			 this.tenHH =rs.getString("TENHH");
			 this.tenLH =rs.getString("TENLH");
			 this.gia =rs.getString("GIA");
			 this.gia+=" VND";
			 System.out.format("|%-14s|%-18s|%-11s|",tenHH,tenLH,gia);
			 System.out.println("");
			}
			System.out.println("|--------------|------------------|-----------|");
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
	}
	
}
