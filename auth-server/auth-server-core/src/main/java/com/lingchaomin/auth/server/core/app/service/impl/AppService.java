package com.lingchaomin.auth.server.core.app.service.impl;

import com.lingchaomin.auth.server.common.dto.OperErrorCode;
import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.core.app.constant.AppStatus;
import com.lingchaomin.auth.server.core.app.dao.AppDao;
import com.lingchaomin.auth.server.core.app.dto.AppSelectDto;
import com.lingchaomin.auth.server.core.app.entity.App;
import com.lingchaomin.auth.server.core.app.service.IAppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.NonNull;


/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/16 下午1:53
 * @description 应用相关服务
 */
@Service
public class AppService implements IAppService {

    @Autowired
    private AppDao appDao;

    /**
     * 统计数量
     */
    public long count(String searchValue) {
        return appDao.count(searchValue);
    }

    /**
     * 列表
     */
    public List<App> list(String searchValue) {
        return appDao.selectAll(null);
    }

    /**
     * 添加
     */
    public OperateResultDto add(@NonNull  String name, String desc) {
        App app = App.builder()
                .gmtCreate(new Date())
                .gmtModified(new Date())
                .name(name)
                .descr(desc)
                .appKey(UUID.randomUUID().toString())
                .appSecret(UUID.randomUUID().toString())
                .available(AppStatus.AVAILABLE)
                .build();

        long ret=appDao.insert(app);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 修改
     */
    public OperateResultDto modify(Long id, String name, String descr) {
        App app = App.builder()
                .id(id)
                .name(name)
                .descr(descr)
                .build();
        long ret= appDao.update(app);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 删除
     */
    public OperateResultDto remove(Long id) {

        long ret= appDao.deleteById(id);
        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 更改状态
     */
    public OperateResultDto modifyAvailable(Long id) {
        long ret= appDao.updateStatus(id, AppStatus.AVAILABLE);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 设置不可用
     */
    public OperateResultDto modifyUnAvailable(Long id) {

        long ret= appDao.updateStatus(id, AppStatus.UNAVAILABLE);
        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 用于添加资源等情况使用
     */
    public List<AppSelectDto> selectAll4Resource() {
       return appDao.selectAll4Resource();
    }


    /**
     * app key查找
     */
    public App findByAppKey(String appKey) {
        return appDao.selectByAppKey(appKey);
    }


}
