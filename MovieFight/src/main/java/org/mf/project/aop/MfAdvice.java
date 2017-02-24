package org.mf.project.aop;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class MfAdvice {

	@Pointcut("execution(* org.mf.project..Admin*Controller.*(..))")
	public void admin_login() {
		// admin패키지 안에 있는 모든 메서드
	}

	@Pointcut("execution(* org.mf.project..*Controller.*_AL(..))")
	public void admin_login2() {
		// '_AL'로 끝나는 모든 메서드
	}

	@Pointcut("execution(* org.mf.project..*Controller.*_NL(..))")
	public void non_login() {
		// '_NL'로 끝나는 모든 메서드
	}

	@Pointcut("execution(* org.mf.project..*Controller.*_ML(..))")
	public void member_login() {
		// '_ML'로 끝나는 모든 메서드
	}

	@Around("admin_login() || admin_login2()")
	public String adminCheckSession(ProceedingJoinPoint joinPoint) throws Throwable {
		// 관리자 URL 체크

		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpServletResponse response = sra.getResponse();
		HttpSession session = request.getSession();

		String url = "redirect:/";

		try {
			Object memLevel = session.getAttribute("memLevel");

			if (memLevel == null || memLevel == "") {
				// memLevel 이 없거나 비어있는경우 ->비 로그인
				view_alert(response, "잘못된 요청 경로입니다.", "/");
			} else {
				if (!memLevel.equals("ADMIN")) {
					// 로그인은 했지만 관리자가 아닌경우
					view_alert(response, "잘못된 요청 경로입니다.", "/");
				} else {
					String str =(String) joinPoint.proceed(); 
					url = str;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("잘못된 접근입니다.");
		}
		return url;

	}

	@Around("member_login()")
	public String memberCheckSession(ProceedingJoinPoint joinPoint) throws Throwable {
		// 로그인 체크
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpServletResponse response = sra.getResponse();
		HttpSession session = request.getSession();

		String url = "redirect:/";

		try {
			Object memLevel = session.getAttribute("memLevel");

			if (memLevel == null || memLevel == "") {
				// memLevel 이 없거나 비어있는 경우 -> 비 로그인
				view_alert(response, "로그인 후 이용해주세요.", "/member/login1");
			} else {
				url = (String) joinPoint.proceed();
			}
		} catch (Exception e) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		return url;
	}

	@Around("non_login()")
	public String nonLoginCheckSession(ProceedingJoinPoint joinPoint) throws Throwable {

		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpServletResponse response = sra.getResponse();
		HttpSession session = request.getSession();
		String url = "redirect:/";
		try {
			Object memLevel = session.getAttribute("memLevel");

			if (!(memLevel == null || memLevel == "")) {
				// memLevel이 비어있지 않고 공백이 아닌경우 ->로그인상태
				view_alert(response, "잘못된 경로입니다.", "/");
			} else {
				url = (String) joinPoint.proceed();
			}

		} catch (Exception e) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		return url;
	}

	private void view_alert(HttpServletResponse response, String msg, String url) throws Exception {
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('" + msg + "');");
		out.print("location.href='" + url + "';");
		out.print("</script>");
		out.flush();
		out.close();
	}

}
