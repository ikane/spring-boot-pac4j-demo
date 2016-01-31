package org.ikane;

import org.ikane.security.ClientUserDetailsService;
import org.ikane.service.AccountService;
import org.pac4j.cas.client.CasClient;
import org.pac4j.core.client.Clients;
import org.pac4j.springframework.security.authentication.ClientAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Pac4jConfig {
	
	public static String CAS_LOGIN_URL = "https://casserverpac4j.herokuapp.com/login";
	
	@Value("${oauth.callback.url}")
    private String oauthCallbackUrl;
	
	@Autowired
    AccountService accountService;
	
	@Bean
	CasClient casClient() {
		return new CasClient(CAS_LOGIN_URL);
	}
	
	@Bean
    Clients clients() {
        return new Clients(oauthCallbackUrl, casClient());
    }
	
	

    @Bean
    ClientUserDetailsService clientUserDetailsService() {
    	ClientUserDetailsService clientUserDetailsService = new ClientUserDetailsService();
    	clientUserDetailsService.setAccountService(accountService);
    	return clientUserDetailsService;
    }
	
	@Bean
    ClientAuthenticationProvider clientProvider() {
		ClientAuthenticationProvider clientAuthenticationProvider = new ClientAuthenticationProvider();
		clientAuthenticationProvider.setClients(clients());
		clientAuthenticationProvider.setUserDetailsService(clientUserDetailsService());
		return clientAuthenticationProvider;
    }

}
