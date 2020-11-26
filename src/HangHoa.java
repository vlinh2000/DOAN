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
			while(rs.next()) {
			 this.tenHH =rs.getString("TENHH");
			 this.tenLH =rs.getString("TENLH");
			 this.gia =rs.getString("GIA");
			System.out.println(tenHH+"\t"+tenLH+"\t"+gia+" VND");
			}
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
	}
	
}
