package org.ikane.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

class ClientUserDetails implements UserDetails {

    private static final long serialVersionUID = 6523314653561682296L;

    String username;
    String providerId;
    Collection<GrantedAuthority> authorities;
    String password;
    
    public ClientUserDetails() {
		// TODO Auto-generated constructor stub
	}
    
	public ClientUserDetails(String username, String providerId, Collection<GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.providerId = providerId;
		this.authorities = authorities;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}