package org.mf.project.magazine.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mf.project.magazine.admin.service.AdminMagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminMagazineController {
	
	@Autowired
	private AdminMagazineService service;

	@RequestMapping(value = "/magazine/insert", method = RequestMethod.GET)
	public String insert() {
		//매거진 입력 폼 페이지

		return "magazine/MagazineInsert";
	}
	
	@RequestMapping(value="/magazine/insert",method=RequestMethod.POST)
	public String insert(@RequestParam Map map){
		//매거진 입력 처리 페이지
		service.insert(map);
		
		return "redirect:/magazine/list2/1";
	}
	
	@RequestMapping(value="/magazine/update/{magKey}",method=RequestMethod.GET)
	public String update(Model model,@PathVariable int magKey){
		//매거진 수정 폼 페이지
		service.update(model,magKey);
		
		return "magazine/MagazineUpdate";
	}
	
	@RequestMapping(value="/magazine/update",method=RequestMethod.POST)
	public String update(@RequestParam Map map){
		//매거진 수정 처리 페이지
		service.update(map);
		
		return "redirect:/magazine/content?magkey="+map.get("magKey");
	}
	
	@RequestMapping(value="/magazine/delete/{magKey}",method=RequestMethod.GET)
	public String delete(@PathVariable int magKey){
		//매거진 삭제 처리 페이지
		service.delete(magKey);
		
		return "redirect:/magazine/list2/1";
	}
	

	@RequestMapping(value = "/magazine/insert/image", method = RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile upload) {
		//매거진 입력 폼 이미지 업로드 처리 페이지
		service.imageUpload(request,response,upload);

	}
}
