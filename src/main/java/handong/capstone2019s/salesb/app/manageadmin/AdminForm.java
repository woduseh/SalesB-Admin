package handong.capstone2019s.salesb.app.manageadmin;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Customer form object.
 */
@Data
public class AdminForm implements Serializable {
	
    /**
	 * serialVersion.
	 */
	private static final long serialVersionUID = 8813781733671862359L;

    @NotEmpty
    @Size(max = 20)
    @Pattern(regexp = "[0-9a-zA-Z]+")
    private String adminName;
    
    @Email
    @Pattern(regexp="[a-zA-Z0-9]+@[a-z]+.[a-z]+$")
    @Size(max = 30)
    private String adminMail;

    @NotEmpty
    @Pattern(regexp = "[0-9a-zA-Z]+")
    @Size(min = 4, max = 20)
    private String adminPass;

    @NotEmpty
    @Pattern(regexp = "[0-9a-zA-Z]+")
    @Size(min = 4, max = 20)
    private String adminPassConfirm;

    @Pattern(regexp = "[0-9-]+[0-9]+$")
    @Size(min = 10, max = 13)
    private String adminTel;
    
    private String adminAuthority;

    @Override
    public String toString() {
        return "&adminName=" + adminName
                + "&adminMail=" + adminMail
                + "&adminPass=" + adminPass + "&adminPassConfirm="
                + adminPassConfirm + "&adminTel=" + adminTel;
    }

}
