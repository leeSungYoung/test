package org.mf.project.member.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mf.project.member.user.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	
	//로그인처리
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String login2(HttpServletRequest req, Model model, Map map, RedirectAttributes red) {
		
		List<HashMap> members = memberDao.getAllMember(map);

		String resultURI = null;

		if (0 != members.size()) {

			String dbPassward = (String) ((Map) members.get(0)).get("memPw");

			if (dbPassward.equals((String) map.get("memPw"))) { //로그인시 아이디, 패스워드 일치 확인
				Map db = (Map)members.get(0);
				HttpSession session = req.getSession();
				session.setAttribute("memKey", db.get("memKey"));
				session.setAttribute("memName", db.get("memName"));
				session.setAttribute("memLevel", db.get("memLevel"));

				resultURI = "redirect:/"; //일치시 메인 페이지로 이동

			} else {

				red.addFlashAttribute("MSG", "패스워드를 확인하세요");
				//req.setAttribute("MSG", "패스워드를 확인하세요"); //패스워드 불일치시 경고문
				resultURI = "redirect:/member/login1";
			}

		} else {
			red.addFlashAttribute("MSG", "아이디를 확인하세요");
			//req.setAttribute("MSG", "아이디를 확인하세요"); //아이디 불일치시 경고문
			resultURI = "redirect:/member/login1";
		}
		
		return resultURI;
	}
	
	//비밀번호 찾기 처리
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String findPW2(HttpServletRequest req, Model model, Map map, RedirectAttributes red) {
		// TODO Auto-generated method stub
	
		String birthDay = (String) map.get("memBirth_Year") + '/' + map.get("memBirth_Month") + '/'
				+ map.get("memBirth_Day"); // 회원의 생년월일을  yyyy/mm/dd 형식으로 db에 저장

		map.put("Y", birthDay);
		
		List<HashMap> members = memberDao.getAllMember(map);

		String resultURI = null;

		if (0 != members.size()) {

			String dbPassward = (String) ((Map) members.get(0)).get("memName");

			if (dbPassward.equals((String) map.get("memName"))) { 
				Map db = (Map)members.get(0);
				HttpSession session = req.getSession();
		
				model.addAttribute("memKey", db.get("memKey"));
				resultURI = "redirect:/member/updatePW3"; //일치시 비밀번호 수정 페이지로 이동

			} else {

				red.addFlashAttribute("MSG", "일치하는 회원정보가 없습니다.");
				
				resultURI = "redirect:/member/findPW1";
			}
		} else {
			red.addFlashAttribute("MSG", "일치하는 회원정보가 없습니다.");
			
			resultURI = "redirect:/member/findPW1";
		}
		
		
	
		return resultURI;
	}
		
//		List list = memberDao.findPW2(map);
//		boolean check = false;
//		if(list.size() >0){
//			check = true;
//			Map db = (Map)list.get(0);
//			model.addAttribute("memKey",db.get("memKey")); //db검색 결과가 존재할시 회원 정보를 불러옴
//		}
//		
//		return check;
//	}
	

	//회원가입 처리
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int joinStep2(Map map) {

		String birthDay = (String) map.get("memBirth_Year") + '/' + map.get("memBirth_Month") + '/'
				+ map.get("memBirth_Day"); //생년,월,일 정보  memBirth 하나로 묶음

		map.put("Y", birthDay);

		return memberDao.joinStep2(map);
	}

	
	//회원탈퇴 처리
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int delete2(HttpServletRequest request) {
		
			int m = (int) request.getSession().getAttribute("memKey"); //회원정보 불러오기
			Map map = new HashMap();
			map.put("memKey", m);
			
			return memberDao.delete2(map);
	}



	
	
	//비밀번호 변경폼   비밀번호 변경을위한 비밀번호 체크시 일치 불일치 확인 경고문
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean updatePW1(Map map, HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		int memKey = (int)req.getSession().getAttribute("memKey");
		map.put("memKey", memKey);
		
		boolean check =checkPw2(map, req); 
		
		if(check){  //비밀번호 일치 여부 확인
			List list = memberDao.getAllMember(map);
			Map member = (Map)list.get(0);
			
			model.addAttribute("member",member);
		}
		
		return check;
	}
	
	//비밀번호 변경 처리
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updatePW2(HttpServletRequest request,Map map) {
		// TODO Auto-generated method stub
		int m =(int)request.getSession().getAttribute("memKey"); //회원정보 불러오기
		map.put("memKey",	m);
		
		return memberDao.updatePW2(map);
	}

	//회원정보수정창 회원 정보 보여주기
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean update1(Map map, HttpServletRequest request,Model model) {
		
		int memKey = (int)request.getSession().getAttribute("memKey"); //회원정보 불러오기
		map.put("memKey", memKey);
		
		boolean check =checkPw2(map, request); 
		
		if(check){
			
			List list = memberDao.getAllMember(map); //비밀번호 일치 여부 확인
			Map member = (Map)list.get(0);  
			String date = (String)member.get("memBirth");  // 회원의 생년월일을  yyyy/mm/dd 형식으로 db에 저장
			String[] d = date.split("/");
			member.put("memBirth_Year", d[0]);
			member.put("memBirth_Month", d[1]);
			member.put("memBirth_Day", d[2]);
			
			model.addAttribute("member",member);
		}
		
		return check;

	}
	
	//회원정보 수정처리
		@Override
		@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
		public int update2(HttpServletRequest request,Map map) {
			// TODO Auto-generated method stub
			String birthDay = (String) map.get("memBirth_Year") + '/' + map.get("memBirth_Month") + '/'
					+ map.get("memBirth_Day"); //생년,월,일 정보  memBirth 하나로 묶음

			map.put("Y", birthDay);
			HttpSession session = request.getSession();
			int m = (int)session.getAttribute("memKey");  //회원정보 불러오기
			
			map.put("memKey", m);
			
			int result = memberDao.update2(map);
			session.setAttribute("memName", map.get("memName"));
			//정보 수정후 session에 이름 갱신
			return result;
		}
	
	
	//내가평가한 영화 리스트
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public List star(HttpServletRequest request,Map map) {
		
		int m =(int)request.getSession().getAttribute("memKey");  //회원정보 불러오기
		map.put("memKey",m);
		
		
		return memberDao.getMemKey_star(map);
	}
	
	//내가 찜한 영화 리스트
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public List zzimMovie(HttpServletRequest request,Map map) {

		int m =(int)request.getSession().getAttribute("memKey");  //회원정보 불러오기
		map.put("memKey",m);
		
		return memberDao.zzimMovie(map);
	}
	
	//아이디 중복체크
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int checkidDup(Map map) {
		// TODO Auto-generated method stub
		Map map2 = memberDao.checkIdDup(map);
		int re = 0;   //중복되는 아이디 없음
		if (map2 != null) {
			re = 1; //중복되는 아이디 있음
		}
		return re;
	}
	

	//회원정보 수정시 비밀번호 확인 처리
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	private boolean checkPw2(Map map, HttpServletRequest request) {

		List list = memberDao.getAllMember(map);
		Map db = (Map)list.get(0);
		String dbPw = (String)db.get("memPw");
		
		
		boolean check = map.get("password").equals(dbPw);  //비밀번호 입력값 일치 확인
		
		
		return check;

	}
	//비밀번호 찾기 후 비밀번호 변경처리
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public int updatePW3(Map map) {
		// TODO Auto-generated method stub
		return memberDao.updatePW2(map);
	}

	//로그아웃 처리
	@Override
	public void logout(HttpServletRequest req) {
		// TODO Auto-generated method stub
		req.getSession().invalidate(); //세션 데이터 무효화
	}

}
