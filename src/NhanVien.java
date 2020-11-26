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
			while(rs.next()) {
			 maNV =rs.getString("MANV");
			 tenNV =rs.getString("TENNV");
			 gioiTinh =rs.getString("GIOITINH");
			 chucVu =rs.getString("CHUCVU");
			 ngayVaoLam=rs.getString("NGAY_VAO_LAM");
			 diaChi =rs.getString("DIACHI");
			 sdt =rs.getString("SDT");
			 System.out.println("---------------------------");
			System.out.println(maNV +"\t"+tenNV+"\t"+gioiTinh+"\t"+chucVu+"\t"+ngayVaoLam+ "\t"+diaChi+"\t"+sdt); 
			}
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
try {
		
		PreparedStatement stmt = conn.prepareStatement("delete from NHANVIEN where MANV = ?");
		stmt.setString(1,this.maNV);
		stmt.executeUpdate();
		System.out.println("Xóa nhân viên thành công!"); 
	}catch(Exception e){
		System.out.println("ERR:" +e);
		System.out.println("Thực thi không  thành công");
	}
}

public void updateNV(Connection conn) {
	System.out.println("------------------------");
	System.out.println("1. Thêm nhân viên");
	System.out.println("2. Sửa nhân viên");
	System.out.println("3. Xóa nhân viên");
	System.out.println("4. Quay lại");	
	Scanner sc = new Scanner(System.in);
	int x = sc.nextInt();
	switch(x) {
	case 1 : this.insertNV(conn);
		break; 
	case 2: {
		this.getAllNV(conn);
		this.update(conn);
	
	}
		break;
	case 3: this.deleteNV(conn);
		break;
	}
	
}












}