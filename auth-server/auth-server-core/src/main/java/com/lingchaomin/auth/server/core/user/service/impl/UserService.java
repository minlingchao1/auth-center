package com.lingchaomin.auth.server.core.user.service.impl;


import com.google.common.collect.Lists;

import com.lingchaomin.auth.core.dto.Node;
import com.lingchaomin.auth.core.dto.NodeIdComparator;
import com.lingchaomin.auth.core.dto.QQUserDto;
import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.core.exception.ErrorCode;
import com.lingchaomin.auth.core.exception.YunbeiOauthException;
import com.lingchaomin.auth.server.common.dto.OperErrorCode;
import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.common.utils.MD5Util;
import com.lingchaomin.auth.server.common.utils.MobileUtil;
import com.lingchaomin.auth.server.common.utils.RandomUtil;
import com.lingchaomin.auth.server.common.utils.SmsUtil;
import com.lingchaomin.auth.server.core.app.dao.AppDao;
import com.lingchaomin.auth.server.core.app.entity.App;
import com.lingchaomin.auth.server.core.role.dao.AuthorizationDao;
import com.lingchaomin.auth.server.core.role.dao.ResourceDao;
import com.lingchaomin.auth.server.core.role.dao.RoleDao;
import com.lingchaomin.auth.server.core.role.entity.Authorization;
import com.lingchaomin.auth.server.core.role.entity.Resource;
import com.lingchaomin.auth.server.core.user.constant.OauthClient;
import com.lingchaomin.auth.server.core.user.constant.UserStatus;
import com.lingchaomin.auth.server.core.user.dao.QQUserDao;
import com.lingchaomin.auth.server.core.user.dao.QQUserRefDao;
import com.lingchaomin.auth.server.core.user.dao.UserDao;
import com.lingchaomin.auth.server.core.user.dto.UserSelectDto;
import com.lingchaomin.auth.server.core.user.entity.QQUser;
import com.lingchaomin.auth.server.core.user.entity.QQUserRef;
import com.lingchaomin.auth.server.core.user.entity.User;
import com.lingchaomin.auth.server.core.user.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.NonNull;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午4:08
 * @description 用户信息
 */
@Service
public class UserService implements IUserService {

    private static final Logger LOG= LoggerFactory.getLogger(UserService.class);

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

    @Autowired
    private QQUserDao qqUserDao;

    /**
     * 列表
     */
    public List<User> list(String searchValue) {
        List<User> users = userDao.selectAll(searchValue);
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
                .userId(RandomUtil.getRand())
                .mobile(mobile)
                .userNick(nick)
                .descr(descr)
                .salt(salt)
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
     * 获取用户信息
     */
    public User getUser(String appKey, String userName,Boolean isMobile) {
        if(isMobile){
            User user= userDao.findByMobile(userName);
            return user;
        }else {
            QQUserRef qqUserRef = qqUserRefDao.findQQUserByOpenId(userName);
            User user=userDao.findByUserIdAndStatus(qqUserRef.getUserId(),UserStatus.UN_LOCKED);
            return user;
        }
    }

    /**
     * 获取角色
     */
    public List<String> getRole4QQLogin(String appKey,Long userId) {

        return findRolesByUserId(appKey,userId);
    }

    /**
     * 获取权限
     */
    public List<String> getPermission4QQLogin(String appKey,Long userId) {

        return findPermissionsByUserId(appKey, userId);
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
            // find user info
             user=userDao.findByUserIdAndStatus(qqUserRef.getUserId(), UserStatus.UN_LOCKED);
        }


        if (user == null) {
            throw new YunbeiOauthException(ErrorCode.USER_HAS_LOCKED, "该账号已经被锁定", token);
        }
        return user;
    }

    /**
     * 根据shirokey查找当前用户
     */
    public UserDto findByPrincipal(String key) {

        if(MobileUtil.checkMobile(key)){
            return getUserDtoByMobile(key);
        }else {
            return getUserDtoByQQ(key);
        }
    }



    private UserDto getUserDtoByQQ(String key){

        if(StringUtils.isEmpty(key)){
            return null;
        }
        QQUserRef qqUserRef=qqUserRefDao.findQQUserByOpenId(key);



        if(qqUserRef==null){
            throw new YunbeiOauthException(ErrorCode.USER_NOT_BIND, "qq 用户未绑定本地用户", key);
        }

        User user=userDao.findByUserIdAndStatus(qqUserRef.getUserId(),UserStatus.UN_LOCKED);

        if (user == null) {
            throw new YunbeiOauthException(ErrorCode.USER_HAS_LOCKED, "该账号已经被锁定", key);
        }


        QQUser qqUser=qqUserDao.findByUserId(user.getUserId());

        if(qqUser==null){
            throw new YunbeiOauthException(ErrorCode.QQ_USER_NOT_EXITST, "qq用户不存在", key);
        }

       // LOG.warn("user:{},qqUser:{}",user.toString(),qqUser.toString());

        UserDto userDto=new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFigureUrl(qqUser.getFigureUrl());
        userDto.setNick(qqUser.getNickName());
        userDto.setOpenId(qqUser.getOpenId());
        return userDto;
    }

    private UserDto getUserDtoByMobile(String key){

        User user=userDao.findByMobile(key);

        if(user==null){
            return null;
        }

        if (user.getLocked()) {
            throw new YunbeiOauthException(ErrorCode.USER_HAS_LOCKED, "该账号已经被锁定", key);
        }

       // LOG.warn("user:{},key:{}",user.toString(),key);

        QQUser qqUser=qqUserDao.findByUserId(user.getUserId());

        if(qqUser==null){
            throw new YunbeiOauthException(ErrorCode.QQ_USER_NOT_EXITST, "qq用户不存在", key);
        }

        //LOG.warn("user:{},qqUser:{}",user.toString(),qqUser.toString());

        UserDto userDto=new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFigureUrl(qqUser.getFigureUrl());
        userDto.setNick(qqUser.getNickName());
        return userDto;
    }

    /**
     * 添加用户
     */
    public OperateResultDto addUser(String client, String openId, String nick, String figureUrl) {

        if(client.equals(OauthClient.QQ)){
            QQUser qqUser=QQUser.builder()
                    .openId(openId)
                    .figureUrl(figureUrl)
                    .nickName(nick)
                    .build();

            qqUserDao.insert(qqUser);
        }
        return null;
    }

    /**
     * 获取菜单 for om
     */
    public List<Node> getMenuList(String appKey, String key) {

        if(StringUtils.isEmpty(key)){
            LOG.warn("key is null!!!!!!!");
            return new ArrayList<Node>();
        }

        boolean isMobile=MobileUtil.checkMobile(key);

        User user=getUser(appKey,key,isMobile);

        List<Node> nodes= Lists.newArrayList();

        if(user==null){
            return nodes;
        }


        App app = appDao.selectByAppKey(appKey);

        if (app == null) {
            return new ArrayList<Node>();
        }

        Authorization authorizations=authorizationDao.findByUserIdAndAppId(user.getUserId(),app.getId());

        if(authorizations==null){
            return new ArrayList<Node>();
        }

        String roleIds=authorizations.getRoleIds();

        if(StringUtils.isEmpty(roleIds)){
            return new ArrayList<Node>();
        }

        List<String> roleIdList= Arrays.asList(roleIds.split(","));

        List<String> resourceIds=roleDao.selectResourceByRoleIds(roleIdList);

        Set<String> resourcesSet=new HashSet<String>();

        for(String s:resourceIds){
            resourcesSet.addAll(Arrays.asList(s.split(",")));
        }

        List<Resource> resources=resourceDao.selectByResourceIds(resourcesSet);

        HashMap nodeList=new HashMap();

        List<Node> root=new ArrayList<Node>();

        // 根据结果集构造节点列表（存入哈希表）
        for(Iterator it=resources.iterator();it.hasNext();){
            Resource resource=(Resource)it.next();
            Node node=new Node();

            node.setId(resource.getId());
            node.setName(resource.getName());
            node.setParentId(resource.getParentId());
            node.setUrl(resource.getUrl());
            nodeList.put(node.getId(),node);
        }

        // 构造无序的内存多叉树
        Set entrySet=nodeList.entrySet();
        for(Iterator it=entrySet.iterator();it.hasNext();){

            Node node=(Node)((Map.Entry)it.next()).getValue();

            LOG.warn("node:{}",node.toString());
            if(node.getParentId()==0){
                root.add(node);
            }else {
                Node parentNode=(Node)nodeList.get(node.getParentId());

                if(parentNode==null){
                    LOG.warn("parentNode:{} is null~~~~~",node.getParentId());
                }else {
                    parentNode.getChildren().addChild(node);
                }
            }
        }


        //按照id排序
        for(Node r:root){
            r.sortChildren();
        }

        //根节点按照id排序
        Collections.sort(root, new NodeIdComparator());

        return root;
    }

    /**
     * 获取客服部门人员
     */
    public List<QQUserDto> getCustomerService(String appKey) {

        App app=appDao.selectByAppKey(appKey);

        List<Long> roleIds=new ArrayList<Long>();
        //销售
        roleIds.add(16l);

        //客服
        roleIds.add(10l);

        //客服主管
        roleIds.add(8l);

        List<QQUserDto> userBindQQDtos=userDao.selectCustomerService(app.getId(),roleIds);
        return userBindQQDtos;
    }

    private List<String> findRolesByUserId(String appKey, Long userId) {

        App app = appDao.selectByAppKey(appKey);

        if (app == null) {
            LOG.warn("app is null~~~~~~");
            return new ArrayList<String>();
        }

        Authorization authorization = authorizationDao.findByUserIdAndAppId(userId, app.getId());

        String rolesIds = authorization.getRoleIds();

        List<String> roleIdList = Arrays.asList(rolesIds.split(","));

        List<String> roles = roleDao.selectByRoleIds(roleIdList);

        //LOG.warn("get roles for user :{},{}",userId,roles.toString());

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
        List<String> resources = roleDao.selectResourceByRoleIds(roleIdList);

        String allResources = StringUtils.join(resources, ",");
        List<String> permissions = resourceDao.selectPermissionsByResourceIds(Arrays.asList(allResources.split(",")));

       // LOG.warn("get permissions for user :{},{}",userId,permissions.toString());

        return permissions;

    }


    public static void main(String[] args) {
        String salt = SmsUtil.get16Id();

        String password="2703697";

        System.out.printf(salt+","+MD5Util.MD5(password + salt));
    }
}
