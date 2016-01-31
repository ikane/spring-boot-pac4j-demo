package org.ikane.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	
	/**
	 * @Autowired
    JdbcTemplate jdbcTemplate
	 * */

	public Map lookupAccountByProvider(String providerName, String providerUserId) {
		HashMap<Object,Object> map = new HashMap<>();
		
		/**
		 *  
		 List results = jdbcTemplate.query(
                "select * from account where provider = ? and provider_user_id = ?",
                [providerName, providerUserId] as Object[],
                new GenericRowMapper()
        )

        if (results.size() > 1) {
            throw new Exception("multiple accounts by provider [${providerName}] for id [${providerUserId}]")
        }
        
		 * **/
		
		return map;
	}
	
	public Boolean createAccountForProvider(String providerName, String providerUserId, String displayName) {
	
		/**
		 *  log.debug("creating new account for displayName=${displayName} using provider=${providerName} with id ${providerUserId}")

        int result = jdbcTemplate.update(
                "insert into account (display_name, provider, provider_user_id) values (?, ?, ?)",
                displayName,
                providerName,
                providerUserId
        )

        if (result != 1) {
            log.warn("creation of account for provider [${providerName}] and id [${providerUserId}] failed")
            return false
        }
		 * */
		return true;
	}
}
