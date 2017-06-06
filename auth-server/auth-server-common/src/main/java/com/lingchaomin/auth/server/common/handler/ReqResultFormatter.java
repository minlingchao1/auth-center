package com.lingchaomin.auth.server.common.handler;


import com.lingchaomin.auth.server.common.dto.OperErrorCode;
import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.common.dto.PagingResultDto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午9:53
 * @description 格式化操作结果
 */
public class ReqResultFormatter {

    /**
     * 格式化分页结果
     * @param count
     * @param result
     * @return
     */
    public static PagingResultDto formatPagingResult(Long count, Object result){
        PagingResultDto pagingResultDto=new PagingResultDto();
        pagingResultDto.setCount(count);
        pagingResultDto.setResult(result);
        return pagingResultDto;
    }

    /**
     * 格式化操作成功结果
     * @param result
     * @return
     */
    public static OperateResultDto formatOperSuccessDto(Object result){
        OperateResultDto operateResultDto=new OperateResultDto(true);
        operateResultDto.setResult(result);
        operateResultDto.setMsg(OperErrorCode.SUCCESS.getValueZn());
        return operateResultDto;
    }

    /**
     * 格式化失败结果
     * @param operErrorCode
     * @return
     */
    public static OperateResultDto formatFailDto(OperErrorCode operErrorCode){
        OperateResultDto operateResultDto=new OperateResultDto(false);
        operateResultDto.setMsg(operErrorCode.getValueZn());
        return operateResultDto;
    }
}
