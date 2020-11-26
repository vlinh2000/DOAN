import java.sql.Connection;
import java.util.Scanner;

public class QuanLyCaFe {
public static void main(String[] args) {
	System.out.println("Chào mừng bạn đến với phần mềm quản lý quán Cafe");
	showMenu();
}

public static void showMenu(){
	System.out.println("Danh sách chức năng");
	System.out.println("1. Hiển thị danh sách nhân viên ");
	System.out.println("2. Hiển thị bản lương nhân viên");
	System.out.println("3. Cập nhật nhân viên");
	System.out.println("4. Hiển thị danh sách nhà cung cấp");
	System.out.println("5. Hiển thị danh sách bàn");
	System.out.println("6. Tìm nhân viên");
	System.out.println("7. Thống kê doanh thu theo tháng");
	System.out.println("8. Hiển thị hàng hóa theo loại");
	System.out.println("9. Lập hóa đơn");
	System.out.println("10. Hiển thị danh sách hóa đơn");
	System.out.println("11. Thoát");
	System.out.println("Mời bạn lựa chọn chức năng: ");
	Scanner sc = new Scanner(System.in);
	int ans=sc.nextInt();
	checkAnswer(ans);
} 

public static void backOp() {
	System.out.println("");
	System.out.println("1. Quay lại menu                  2. Thoát");
	System.out.print("> ");
	Scanner sc = new Scanner(System.in);
	int back =sc.nextInt();
	if(back == 1) showMenu();
	else return;
	
}
public static void exit() {
	System.out.println("Cám ơn bạn đã sử dụng phần mềm, xin chào và hẹn gặp lại!!!");
}
public static void checkAnswer(int ans) {
	NhanVien list = new NhanVien();
	NhaCungCap ncc = new NhaCungCap();
	Ban ban= new Ban();
	HoaDon hd = new HoaDon();
	HangHoa hh = new HangHoa();
	Connection conInit =null;
	connection conn = new connection(conInit);
	conn.connect();
	// Connected!
	switch (ans) {
	case 1: list.getAllNV(conn.getConnection());
			backOp();// conn.getConnection return a Connection connected
	break;
	case 2: list.getLuongNV(conn.getConnection());
			backOp();
	break;
	case 3: list.updateNV(conn.getConnection());//test new class connection
			backOp();
	break;
	case 4: ncc.showNCC(conn.getConnection());//test new class connection
			backOp();
	break;
	case 5: ban.showBan(conn.getConnection());//test new class connection
	backOp();
    break;
	case 6: list.findNV(conn.getConnection());
	backOp();
    break;
	case 7: hd.ThongKe(conn.getConnection());
	backOp();
    break;
	case 8: hh.showHH(conn.getConnection());
	backOp();
    break;
	case 9: hd.createHD(conn.getConnection());
	backOp();
    break;
	case 10: hd.showHD(conn.getConnection());
	backOp();
    break;
	case 11: exit();
    break;
	}
	//close Connection!
	conn.closeConnect();
}

}