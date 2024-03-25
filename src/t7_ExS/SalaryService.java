package t7_ExS;

import java.util.ArrayList;
import java.util.Scanner;

public class SalaryService {
	Scanner sc = new Scanner(System.in);
	NetpayDAO dao = new NetpayDAO();
	InsaVO vo = null;
	
	String ans = "N";
	int choice = 0, res = 0;

	// 본봉입력
	public void setSalaryInput() {
		System.out.println("\n** 본봉테이블 입력처리 **");
		String jikkub = "";
		
		while(true) {
			System.out.print("직급 : "); jikkub = sc.next();
			vo = dao.getJikkubSearch(jikkub);
			if(vo == null) break;
			else System.out.println("같은 직급이 존재합니다. 다시 입력하세요");
		}
		vo = new InsaVO();
		vo.setJikkub(jikkub);
		System.out.print("본봉 : "); vo.setBonbong(sc.nextInt());
		
		int res = dao.setSalaryInput(vo);
		
		if(res != 0) System.out.println("본봉 테이블에 등록되었습니다.");
		else System.out.println("본봉테이블 등록 실패~~");
	}

	// 직급/본봉 전체 조회
	public void getSalaryList() {
		ArrayList<InsaVO> vos = dao.getSalaryList();
		
		System.out.println("\n\t*** 본봉 테이블 ***");
		System.out.println("====================================");
		System.out.println("번호\t직급\t본봉");
		System.out.println("------------------------------------");
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			System.out.print(" " + (i+1) + "\t");
			System.out.print(vo.getJikkub() + "\t");
			System.out.print(vo.getBonbong() + "\n");
		}
		System.out.println("------------------------------------");
		System.out.println("\t직급 총 건수 : " + vos.size() + "명");
		System.out.println("====================================");
	}

	// 본봉 수정하기
	public void setSalaryUpdate() {
		salaryBasicSearch();
		
		System.out.print("수정할 본봉 금액을 입력하세요? ");
		vo.setBonbong(sc.nextInt());
		
		res = dao.setSalaryUpdate(vo);
		if(res != 0) System.out.println("본봉테이블이 수정 되었습니다.");
		else System.out.println("수정된 내역이 없습니다.");
	}

	// 기본 직급 조회
	private void salaryBasicSearch() {
		while(true) {
			System.out.print("\n조회할 직급을 입력하세요 ==> ");
			String jikkub = sc.next();
			
			vo = dao.getJikkubSearch(jikkub);
			
			if(vo != null) {
				System.out.println("\n직급 : " + vo.getJikkub());
				System.out.println("본봉 : " + vo.getBonbong());
				break;
			}
			else System.out.println("검색하신 "+jikkub+" 은 없습니다.");
		}
	}

	// 직급 삭제
	public void setSalaryDelete() {
		salaryBasicSearch();
		if(vo != null) {
			System.out.print("삭제 하시겠습니까?(y/n) ==> ");
			ans = sc.next();
			if(ans.toUpperCase().equals("Y")) {
				res = dao.setSalaryDelete(vo.getJikkub());
				if(res != 0) System.out.println(vo.getJikkub() + " 자료가 삭제 되었습니다.");
				else System.out.println(vo.getJikkub() + " 자료가 없습니다.");
			}
			else System.out.println("삭제 취소....");
		}
	}
}
