package org.ikane.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ikane.service.AccountService;
import org.pac4j.springframework.security.authentication.ClientAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ClientUserDetailsService implements AuthenticationUserDetailsService<ClientAuthenticationToken> {

    private AccountService accountService;

    public UserDetails loadUserDetails(final ClientAuthenticationToken token) throws UsernameNotFoundException {
    	
        Map account = accountService.lookupAccountByProvider(token.getClientName(), token.getUserProfile().getId());

        //String username = account.containsKey("displayName") ? account.displayName : ""
        String username = "admin";

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role: token.getUserProfile().getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        if (!account.isEmpty() && authorities.isEmpty()) {
            // default to user role
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new ClientUserDetails(username, token.getUserProfile().getId(), authorities);
    }

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}