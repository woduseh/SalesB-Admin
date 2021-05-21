package handong.capstone2019s.salesb.app.admin.storelist;

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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.dozermapper.core.Mapper;

import handong.capstone2019s.salesb.domain.model.Store;
import handong.capstone2019s.salesb.domain.repository.admin.AdminRepository;
import handong.capstone2019s.salesb.domain.service.admin.storelist.AdminStoreListService;

@Slf4j
@Controller
@SessionAttributes(types = Store.class)
public class AdminStoreListController {

	@Inject
	AdminRepository adminRepository;

	@Inject
	AdminStoreListService adminStoreListService;

	@Inject
	Mapper beanMapper;

	@RequestMapping(value = "admin/storelist")
	public String RequestController(Authentication authentication, @ModelAttribute @Valid AdminStoreListForm storelist,
			BindingResult result, Model model, @PageableDefault Pageable pageable) {

		log.info("initial view");

		/*
		 * if (result.hasErrors()) { return "admin/form";}
		 */

		String adminName = authentication.getName();

		AdminStoreListForm criteria = beanMapper.map(storelist, AdminStoreListForm.class);

		Page<Store> page = adminStoreListService.showStoreList(criteria, adminName, pageable);
		model.addAttribute("page", page);

		return "admin/storeListForm";
	}

	@ModelAttribute("AdminStoreListForm")
	public AdminStoreListForm setUpSearchAdminRequestForm() {
		AdminStoreListForm adminStoreListForm = new AdminStoreListForm();

		return adminStoreListForm;
	}

	/*
	 * @RequestMapping(method = RequestMethod.GET, params = "initStoreListForm")
	 * public String searchInitForm(SessionStatus status) { status.setComplete();
	 * return "redirect:/admin/storeList?StoreListForm"; }
	 * 
	 * @RequestMapping(method = RequestMethod.GET, params = "StoreListForm") public
	 * String searchForm() { return "admin/storeListForm"; }
	 */

	@RequestMapping(value = "admin/storelist", method = RequestMethod.GET)
	public String Search(Authentication authentication, @ModelAttribute @Valid AdminStoreListForm storelist,
			BindingResult result, Model model, @PageableDefault Pageable pageable) {

		log.info("initial view");

		if (result.hasErrors()) {
			return "admin/storeListForm";
		}

		if (log.isDebugEnabled()) {
			log.debug("pageable={}", pageable);
		}

		String adminName = authentication.getName();

		AdminStoreListForm criteria = beanMapper.map(storelist, AdminStoreListForm.class);

		Page<Store> page = adminStoreListService.showStoreList(criteria, adminName, pageable);
		model.addAttribute("page", page);

		int pageSize = page.getNumberOfElements();

		model.addAttribute("pageSize", pageSize);

		return "admin/storeListForm";
	}

	@RequestMapping(value = "admin/storelist/{storeCode}/expired")
	public String updateStoreExpiredTrue(Authentication authentication, @PathVariable("storeCode") String storeCode) {
		adminStoreListService.updateStoreExpiredTrue(storeCode);
		
		return "admin/storeExpiredChangeSuccess";
	}
	
	@RequestMapping(value = "admin/storelist/{storeCode}/notexpired")
	public String updateStoreExpiredFalse(Authentication authentication, @PathVariable("storeCode") String storeCode) {
		adminStoreListService.updateStoreExpiredFalse(storeCode);
		
		return "admin/storeExpiredChangeSuccess";
	}

}