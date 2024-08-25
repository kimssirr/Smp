import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ModifySmp {
    public static void main(String[] args) {
        Modify();
    }
    public static void Modify() {
        System.out.println("수정 기능입니다. ");
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





        PreparedStatement pstmt = null;
        String sql = null;

        try {
            // 3. Query 준비
            System.out.println("수정할 대상 입력:");
            String dname = sc.next();
            System.out.println("1: 이메일 수정, 2: 번호 수정");
            int num = sc.nextInt();
            if(num == 1) {
                System.out.println("이메일 입력:");
                String dmail = sc.next();
                sql = "UPDATE Smp SET Email=? WHERE Name = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dmail);
                pstmt.setString(2, dname);
            } else if (num == 2) {
                System.out.println("핸드폰번호 입력:");
                String dnum = sc.next();
                sql = "UPDATE Smp SET Phone=? WHERE Name = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dnum);
                pstmt.setString(2, dname);
            }



            // 4. Query 실행 및 리턴 (쿼리 문자열을 전달할 필요 없음)
            int res = pstmt.executeUpdate();
            if (res > 0) {
                System.out.println("수정 성공");
            } else {
                System.out.println("수정 실패");
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
