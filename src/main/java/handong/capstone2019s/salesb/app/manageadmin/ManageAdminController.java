package handong.capstone2019s.salesb.app.manageadmin;

import javax.inject.Inject;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import com.github.dozermapper.core.Mapper;

import handong.capstone2019s.salesb.domain.model.Admin;
import handong.capstone2019s.salesb.domain.service.admin.AdminManagementService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

/**
 * Handle request of customer register.
 */
@Slf4j
@Controller
@RequestMapping(value = "admins")
@TransactionTokenCheck(value = "admins")
public class ManageAdminController {

    @Inject
    AdminManagementService adminService;

    @Inject
    AdminPassEqualsValidator passwordEqualsValidator;

    @Inject
    Mapper beanMapper;

    @InitBinder("adminForm")
    public void initBinder(WebDataBinder webDataBinder) {
        // add custom validators to default bean validations
        webDataBinder.addValidators(passwordEqualsValidator);
    }

    /**
     * pre-initialization of form backed bean
     * @return
     */
    @ModelAttribute
    public AdminForm setUpAdminForm() {
        AdminForm form = new AdminForm();
        log.debug("populate form {}", form);
        return form;
    }

    /**
     * pre-initialization of form backed bean
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET, params = "form")
    public String createForm() {
        return "manageuser/admin/createAdminForm";
    }

    /**
     * Return to main input screen
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, params = "redo")
    public String createRedo(AdminForm form) {
        return "manageuser/admin/createAdminForm";
    }

    /**
     * shows the confirmation screen before registering a new customer
     * @param form
     * @param result
     * @return
     */
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.BEGIN)
    @RequestMapping(value = "create", method = RequestMethod.POST, params = "confirm")
    public String createConfirm(@Validated AdminForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return createRedo(form);
        }
        
        // ID OVERLAP CHECK
        Admin admin = new Admin();
        admin.setAdminName(form.getAdminName());
        int overlap = adminService.nameOverlapChk(admin);
        if (overlap > 0)
        	return createRedo(form);
        
        return "manageuser/admin/createAdminConfirm";
    }

    /**
     * registers a new customer
     * @param form
     * @param result
     * @param redirectAttr
     * @return
     */
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.IN)
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Validated AdminForm form, BindingResult result,
            RedirectAttributes redirectAttr) {
        if (result.hasErrors()) {
            return createRedo(form);
        }

        Admin admin = beanMapper.map(form, Admin.class);

        Admin registeredAdmin = adminService.register(admin, form
                .getAdminPass());
        redirectAttr.addFlashAttribute(registeredAdmin);
        return "redirect:/admins/create?complete";
    }

    /**
     * Redirected to the result page after registering a customer
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET, params = "complete")
    public String createComplete() {
        return "manageuser/admin/createAdminComplete";
    }
    
    @ResponseBody
    @RequestMapping(value = "nameOverlapChk", method = RequestMethod.POST)
    public int nameOverlapChk(Admin admin) {
    	int result = adminService.nameOverlapChk(admin);
    	
    	return result;
    }
}
