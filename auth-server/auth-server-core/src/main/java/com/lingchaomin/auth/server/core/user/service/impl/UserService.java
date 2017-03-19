package com.lingchaomin.auth.server.core.user.service.impl;


import com.lingchaomin.auth.server.core.app.dao.AppDao;
import com.lingchaomin.auth.server.core.app.entity.App;
import com.lingchaomin.auth.server.core.base.exception.ErrorCode;
import com.lingchaomin.auth.server.core.base.exception.YunbeiOauthException;
import com.lingchaomin.auth.server.core.role.dao.AuthorizationDao;
import com.lingchaomin.auth.server.core.role.dao.ResourceDao;
import com.lingchaomin.auth.server.core.role.dao.RoleDao;
import com.lingchaomin.auth.server.core.role.entity.Authorization;
import com.lingchaomin.auth.server.core.user.constant.OauthClient;
import com.lingchaomin.auth.server.core.user.constant.UserStatus;
import com.lingchaomin.auth.server.core.user.dao.QQUserRefDao;
import com.lingchaomin.auth.server.core.user.dao.UserDao;
import com.lingchaomin.auth.server.core.user.dto.UserSelectDto;
import com.lingchaomin.auth.server.core.user.entity.QQUserRef;
import com.lingchaomin.auth.server.core.user.entity.User;
import com.lingchaomin.auth.server.core.user.service.IUserService;
import com.yunbeitech.auth.common.dto.OperErrorCode;
import com.yunbeitech.auth.common.dto.OperateResultDto;
import com.yunbeitech.auth.common.handler.ReqResultFormatter;
import com.yunbeitech.auth.common.utils.MD5Util;
import com.yunbeitech.auth.common.utils.SmsUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.NonNull;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午4:08
 * @description 用户信息
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private QQUserRefDao qqUserRefDao;

    @Autowired
    private AppDao appDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ResourceDao resourceDao;

    /**
     * 根据用户id和status查找
     */
    public User findByUserIdAndStatus(Long userId, Boolean status) {
        return userDao.findByUserIdAndStatus(userId, status);
    }

    /**
     *
     * @param mobile
     * @return
     */
    public User findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }


    /**
     * 列表
     */
    public List<User> list(String searchValue) {
        List<User> users = userDao.findAll(searchValue);
        return users;
    }

    /**
     * 统计数量
     */
    public OperateResultDto count(String searchValue) {
        return ReqResultFormatter.formatOperSuccessDto(userDao.count(searchValue));
    }

    /**
     * 删除
     */
    public OperateResultDto remove(Long id) {

        long ret = userDao.deleteById(id);

        if (ret > 0) {
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }


    /**
     * 修改
     */
    public OperateResultDto modify(Long id, String name, String value) {

        long ret = userDao.updateByParams(id, name, value);

        if (ret > 0) {
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 根据id查找
     */
    public User findById(Long id) {
        return userDao.selectById(id);
    }

    /**
     * 注册
     */
    public OperateResultDto register(@NonNull String mobile, @NonNull String nick, String descr, String password, @NonNull String openId) {
        if (StringUtils.isBlank(mobile)) {
            return ReqResultFormatter.formatFailDto(OperErrorCode.LACK_PARAMS);
        }

        if (StringUtils.isBlank(password)) {
            return ReqResultFormatter.formatFailDto(OperErrorCode.PASSWORD_IS_EMPTY);
        }

        User user = userDao.findByMobile(mobile);

        if (user != null) {
            return ReqResultFormatter.formatFailDto(OperErrorCode.USER_HAS_REGISTERED);
        }

        Date now = new Date();
        String salt = SmsUtil.get16Id();

        user = User.builder()
                .gmtCreate(new Date())
                .gmtModified(new Date())
                .mobile(mobile)
                .userNick(nick)
                .descr(descr)
                .password(password)
                .entryPassword(MD5Util.MD5(password + salt))
                .locked(UserStatus.UN_LOCKED)
                .build();

        long ret = userDao.insert(user);

        if (ret > 0) {
            return bindQQ(openId, mobile);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 绑定qq用户
     */
    public OperateResultDto bindQQ(@NonNull String openId, @NonNull String mobile) {

        User user = userDao.findByMobile(mobile);

        if (user == null) {
            return ReqResultFormatter.formatFailDto(OperErrorCode.USER_NOT_EXIST);
        }

        QQUserRef qqUserRef = qqUserRefDao.findByUserId(user.getUserId());

        if (qqUserRef != null) {
            return ReqResultFormatter.formatFailDto(OperErrorCode.USER_HAS_BEEN_BINDED);
        }


        qqUserRef = QQUserRef.builder()
                .userId(user.getUserId())
                .openId(openId)
                .gmtCreate(new Date())
                .gmtModified(new Date())
                .build();

        long ret = qqUserRefDao.insert(qqUserRef);

        if (ret > 0) {
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 检测用户
     */
    public OperateResultDto check(@NonNull String mobile) {

        User user = userDao.findByMobile(mobile);

        if (user != null) {
            return ReqResultFormatter.formatFailDto(OperErrorCode.USER_HAS_REGISTERED);
        }

        return ReqResultFormatter.formatOperSuccessDto(null);
    }

    /**
     * 锁定用户
     */
    public OperateResultDto modifyLock(@NonNull Long id) {


        long ret = userDao.updateStatus(id, UserStatus.LOCKED);
        if (ret > 0) {
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 解锁用户
     */
    public OperateResultDto modifyUnLock(@NonNull Long id) {

        long ret = userDao.updateStatus(id, UserStatus.UN_LOCKED);
        if (ret > 0) {
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 获取用户
     */
    public List<UserSelectDto> select4Auth() {
        return userDao.select4Auth();
    }

    /**
     * 获取角色
     */
    public List<String> getRole4QQLogin(String appKey, String username) {
        QQUserRef qqUserRef = qqUserRefDao.findQQUserByOpenId(username);
        return findRolesByUserId(appKey, qqUserRef.getUserId());
    }

    /**
     * 获取权限
     */
    public List<String> getPermission4QQLogin(String appKey, String username) {
        QQUserRef qqUserRef = qqUserRefDao.findQQUserByOpenId(username);
        return findPermissionsByUserId(appKey, qqUserRef.getUserId());
    }

    /**
     * 检测用户是否合法
     */
    public User checkUserIsLegal(String client, String token) {
        User user = null;

        //如果没有传，则默认为用户名密码登录
        if (StringUtils.isBlank(client)) {
            client = OauthClient.USER_AND_PASSWORD;
        }

        //qq login
        if (client.equals(OauthClient.QQ)) {
            QQUserRef qqUserRef = qqUserRefDao.findQQUserByOpenId(token);
            if (qqUserRef == null) {
                throw new YunbeiOauthException(ErrorCode.USER_NOT_BIND, "qq 用户未绑定本地用户", token);
            }
            // find user infouser=userLogic.findByUserIdAndStatus(qqUserRef.getUserId(), UserStatus.UN_LOCKED);
        }


        if (user == null) {
            throw new YunbeiOauthException(ErrorCode.USER_HAS_LOCKED, "该账号已经被锁定", token);
        }
        return user;
    }

    private List<String> findRolesByUserId(String appKey, Long userId) {

        App app = appDao.selectByAppKey(appKey);

        if (app == null) {
            return new ArrayList<String>();
        }

        Authorization authorization = authorizationDao.findByUserIdAndAppId(userId, app.getId());

        String rolesIds = authorization.getRoleIds();

        List<String> roleIdList = Arrays.asList(rolesIds.split(","));

        List<String> roles = roleDao.selectByRoleIds(roleIdList);

        return roles;
    }


    private List<String> findPermissionsByUserId(String appKey, Long userId) {
        App app = appDao.selectByAppKey(appKey);

        if (app == null) {
            return new ArrayList<String>();
        }

        Authorization authorization = authorizationDao.findByUserIdAndAppId(userId, app.getId());
        String rolesIds = authorization.getRoleIds();

        List<String> roleIdList = Arrays.asList(rolesIds.split(","));
        List<String> resources = roleDao.selectByRoleIds(roleIdList);

        String allResources = StringUtils.join(resources, ",");
        List<String> permissions = resourceDao.selectPermissionsByResourceIds(Arrays.asList(allResources.split(",")));

        return permissions;

    }
}
