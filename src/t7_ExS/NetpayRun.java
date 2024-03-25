package t7_ExS;

import java.util.Scanner;

public class NetpayRun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SalaryService salaryService = new SalaryService();
		InsaService insaService = new InsaService();
		NetpayService netpayService = new NetpayService();
		
		int choice = 0;
		boolean run = true;
		
		while(run) {
			System.out.println("\n\t**** 인사 / 급여관리 ****");
			System.out.print("메뉴선택? 1:본봉테이블관리  2:인사관리  3:급여관리  0:종료 ==> ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					while(true) {
						System.out.println("\n\t**** 본봉테이블 관리 ****");
						System.out.print("1:직급/본봉입력  2:직급/본봉조회  3:본봉수정  4:직급삭제  0:종료 ==> ");
						choice = sc.nextInt();
						if(choice == 1) salaryService.setSalaryInput();
						else if(choice == 2) salaryService.getSalaryList();
						else if(choice == 3) salaryService.setSalaryUpdate();
						else if(choice == 4) salaryService.setSalaryDelete();
						else break;
					}
					break;
				case 2:
					System.out.println("\t**** 인 사 관 리 ****");
					System.out.print("1:사원등록  2:사원전체조회  3:사원개별조회  0:종료 ==> ");
					choice = sc.nextInt();
//					if(choice == 1) insaService.setSalaryInput();
//					else if(choice == 2) insaService.getInsaList();
//					else if(choice == 3) insaService.setInsaUpdate();
//					else if(choice == 4) insaService.setInsaDelete();
					break;
				case 3:
					System.out.println("\t**** 급 여 관 리 ****");
					System.out.print("1:사번을 입력하세요 ==> ");
					System.out.print("2:초과시간을 입력하세요 ==> ");
//					netpayService.getNetpaySearch();
					break;
				default:
					run = false;
			}
		}
		System.out.println("=================================================================");
		System.out.println("작업끝!");
		
		sc.close();
	}
}
