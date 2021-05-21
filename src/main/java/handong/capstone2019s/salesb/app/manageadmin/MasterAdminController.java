package handong.capstone2019s.salesb.app.manageadmin;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.github.dozermapper.core.Mapper;

import handong.capstone2019s.salesb.domain.model.Admin;
import handong.capstone2019s.salesb.domain.service.admin.AdminManagementService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MasterAdminController {

	
    @Inject
    AdminManagementService adminService;
    @Inject
    Mapper beanMapper;
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    /*View 제작 중 !*/
    
	@RequestMapping(value = "admin/manageadmin")
	public String RequestList(Authentication authentication, @ModelAttribute @Valid AdminManageForm admin,
			BindingResult result, Model model, @PageableDefault Pageable pageable) {

		log.info("initial view");

		if (result.hasErrors()) {
			return "admin/adminForm";
		}

		if (log.isDebugEnabled()) {
			log.debug("pageable={}", pageable);
		}

		AdminManageForm criteria = beanMapper.map(admin, AdminManageForm.class);

		Page<Admin> page = adminService.selectAdmin(criteria, pageable);
		model.addAttribute("page", page);

		return "admin/adminForm";
	}

	@ModelAttribute("AdminManageForm")
	public AdminManageForm setUpSearchAdminManageForm() {
		AdminManageForm adminForm = new AdminManageForm();

		return adminForm;
	}

	@RequestMapping(value = "admin/manageadmin", method = RequestMethod.GET)
	public String Search(Authentication authentication, @ModelAttribute @Valid AdminManageForm admin,
			BindingResult result, Model model, @PageableDefault Pageable pageable) {

		log.info("initial view");

		if (result.hasErrors()) {
			return "admin/adminForm";
		}

		if (log.isDebugEnabled()) {
			log.debug("pageable={}", pageable);
		}

		AdminManageForm criteria = beanMapper.map(admin, AdminManageForm.class);

		Page<Admin> page = adminService.selectAdmin(criteria, pageable);
		model.addAttribute("page", page);

		int pageSize = page.getNumberOfElements();

		model.addAttribute("pageSize", pageSize);

		return "admin/adminForm";
	}

	@RequestMapping(value = "admin/admindetail", params = "admincode", method = RequestMethod.GET)
	public String View(Model model, @PageableDefault Pageable pageable, @RequestParam("admincode") int seq) {
		
		Page<Admin> page = adminService.selectAdminbyAdminCode(seq, pageable);
		model.addAttribute("page", page);

		int pageSize = page.getNumberOfElements();

		model.addAttribute("pageSize", pageSize);

		return "admin/view";
	}
}
