package com.lingchaomin.auth.server.web.controller;

import com.lingchaomin.auth.cas.shiro.common.constant.ShiroConstant;
import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.common.utils.DateUtil;
import com.lingchaomin.auth.server.core.user.constant.SessionStatus;
import com.lingchaomin.auth.server.core.user.dto.SessionDto;
import com.lingchaomin.auth.server.core.user.service.IUserService;
import com.lingchaomin.auth.server.web.base.shiro.SessionConfig;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/22 下午4:51
 * @description
 */
@Controller
@RequestMapping("session")
public class SessionCtrl {

    private static final Logger LOG= LoggerFactory.getLogger(SessionCtrl.class);


    @Autowired
    private IUserService userService;

    @Autowired
    private SessionDAO sessionDAO;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        Collection<Session> sessions=sessionDAO.getActiveSessions();


        List<SessionDto> sessionDtos=new ArrayList<SessionDto>();

        for(Session session:sessions){

            LOG.warn("~~~~~~~~~~~~~~~~~~~~~~principalCollection:{}",session.getAttributeKeys().toString());
            PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

            if(principalCollection==null){
                continue;
            }

            String key=principalCollection.getPrimaryPrincipal().toString();
            UserDto userDto= SessionConfig.USER_CACHE.getIfPresent(key);

            if(userDto==null){
                 userDto=userService.findByPrincipal(key);
                 if(userDto!=null){
                     SessionConfig.USER_CACHE.put(key,userDto);
                 }

            }


            Integer status= SessionStatus.NORMAL;
            if(session.getAttributeKeys().contains(ShiroConstant.SESSION_FORCE_LOGOUT_KEY)){
                status=SessionStatus.FORCE_LOG_OUT;
            }

            if(userDto!=null){
                SessionDto sessionDto=SessionDto.builder()
                        .ip(session.getHost())
                        .lastLoginTime(DateUtil.formDateForYMDHMS(session.getLastAccessTime().getTime()))
                        .startTime(DateUtil.formDateForYMDHMS(session.getStartTimestamp().getTime()))
                        .nick(userDto.getNick())
                        .sessionId(session.getId().toString())
                        .status(status)
                        .build();
                sessionDtos.add(sessionDto);
            }

        }
        LOG.warn("sessionDto:{}",sessionDtos.toString());

        model.addAttribute("sessions",sessionDtos);

        return "session/session";
    }

    @RequestMapping(value = "forceLogout",method = RequestMethod.POST)
    @ResponseBody
    public Object forceLogout( String sessionId){
        Session session=sessionDAO.readSession(sessionId);
        if(session!=null){
            session.setAttribute(ShiroConstant.SESSION_FORCE_LOGOUT_KEY,Boolean.TRUE);
            sessionDAO.update(session);
        }else {
            LOG.warn("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~session is null~~~~~~~");
        }

       return ReqResultFormatter.formatOperSuccessDto(null);
    }
}
