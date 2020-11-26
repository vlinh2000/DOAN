import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HoaDon {
	private String ngayLap;
	private String tongTien;
	private String maHDBH;
	private String maNV;
	private String maBan;
	
	public void ThongKe(Connection conn) {
		System.out.println("Mời bạn nhập tháng cần thống kê: ");
		Scanner sc = new Scanner(System.in);
		int thang = sc.nextInt();
		try {
			Statement stmt = conn.createStatement();
			String sql ="select sum(a.TONGTIEN) as TONGCONG from HDBANHANG a where month(a.NGAYLAPHD)='"+thang+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
			 this.tongTien =rs.getString("TONGCONG");
			System.out.println("Tổng tiền của tháng "+thang+" là: "+this.tongTien+" VND");
			}
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
	}
	
	public void showHD(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			String sql ="select a.MAHDBH,b.TENNV,a.NGAYLAPHD,c.TENBAN,a.TONGTIEN from HDBANHANG a,nhanvien b,BAN c where a.MANV = b.MANV and a.MABAN=c.MABAN ";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
		   String MAHDBH =rs.getString("MAHDBH");
		   String tenNV =rs.getString("TENNV");	
		   this.ngayLap =rs.getString("NGAYLAPHD");
		   String tenBan =rs.getString("TENBAN");
			 this.tongTien =rs.getString("TONGTIEN");
			System.out.println(MAHDBH+"\t"+tenNV+"\t"+this.ngayLap+"\t"+tenBan+"\t"+this.tongTien+" VND");
			}
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
			
		}
	}
	
	
	public void createHD(Connection conn) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mã hóa đơn bán hàng: ");
		this.maHDBH = sc.nextLine();
		System.out.println("Nhập mã nhân viên: ");
		this.maNV = sc.nextLine();
		System.out.println("Nhập mã bàn: ");
		this.maBan = sc.nextLine();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   this.ngayLap=dtf.format(now);   
		try {  // Thêm vào bảng HDBANHANG
			
			PreparedStatement stmt = conn.prepareStatement("insert into HDBANHANG values(?,?,?,?,?)");
	        stmt.setString(1,this.maHDBH);
	        stmt.setString(2,this.maNV);
	        stmt.setString(3,this.maBan);
	        stmt.setString(4,this.ngayLap);
	        stmt.setString(5,"0");
	        stmt.executeUpdate();    
	        
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
		 System.out.println("Nhập số hàng hóa: "); 
			int a = sc.nextInt();
			int []arrSL= new int[a];
			String []arrHH= new String[a]; 
			for(int i = 0 ; i<a;i++) {
				Scanner sc1 = new Scanner(System.in);
				 System.out.println("Nhập mã hàng hóa thứ "+(i+1)+": ");
					arrHH[i] = sc1.nextLine();
				System.out.println("Nhập số lượng thứ "+(i+1)+": ");
					arrSL[i] = sc1.nextInt();
					 try {// Thêm vào bảng CHITIETHOADON
							
							PreparedStatement stmt1 = conn.prepareStatement("insert into CHITIETBANHANG values(?,?,?)");
					        stmt1.setString(1,this.maHDBH);
					        stmt1.setString(2,arrHH[i]);
					        stmt1.setInt(3,arrSL[i]);
					        stmt1.executeUpdate();
						}catch(Exception e){
							System.out.println("ERR:" +e);
							System.out.println("Thực thi không  thành công");
						}
			}
		
			 try {// Thay đổi lại tổng tiền trong HDBANHANG
					
					PreparedStatement stmt2 = conn.prepareStatement("update HDBANHANG set TONGTIEN= (select sum(a.SOLUONG*b.GIA) from chitietbanhang a, hanghoa b where a.MAHDBH=? and a.MAHH=b.MAHH) where MAHDBH = ?");
			        stmt2.setString(1,this.maHDBH);
			        stmt2.setString(2,this.maHDBH);
			        stmt2.executeUpdate();
			        System.out.println("Thêm hóa đơn thành công!!");
				}catch(Exception e){
					System.out.println("ERR:" +e);
					System.out.println("Thực thi không  thành công");
				}
			 
		
		
	}
	
	
	
	
	
	
}
