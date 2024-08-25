import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("학생 관리 프로그램입니다.");
            System.out.println("1: 학생 등록, 2: 학생 삭제, 3:조회 출력, 4:정보 수정, 5: 종료");
            System.out.print("선택하세요: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    InsertSmp a = new InsertSmp();
                    a.Insert();
                    break;
                case 2:
                    DeleteSmp b = new DeleteSmp();
                    b.Delete();
                    break;
                case 3:
                    SelectSmp c = new SelectSmp();
                    c.Select();
                    break;
                case 4:
                    ModifySmp d = new ModifySmp();
                    d.Modify();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        } while (choice != 5);

        scanner.close();
    }
}