package handong.capstone2019s.salesb.app.manageadmin;

import java.io.Serializable;

import lombok.Data;

/**
 * Customer form object.
 */
@Data
public class AdminManageForm implements Serializable {
	
    /**
	 * serialVersion.
	 */
	private static final long serialVersionUID = 8813781733671862359L;
    private String adminName;
    private String adminMail;
    private String adminPass;
    private String adminPassConfirm;
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
