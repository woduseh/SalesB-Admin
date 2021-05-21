package handong.capstone2019s.salesb.app.admin.request;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.dozermapper.core.Mapper;

import handong.capstone2019s.salesb.domain.model.Request;
import handong.capstone2019s.salesb.domain.repository.admin.AdminRepository;
import handong.capstone2019s.salesb.domain.service.admin.request.AdminRequestService;

@Slf4j
@Controller
@SessionAttributes(types = Request.class)
public class AdminRequestListController {

	@Inject
	AdminRepository adminRepository;

	@Inject
	AdminRequestService adminRequestService;

	@Inject
	Mapper beanMapper;

	@RequestMapping(value = "admin/request")
	public String RequestList(Authentication authentication, @ModelAttribute @Valid AdminRequestForm request,
			BindingResult result, Model model, @PageableDefault Pageable pageable) {

		log.info("initial view");

		if (result.hasErrors()) {
			return "admin/requestForm";
		}

		if (log.isDebugEnabled()) {
			log.debug("pageable={}", pageable);
		}

		String adminName = authentication.getName();

		AdminRequestForm criteria = beanMapper.map(request, AdminRequestForm.class);

		Page<Request> page = adminRequestService.showRequest(criteria, adminName, pageable);
		model.addAttribute("page", page);

		return "admin/requestForm";
	}

	@ModelAttribute("AdminRequestForm")
	public AdminRequestForm setUpSearchAdminRequestForm() {
		AdminRequestForm adminRequestForm = new AdminRequestForm();

		return adminRequestForm;
	}

	@RequestMapping(value = "admin/request", method = RequestMethod.GET)
	public String Search(Authentication authentication, @ModelAttribute @Valid AdminRequestForm request,
			BindingResult result, Model model, @PageableDefault Pageable pageable) {

		log.info("initial view");

		if (result.hasErrors()) {
			return "admin/requestForm";
		}

		if (log.isDebugEnabled()) {
			log.debug("pageable={}", pageable);
		}

		String adminName = authentication.getName();

		AdminRequestForm criteria = beanMapper.map(request, AdminRequestForm.class);

		Page<Request> page = adminRequestService.showRequest(criteria, adminName, pageable);
		model.addAttribute("page", page);

		int pageSize = page.getNumberOfElements();

		model.addAttribute("pageSize", pageSize);

		return "admin/requestForm";
	}

	@RequestMapping(value = "admin/requestdetail", params = "requestnum", method = RequestMethod.GET)
	public String View(Model model, @PageableDefault Pageable pageable, @RequestParam("requestnum") int seq) {
		
		Page<Request> page = adminRequestService.showRequestDetail(seq, pageable);
		model.addAttribute("page", page);

		int pageSize = page.getNumberOfElements();

		model.addAttribute("pageSize", pageSize);

		return "admin/view";
	}
}