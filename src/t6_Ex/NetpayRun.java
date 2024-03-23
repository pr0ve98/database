package t6_Ex;

import java.util.Scanner;

public class NetpayRun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NetpayService service = new NetpayService();
		
		int choice = 0;
		boolean run = true;
		
		while(run) {
			System.out.println("====================================================================================================");
			System.out.println("\t\t\t\t**** 무한 상사 직원 관리 ****");
			System.out.println("====================================================================================================");
			System.out.print("1. 직급별 본봉 수정 및 조회 | 2. 직원 조회 | 3. 직원 추가 | 4. 직원 수정 | 5. 직원 삭제 | 6. 실수령액 계산 | 0. 종료 ==> ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					System.out.print("1. 본봉 조회 | 2. 본봉 수정 | 0. 종료 ==> ");
					choice = sc.nextInt();
					if(choice == 1) service.salaryBonbongList();
					else if(choice == 2) service.setSalaryBonbong();
					else break;
					break;
					
				case 2:
					System.out.print("1. 전체 직원 조회 | 2. 선택 직원 조회 | 0. 종료 ==> ");
					choice = sc.nextInt();
					if(choice == 1) service.getInsaList();
					else if(choice == 2) service.insaSearch();
					else break;
					break;
					
				case 3:
					service.setInsaInsert();
					break;
					
				case 4:
					service.setInsaEdit();
					break;
					
				case 5:
					service.insaDelete();
					break;
					
				case 6:
					service.netpayCal();
					break;
					
				default:
					System.out.println("====================================================================================================");
					System.out.println("\t\t\t\t   **** 종료합니다 ****");
					run = false;
					break;
			}
		}
		
		sc.close();
	}
}
