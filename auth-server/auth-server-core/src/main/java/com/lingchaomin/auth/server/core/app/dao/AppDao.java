package com.lingchaomin.auth.server.core.app.dao;


import com.lingchaomin.auth.server.core.app.dto.AppSelectDto;
import com.lingchaomin.auth.server.core.app.entity.App;
import com.lingchaomin.auth.server.core.base.dao.IDao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午9:44
 * @descriptionn 应用信息
 */

@Repository
public interface AppDao extends IDao<App> {

    /**
     * 查找全部
     * @return
     */
    List<App> selectAll(@Param("searchValue") String searchValue);

    long count(@Param("searchValue") String searchValue);

    /**
     * 更新
     * @param app
     * @return
     */
    long update(App app);

    /**
     * 更新状态
     * @param id
     * @param available
     * @return
     */
    long updateStatus(@Param("id") Long id, @Param("available") Boolean available);

    /**
     * 根据appkey查找
     * @param appKey
     * @return
     */
    App selectByAppKey(@Param("appKey") String appKey);

    /**
     * 用于资源选择使用
     * @return
     */
    List<AppSelectDto> selectAll4Resource();

}

