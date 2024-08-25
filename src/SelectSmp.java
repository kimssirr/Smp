import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement; // Statement 대신 사용
import java.sql.ResultSet;
import java.util.Scanner;

public class SelectSmp {
    public static void main(String[] args) {
        Select();
    }
    public static void Select() {

            System.out.println("조회 기능입니다. 번호를 입력해주세요.");
            ConnectionTest connTest = new ConnectionTest();
            Connection conn = null;
            System.out.println("0. 전체 출력 1.이름 검색 2.이메일 검색 3.번호 검색");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            String sql = null;
            PreparedStatement pstmt = null;
            try {
                conn = connTest.getConnection(); // Connection 객체 가져오기
                if (conn == null) {
                    System.out.println("데이터베이스 연결에 실패했습니다.");
                    return; // 연결 실패 시 메서드 종료
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String dname = null;


        // 3. Query 준비
        try {
            if (num == 1) {
                System.out.println("이름 입력:");
                dname = sc.next();
                sql = "SELECT * FROM Smp WHERE Name=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dname);
            } else if (num == 2) {
                System.out.println("이메일 입력:");
                dname = sc.next();
                sql = "SELECT * FROM Smp WHERE Email=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dname);
            } else if (num == 3) {
                System.out.println("폰번호 입력:");
                dname = sc.next();
                sql = "SELECT * FROM Smp WHERE Phone=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dname);
            } else if (num == 0) {

                sql = "SELECT * FROM Smp";
                pstmt = conn.prepareStatement(sql);
            }



                // select문은 결과집합 반환이라 다르게 해야함.
                ResultSet res = pstmt.executeQuery();
                while (res.next()) {
                    // 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
                    // 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
                    String index = res.getString(1);
                    String name = res.getString(2);
                    String email = res.getString(3);
                    String number = res.getString(4);

                    System.out.println("인덱스 번호: " + index + ", 이름: " + name + ", 이메일:" + email + ", 번호: " + number);
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
