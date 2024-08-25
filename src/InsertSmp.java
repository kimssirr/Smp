import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement; // Statement 대신 사용
import java.util.Scanner;

public class InsertSmp {
    public static void main(String[] args) {
        Insert();
    }

    public static void Insert() {
        System.out.println("등록 기능입니다. ");
        ConnectionTest connTest = new ConnectionTest();
        Connection conn = null;
        Scanner sc = new Scanner(System.in);

        try {
            conn = connTest.getConnection(); // Connection 객체 가져오기
            if (conn == null) {
                System.out.println("데이터베이스 연결에 실패했습니다.");
                return; // 연결 실패 시 메서드 종료
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("이름 입력:");
        String dname = sc.next();
        System.out.println("이메일 입력:");
        String dmail = sc.next();
        System.out.println("핸드폰번호 입력:");
        String dnum = sc.next();

        String sql = "INSERT INTO Smp (Name, Email, Phone) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;

        try {
            // 3. Query 준비
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dname);
            pstmt.setString(2, dmail);
            pstmt.setString(3, dnum);

            // 4. Query 실행 및 리턴 (쿼리 문자열을 전달할 필요 없음)
            int res = pstmt.executeUpdate();
            if (res > 0) {
                System.out.println("입력 성공");
            } else {
                System.out.println("입력 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. DB종료
            try {
                if (pstmt != null) pstmt.close(); // pstmt가 null인지 확인 후 닫기
                if (conn != null) conn.close(); // conn이 null인지 확인 후 닫기
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
