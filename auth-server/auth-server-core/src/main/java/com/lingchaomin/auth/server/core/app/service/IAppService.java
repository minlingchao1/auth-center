package com.lingchaomin.auth.server.core.app.service;

import com.lingchaomin.auth.server.core.app.dto.AppSelectDto;
import com.lingchaomin.auth.server.core.app.entity.App;
import com.yunbeitech.auth.common.dto.OperateResultDto;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午9:46
 * @description 应用信息
 */
public interface IAppService {

    /**
     * 统计数量
     * @return
     */
    long count(String searchValue);

    /**
     * 列表
     * @return
     */
    List<App> list(String searchValue);

    /**
     * 添加
     * @param
     * @return
     */
    OperateResultDto add(String name, String desc);

    /**
     * 修改
     * @param
     * @return
     */
    OperateResultDto modify(Long id,String name,String descr);

    /**
     * 删除
     * @param id
     * @return
     */
   OperateResultDto remove(Long id);

    /**
     * 更改状态
     * @param id
     * @param 
     * @return
     */
    OperateResultDto modifyAvailable(Long id);

    /**
     * app key查找
     * @param appKey
     * @return
     */
    App findByAppKey(String appKey);

    /**
     * 设置不可用
     * @param id
     * @return
     */
    OperateResultDto modifyUnAvailable(Long id);

    /**
     * 用于添加资源等情况使用
     * @return
     */
    List<AppSelectDto> selectAll4Resource();
}
