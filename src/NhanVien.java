import java.sql.*;
import java.util.Scanner;

public class NhanVien {
private String maNV;
private String tenNV;
private String gioiTinh;
private String chucVu;
private String ngayVaoLam;
private String diaChi;
private String sdt;
private String tienluong;

public void getAllNV(Connection conn) { 
		try {
			Statement stmt = conn.createStatement();
			String sql ="select * from NhanVien";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.println("Danh sách nhân viên  ");
			System.out.println("");
			 System.out.println("|-------|----------------------|-----------|-----------|--------------|--------------------|------------|");
			 System.out.println("| Mã NV |       Tên NV         | Giới tính |  Chức vụ  | Ngày vào làm |       Địa chỉ      |     SĐT    |");
			 System.out.println("|-------|----------------------|-----------|-----------|--------------|--------------------|------------|");
			while(rs.next()) {
			 maNV =rs.getString("MANV");
			 tenNV =rs.getString("TENNV");
			 gioiTinh =rs.getString("GIOITINH");
			 chucVu =rs.getString("CHUCVU");
			 ngayVaoLam=rs.getString("NGAY_VAO_LAM");
			 diaChi =rs.getString("DIACHI");
			 sdt =rs.getString("SDT");
			  System.out.format("|%-7s|%-22s|%-11s|%-11s|%-14s|%-20s|%-12s|",maNV,tenNV,gioiTinh,chucVu,ngayVaoLam,diaChi,sdt);
			 System.out.println("");
			}
			 System.out.println("|-------|----------------------|-----------|-----------|--------------|--------------------|------------|");
			 System.out.println("");
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
 }
public void getLuongNV(Connection conn) { 
		try {
			Statement stmt = conn.createStatement();
			String sql ="select a.MANV, b.TENNV,a.THANHTIEN from LUONGNV a, NHANVIEN b where a.MANV = b.MANV";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.println("Bảng lương nhân viên  ");
			System.out.println("");
			 System.out.println("|-------|----------------------|------------|");
			 System.out.println("| Mã NV |       Tên NV         |   Lương    |");
			 System.out.println("|-------|----------------------|------------|");
			while(rs.next()) {
			 maNV =rs.getString("MANV");
			 tenNV = rs.getString("TENNV");
			 tienluong =rs.getString("THANHTIEN");
			 tienluong+=" VND";
			  System.out.format("|%-7s|%-22s|%-12s|",maNV,tenNV,tienluong);
			  System.out.println("");
			}
			 System.out.println("|-------|----------------------|------------|");
		}catch(Exception e){
			System.out.println("ERR:" +e);
			System.out.println("Thực thi không  thành công");
		}
}		

public void test(Connection conn) {
	try {
		Statement stmt = conn.createStatement();
		String sql ="select a.MANV, b.TENNV,a.THANHTIEN from LUONGNV a, NHANVIEN b where a.MANV = b.MANV";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
		 maNV =rs.getString("MANV");
		 tenNV = rs.getString("TENNV");
		 tienluong =rs.getString("THANHTIEN");
		 
		System.out.println(maNV +"\t"+tenNV+"\t"+tienluong); 
		}
	}catch(Exception e){
		System.out.println("ERR:" +e);
		System.out.println("Thực thi không  thành công");
	}
}

public void insertNV(Connection conn) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Nhập mã nhân viên: ");
	this.maNV = sc.nextLine();
	System.out.println("Nhập tên nhân viên: ");
	this.tenNV = sc.nextLine();
	System.out.println("Nhập giới tính nhân viên: ");
	this.gioiTinh = sc.nextLine();
	System.out.println("Nhập chức vụ nhân viên: ");
	this.chucVu = sc.nextLine();
	System.out.println("Nhập ngày vào làm nhân viên (yyyy/mm/dd) : ");
	this.ngayVaoLam = sc.nextLine();
	System.out.println("Nhập địa chỉ nhân viên: ");
	this.diaChi = sc.nextLine();
	System.out.println("Nhập số điện thoại nhân viên: ");
	this.sdt = sc.nextLine();
	System.out.println("Nhập tiền lương nhân viên: ");
	this.tienluong = sc.nextLine();
	try {
		
		PreparedStatement stmt = conn.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?)");
        stmt.setString(1,this.maNV);
        stmt.setString(2,this.tenNV);
        stmt.setString(3,this.gioiTinh);
        stmt.setString(4,this.chucVu);
        stmt.setString(5,this.ngayVaoLam);
        stmt.setString(6,this.diaChi);
        stmt.setString(7,this.sdt);
        stmt.executeUpdate();
        
        PreparedStatement stmt1 = conn.prepareStatement("insert into LUONGNV values(?,?)");
        stmt1.setString(1,this.maNV);
        stmt1.setString(2,this.tienluong);
        stmt1.executeUpdate();
        
		System.out.println("Thêm nhân viên thành công!"); 
	}catch(Exception e){
		System.out.println("ERR:" +e);
		System.out.println("Thực thi không  thành công");
	}
}

public void update(Connection conn) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Mời bạn chọn mã nhân viên cần sửa: ");
	this.maNV = sc.nextLine();
	System.out.println("1.Sửa tên nhân viên: ");
	System.out.println("2.Sửa giới tính nhân viên: ");
	System.out.println("3.Sửa chức vụ nhân viên: ");
	System.out.println("4.Sửa ngày vào làm nhân viên: ");
	System.out.println("5.Sửa địa chỉ nhân viên: ");
	System.out.println("6.Sửa số điện thoại nhân viên: ");
	int op[] = new int[10];
	int i=0;
	boolean choose=true;
	System.out.println("Mời chọn danh mục cần sửa (ấn phím 0 khi hoàn tất): ");
	while(choose) {
		Scanner sc1 = new Scanner(System.in);
		System.out.print("> ");
		int a=sc1.nextInt();
		if(a==0) choose= false;
		op[i]=a;
		i++;
		}
		String []arr = new String[i-1];
		String []values = new String[i-1];
	for(int j =0;j<op.length;j++) {
		switch(op[j]) {
		case 1: System.out.println("Nhập tên: "); this.tenNV = sc.nextLine();
				arr[j] = "TENNV";
				values[j] = this.tenNV;
			break;
		case 2: System.out.println("Nhập giới tính: "); this.gioiTinh = sc.nextLine(); 
				arr[j] = "GIOITINH";
				values[j] = this.gioiTinh;
		break;
		case 3: System.out.println("Nhập chức vụ: "); this.chucVu = sc.nextLine();
				arr[j] = "CHUCVU";
				values[j] = this.chucVu;
			break;
		case 4: System.out.println("Nhập ngày vào làm (yyyy/mm/dd): "); this.ngayVaoLam = sc.nextLine();
				arr[j] = "NGAY_VAO_LAM";
				values[j] = this.ngayVaoLam;
		break;
		case 5: System.out.println("Nhập địa chỉ: "); this.diaChi = sc.nextLine();
				arr[j] = "DIACHI";	
				values[j] = this.diaChi;
		break;
		case 6: System.out.println("Nhập số điện thoại: "); this.sdt = sc.nextLine();
				arr[j] = "SDT";	
				values[j] = this.sdt;
		break;
		}
		
	} String sql="update NhanVien set ";
	for(int j=0;j<arr.length;j++) {
		if(j==arr.length-1) {
			sql+= arr[j]+"='" +values[j]+"'";
		}
		else sql+= arr[j]+"='" +values[j] +"',";
	}
	sql+= " where MANV = '"+this.maNV+"'";
	//System.out.println(sql);
try {
		
		PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
		System.out.println("Sửa nhân viên thành công!"); 
	}catch(Exception e){
		System.out.println("ERR:" +e);
		System.out.println("Thực thi không  thành công");
	}
	
	
}

public void deleteNV(Connection conn) {
	this.getAllNV(conn);
	System.out.print("Nhập mã nhân viên cần xóa: ");
	Scanner sc1 = new Scanner(System.in);
	this.maNV = sc1.nextLine();
	System.out.println("Bạn có chắc chắn muốn xóa nhân viên có mã "+this.maNV+" ?");
	System.out.println("[1]. Có          [2]. Không");
	System.out.print("> ");
	int c = sc1.nextInt();
	if(c==1) {
try {   
	  
	  Statement stmt0 = conn.createStatement();
			String sql ="select * from NHANVIEN a where a.MANV ='"+this.maNV+"'";
			ResultSet rs = stmt0.executeQuery(sql);
			if(!rs.next()) {
				 System.out.println("Nhân viên có mã "+this.maNV+" không tồn tại! Mời thực hiện lại ");
				 deleteNV(conn);
			}				
	    PreparedStatement stmt1 = conn.prepareStatement("delete from LUONGNV where MANV = ?");
	    stmt1.setString(1,this.maNV);
	    stmt1.executeUpdate();
		PreparedStatement stmt = conn.prepareStatement("delete from NHANVIEN where MANV = ?");
		stmt.setString(1,this.maNV);
		stmt.executeUpdate();
		System.out.println("Xóa nhân viên thành công!"); 
	}catch(Exception e){
		System.out.println("ERR:" +e);
		System.out.println("Thực thi không  thành công");
	}
} else this.updateNV(conn);
}
	

public void updateNV(Connection conn) {
	QuanLyCaFe a = new QuanLyCaFe();
	System.out.println("------------------------");
	System.out.println("[1]. Thêm nhân viên");
	System.out.println("[2]. Sửa nhân viên");
	System.out.println("[3]. Xóa nhân viên");
	System.out.println("[4]. Quay lại");
	System.out.print("> ");	
	Scanner sc = new Scanner(System.in);
	int x = sc.nextInt();
	switch(x) {
	case 1 : this.insertNV(conn);
	  a.backOp();
		break; 
	case 2: {
		this.getAllNV(conn);
		this.update(conn);
	  a.backOp();
	  break;
	}	
	case 3: {this.deleteNV(conn);
	a.backOp();
		break;
	}
		default: a.showMenu();
	}
	
	
}


public void findNV(Connection conn) {
	System.out.print("Nhập mã nhân viên cần tìm: ");
	Scanner sc1 = new Scanner(System.in);
	this.maNV = sc1.nextLine();
	System.out.println("");
	 System.out.println("|----------------------|-----------|-----------|--------------|--------------------|------------|");
	 System.out.println("|       Tên NV         | Giới tính |  Chức vụ  | Ngày vào làm |       Địa chỉ      |     SĐT    |");
	 System.out.println("|----------------------|-----------|-----------|--------------|--------------------|------------|");
	try {
		Statement stmt = conn.createStatement();
		String sql ="select * from NHANVIEN a where a.MANV = '"+this.maNV+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
		 tenNV =rs.getString("TENNV");
		 gioiTinh =rs.getString("GIOITINH");
		 chucVu =rs.getString("CHUCVU");
		 ngayVaoLam=rs.getString("NGAY_VAO_LAM");
		 diaChi =rs.getString("DIACHI");
		 sdt =rs.getString("SDT");
		 System.out.format("|%-22s|%-11s|%-11s|%-14s|%-20s|%-12s|",tenNV,gioiTinh,chucVu,ngayVaoLam,diaChi,sdt);
		 System.out.println("");
		}
		System.out.println("|----------------------|-----------|-----------|--------------|--------------------|------------|");
	}catch(Exception e){
		System.out.println("ERR:" +e);
		System.out.println("Thực thi không  thành công");
	}
}




}
