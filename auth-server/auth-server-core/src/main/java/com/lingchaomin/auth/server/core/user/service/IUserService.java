package com.lingchaomin.auth.server.core.user.service;


import com.lingchaomin.auth.core.dto.Node;
import com.lingchaomin.auth.core.dto.QQUserDto;
import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.core.user.dto.UserSelectDto;
import com.lingchaomin.auth.server.core.user.entity.User;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午4:06
 * @description
 */
public interface IUserService {


    /**
     * 列表
     * @return
     */
    List<User> list(String searchValue);

    /**
     * 统计数量
     * @return
     */
    OperateResultDto count(String searchValue);

    /**
     * 删除
     * @param id
     * @return
     */
    OperateResultDto remove(Long id);


    /**
     * 修改
     * @return
     */
    OperateResultDto modify(Long id, String name, String value);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 注册
     * @param mobile
     * @param nick
     * @param descr
     * @param password
     * @param openId
     * @return
     */
    OperateResultDto register(String mobile, String nick, String descr, String password, String openId);

    /**
     * 绑定qq
     * @param openId
     * @param mobile
     * @return
     */
    OperateResultDto bindQQ(String openId, String mobile);

    /**
     * 检测用户
     * @param mobile
     * @return
     */
    OperateResultDto check(String mobile);

    /**
     * 锁定用户
     * @param id
     * @return
     */
    OperateResultDto modifyLock(Long id);

    /**
     * 解锁用户
     * @param id
     * @return
     */
    OperateResultDto modifyUnLock(Long id);

    /**
     * 获取用户
     * @return
     */
    List<UserSelectDto> select4Auth();

    /**
     * 获取用户信息
     * @param appKey
     * @param userName
     * @return
     */
    User getUser(String appKey, String userName, Boolean isMobile);

    /**
     * 获取角色
     * @param appKey
     * @return
     */
    List<String> getRole4QQLogin(String appKey, Long userId);

    /**
     * 获取权限
     * @param appKey
     * @return
     */
    List<String> getPermission4QQLogin(String appKey, Long userId);

    /**
     * 检测用户是否合法
     * @param s
     * @param s1
     */
    User checkUserIsLegal(String s, String s1);

    /**
     * 根据shirokey查找当前用户
     * @param key
     * @return
     */
    UserDto findByPrincipal(String key);

    /**
     * 添加用户
     * @param openId
     * @param nick
     * @param figureUrl
     * @return
     */
    OperateResultDto addUser(String client, String openId, String nick, String figureUrl);


    /**
     * 获取菜单 for om
     * @return
     */
    List<Node> getMenuList(String appKey, String key);

    /**
     * 获取客服部门人员
     * @param appKey
     * @return
     */
    List<QQUserDto> getCustomerService(String appKey);
}
