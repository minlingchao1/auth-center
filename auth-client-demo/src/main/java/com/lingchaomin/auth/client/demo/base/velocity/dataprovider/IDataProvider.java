package com.lingchaomin.auth.client.demo.base.velocity.dataprovider;

import java.util.Map;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/21 下午7:57
 * @description 数据提供端
 */
public interface IDataProvider {
    Map<String,Object> load() ;
}
