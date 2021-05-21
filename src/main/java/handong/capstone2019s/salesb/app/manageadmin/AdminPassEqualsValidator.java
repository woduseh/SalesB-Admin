package handong.capstone2019s.salesb.app.manageadmin;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AdminPassEqualsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return (AdminForm.class).isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AdminForm admin = (AdminForm) target;
        String pass = admin.getAdminPass();
        String passConfirm = admin.getAdminPassConfirm();
        if (pass == null || passConfirm == null) {
            // must be checked by @NotEmpty
            return;
        }
        if (!pass.equals(passConfirm)) {
            errors.rejectValue("adminPass", "NotEquals.adminPass",
                    "Password and password confirm is not same.");
        }
    }
}
