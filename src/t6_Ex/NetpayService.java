package t6_Ex;

import java.util.ArrayList;
import java.util.Scanner;

public class NetpayService {
	Scanner sc = new Scanner(System.in);
	NetpayDAO dao = new NetpayDAO();
	NetpayVO vo = null;
	
	int res, choice = 0;
	String ans = "N";

	// 직급별 본봉 조회
	public void salaryBonbongList() {
		ArrayList<NetpayVO> vos = dao.getSalaryBonbongList();
		System.out.println("====================================================================================================");
		System.out.println("\t\t\t\t**** 무한상사 직급별 본봉 조회 ****");
		System.out.println("====================================================================================================");
		System.out.println("\t\t\t\t직급\t\t\t\t본봉");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			System.out.print("\t\t\t\t"+vo.getJikkub());
			System.out.print("\t\t\t    "+String.format("%,d", vo.getBonbong())+"원\n");
		}
	}
	// 본봉 수정
	public void setSalaryBonbong() {
		boolean run = true;
		String jikkub = "";
		
		while(run) {
			System.out.print("본봉을 수정할 계급 입력 ==> ");
			jikkub = sc.next();
			System.out.print("수정할 금액을 입력하세요> ");
			int bonbong = sc.nextInt();
			
			if(jikkub.equals("부장")) {
				vo.setBonbong(bonbong);
				break;
			}
			else if(jikkub.equals("과장")) {
				vo.setBonbong(bonbong);
				break;
			}
			else if(jikkub.equals("대리")) {
				vo.setBonbong(bonbong);
				break;
			}
			else if(jikkub.equals("사원")) {
				vo.setBonbong(bonbong);
				break;
			}
			else {
				System.out.println("해당 직급이 없습니다.");
				run = false;
			}
		}
		res = dao.getSalaryUpdate(vo, jikkub);
		if(res != 0) System.out.println("수정 완료!");
		else System.out.println("수정 실패...");
	}
	
	// 실수령액 계산
	public void calculator(NetpayVO vo) {
		vo.setGongje((int)((vo.getBonbong()+vo.getSudang())*0.1));
		vo.setNetpay(vo.getBonbong()+vo.getSudang()-vo.getGongje());
	}
	
	// 전체 직원 목록
	public void getInsaList() {
		ArrayList<NetpayVO> vos = dao.getInsaList();
		
		System.out.println("====================================================================================================");
		System.out.println("\t\t\t\t**** 무한상사 전직원 목록 조회 ****");
		System.out.println("====================================================================================================");
		System.out.println("번호\t  사번\t\t부서\t 성명\t직급\t나이\t   입사일\t\t성별\t주소\t  본봉");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			System.out.print(" "+(i+1)+"\t");
			System.out.print(vo.getSabun()+"\t");
			System.out.print(vo.getBuseo()+"\t");
			System.out.print(vo.getName()+"\t");
			System.out.print(vo.getJikkub()+"\t");
			System.out.print(vo.getAge()+"\t");
			System.out.print(vo.getIpsail().toString().substring(0, 10)+"\t");
			System.out.print(vo.getGender()+"\t");
			System.out.print(vo.getAddress()+"\t");
			System.out.print(String.format("%,d", vo.getBonbong())+"\n");
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t**** 총 인원 수: "+vos.size()+"명 ****");
		
	}
	
	// 직원 추가
	public void setInsaInsert() {
		while(true) {
			System.out.println("\t\t\t\t**** 직원 추가 입력 ****");
			String name, sabun, buseo, jikkub, ipsail, gender, address;
			int age;
			System.out.print("성명: "); name = sc.next();
			System.out.print("사번: "); sabun = sc.next();
			System.out.print("부서: "); buseo = sc.next();
			System.out.print("직급: "); jikkub = sc.next();
			System.out.print("나이: "); age = sc.nextInt();
			System.out.print("성별: "); gender = sc.next();
			System.out.print("입사일: "); ipsail = sc.next();
			System.out.print("주소: "); address = sc.next();
			
			vo = new NetpayVO();
			vo.setName(name);
			vo.setSabun(sabun);
			vo.setBuseo(buseo);
			vo.setJikkub(jikkub);
			vo.setAge(age);
			vo.setGender(gender);
			vo.setIpsail(ipsail);
			vo.setAddress(address);
			
			int res = dao.setInsanInsert(vo);
			
			if(res != 0) System.out.println("등록 완료!");
			else System.out.println("등록 실패...");
			
			System.out.print("계속 하시겠습니까?(Y/N)>> ");
			ans = sc.next();
			if(!ans.toUpperCase().equals("Y")) break;
		}
	}
	
	// 직원 정보 수정
	public void setInsaEdit() {
		int cnt = insaSearch();
		boolean run = true;
		
		if(cnt == 0) {
			System.out.println("해당 직원이 없습니다.");
			return;
		}
		if(cnt > 1)sameName();
		
		while(run) {
			System.out.println("수정할 항목을 입력하세요.");
			System.out.print("1. 부서 | 2. 성명 | 3. 직급 | 4. 나이 | 5. 입사일 | 6. 성별 | 7. 주소 | 0. 종료 ==> ");
			int choice = sc.nextInt();
			System.out.print("수정할 내용을 입력하세요.> ");
			
			switch(choice) {
				case 1: vo.setBuseo(sc.next()); break;
				case 2: vo.setName(sc.next()); break;
				case 3: vo.setJikkub(sc.next()); break;
				case 4: vo.setAge(sc.nextInt()); break;
				case 5: vo.setIpsail(sc.next()); break;
				case 6: vo.setGender(sc.next()); break;
				case 7: vo.setAddress(sc.next()); break;
				default: run = false; break;
			}
		}
		res = dao.setInsaUpdate(vo);
		if(res != 0) System.out.println("\n수정 완료!");
		else System.out.println("수정 실패");
	
	}
	
	// 직원 개별 검색
	public int insaSearch() {
		int cnt = 0;
		System.out.print("실행할 직원의 성명을 입력하세요.> ");
		String name = sc.next();
		ArrayList<NetpayVO> vos = dao.getInsaSearch(name);
		
		System.out.println("====================================================================================================");
		System.out.println("\t\t\t\t**** 무한상사 "+name+" 조회 ****");
		System.out.println("====================================================================================================");
		System.out.println("번호\t  사번\t\t부서\t 성명\t직급\t나이\t   입사일\t\t성별\t주소\t  본봉");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			System.out.print(" "+(i+1)+"\t");
			System.out.print(vo.getSabun()+"\t");
			System.out.print(vo.getBuseo()+"\t");
			System.out.print(vo.getName()+"\t");
			System.out.print(vo.getJikkub()+"\t");
			System.out.print(vo.getAge()+"\t");
			System.out.print(vo.getIpsail().toString().substring(0, 10)+"\t");
			System.out.print(vo.getGender()+"\t");
			System.out.print(vo.getAddress()+"\t");
			System.out.print(String.format("%,d", vo.getBonbong())+"\n");
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t**** 총 "+name+" 인원 수: "+vos.size()+"명 ****");
		cnt = vos.size();
		
		return cnt;
	}
	
	// 직원 동명이인 사번으로 재검색
	public void sameName() {
		System.out.print("실행할 직원의 사번을 입력하세요> ");
		String sabun = sc.next();
		vo = dao.getInsaSearchSabun(sabun);
			
		System.out.println("사번\t\t부서\t 성명\t직급\t나이\t   입사일\t\t성별\t주소\t  본봉");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.print(vo.getSabun()+"\t");
		System.out.print(vo.getBuseo()+"\t");
		System.out.print(vo.getName()+"\t");
		System.out.print(vo.getJikkub()+"\t");
		System.out.print(vo.getAge()+"\t");
		System.out.print(vo.getIpsail().toString().substring(0, 10)+"\t");
		System.out.print(vo.getGender()+"\t");
		System.out.print(vo.getAddress()+"\t");
		System.out.print(String.format("%,d", vo.getBonbong())+"\n");
		
	}
	
	// 직원 삭제
	public void insaDelete() {
		int cnt = insaSearch();
		if(cnt == 0) {
			System.out.println("해당 직원이 없습니다.");
			return;
		}
		if(cnt > 1) sameName();
		
		if(vo != null) {
			System.out.print("삭제하시겠습니까?(Y/N) ==> ");
			String ans = sc.next().toUpperCase();
			if(ans.equals("Y")) {
				res = dao.insaDelete(vo);
				if(res != 0) System.out.println(vo.getName()+"의 자료가 삭제되었습니다.");
			}
			else System.out.println("삭제를 취소합니다.");
		}
	}
	
	// 실수령액 계산
	public void netpayCal() {
		int cnt = insaSearch();
		if(cnt == 0) {
			System.out.println("해당 직원이 없습니다.");
			return;
		}
		if(cnt>1) sameName();
		System.out.print("초과 근로 시간을 입력하세요> ");
		int hour = sc.nextInt();
		vo.setSudang(hour*30000);
		calculator(vo);
		System.out.println("====================================================================================================");
		System.out.println("\t\t\t\t**** 무한상사 "+vo.getName()+"의 실수령액 계산 ****");
		System.out.println("====================================================================================================");
		System.out.println("사번\t\t 성명\t직급\t   본봉\t\t 초과수당\t\t공제액\t\t실수령액");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.print(vo.getSabun()+"\t");
		System.out.print(vo.getName()+"\t");
		System.out.print(vo.getJikkub()+"\t");
		System.out.print(String.format("%,d", vo.getBonbong())+"원\t");
		System.out.print(String.format("%,d", vo.getSudang())+"원\t");
		System.out.print(String.format("%,d", vo.getGongje())+"원\t");
		System.out.print(String.format("%,d", vo.getNetpay())+"원\n");
	}
}
