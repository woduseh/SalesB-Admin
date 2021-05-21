package handong.capstone2019s.salesb.domain.service.authority;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import handong.capstone2019s.salesb.domain.model.Admin;

public class AuthorityAdminDetails implements UserDetails{
    private static final long serialVersionUID = 1L;
    
    private Admin admin;
    

    private List<? extends GrantedAuthority> authorities;

    public AuthorityAdminDetails(Admin admin) {
    	String auth = admin.getAdminAuthority();
    	// 구분자 중요!
    	String[] temp = auth.split(", ");
    	
        this.admin = admin;
        this.authorities = AuthorityUtils.createAuthorityList(temp);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.admin.getAdminPass();
    }

    @Override
    public String getUsername() {
        return this.admin.getAdminName();
    }

    public Admin getAdmin() {
        return admin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
