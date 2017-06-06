package com.lingchaomin.auth.cas.authentication;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/17 下午3:28
 * @description 云贝用户名密码登录
 */
public class YBQueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

    private static final Logger log= LoggerFactory.getLogger(YBQueryDatabaseAuthenticationHandler.class);
    @NotNull
    private String passwordSql;

    @NotNull
    private String saltSql;

    public YBQueryDatabaseAuthenticationHandler() {
    }

    protected final HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential) throws GeneralSecurityException, PreventedException {
        String username = credential.getUsername();


        try {
            String e =this.getJdbcTemplate().queryForObject(this.passwordSql, String.class, new Object[]{username});
            String salt=this.getJdbcTemplate().queryForObject(this.saltSql, String.class, new Object[]{username});

            String encryptedPassword = this.getPasswordEncoder().encode(credential.getPassword()+salt);
            log.info("密码验证:{},{}",encryptedPassword,e);
            if(!e.equals(encryptedPassword)) {
                throw new FailedLoginException("Password does not match value on record.");
            }
        } catch (IncorrectResultSizeDataAccessException var5) {
            if(var5.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            }

            throw new FailedLoginException("Multiple records found for " + username);
        } catch (DataAccessException var6) {
            throw new PreventedException("SQL exception while executing query for " + username, var6);
        }

        return this.createHandlerResult(credential, new SimplePrincipal(username), (List)null);
    }


    public void setSaltSql(String saltSql) {
        this.saltSql = saltSql;
    }

    public void setPasswordSql(String passwordSql) {
        this.passwordSql = passwordSql;
    }
}
