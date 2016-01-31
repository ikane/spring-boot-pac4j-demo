package org.ikane.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.cas.client.CasClient;
import org.pac4j.core.client.BaseClient;
import org.pac4j.core.client.Clients;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.RequiresHttpAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
    private Clients clients;
 
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	if (isAuthenticated()) {
            return "redirect:/";
        }
    	final WebContext context = new J2EContext(request, response);
        final CasClient casClient = (CasClient) clients.findClient(CasClient.class);
        model.addAttribute("casAuthUrl",  getClientLocation(casClient, context));
    	
        return "login";
    }
 
    public String getClientLocation(BaseClient client, WebContext context) {
    	
    	try {
			return ((CasClient)client).getRedirectAction(context, false).getLocation();
		} catch (RequiresHttpAction e) {
			e.printStackTrace();
			logger.error("error", e);
			return null;
		}
    	
        //return client.getRedirectAction(context, false, false).getLocation();
    }
    
    protected boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth instanceof AnonymousAuthenticationToken);
    }
}
