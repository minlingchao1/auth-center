package com.lingchaomin.auth.cas.plugin.qq;

import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;
import org.scribe.utils.Preconditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午6:05
 * @description 用于获取qq返回的access_token
 */
public class QQJsonTokenExtractor {

    private Pattern accessTokenPattern = Pattern.compile("\"access_token\":\\s*\"(\\S*?)\"");

    public Token extract(String response){
        Preconditions.checkEmptyString(response, "Cannot extract a token from a null or empty String");
        Matcher matcher = accessTokenPattern.matcher(response);
        if(matcher.find()){
            return new Token(matcher.group(1), "", response);
        }
        else{
            throw new OAuthException("Cannot extract an acces token. Response was: " + response);
        }
    }
}
