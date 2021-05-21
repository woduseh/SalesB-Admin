package handong.capstone2019s.salesb.app.admin.menu;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dozermapper.core.Mapper;

import handong.capstone2019s.salesb.app.admin.request.AdminRequestForm;
import handong.capstone2019s.salesb.domain.model.Request;
import handong.capstone2019s.salesb.domain.repository.admin.AdminRepository;
import handong.capstone2019s.salesb.domain.service.admin.request.AdminRequestService;

@Slf4j
@Controller
public class AdminMenuController {

	/**
	 * forward initial menu view
	 * 
	 * @return menu view
	 */

	/*
	 * @RequestMapping("/admin") public String init() { log.info("initial view");
	 * 
	 * return "admin/menu"; }
	 */

	@Inject
	AdminRepository adminRepository;

	@Inject
	AdminRequestService adminRequestService;

	@Inject
	Mapper beanMapper;

    @RequestMapping("/")
    public String toMain(){
        return "redirect:admin";
    }
    
    @RequestMapping("/admin")
	public String RequestController(Authentication authentication, @ModelAttribute @Valid AdminRequestForm request,
			BindingResult result, Model model, @PageableDefault Pageable pageable) {

		log.info("initial view");

		if (authentication != null) {
			
			String adminName = authentication.getName();
			
			AdminRequestForm criteria = beanMapper.map(request, AdminRequestForm.class);

			Page<Request> page = adminRequestService.showLimitedRequest(criteria, adminName, pageable);
			model.addAttribute("page", page);
			
			int pageSize = page.getNumberOfElements();
			
			model.addAttribute("pageSize", pageSize);
		}

		return "admin/menu";
	}

}