package com.lingchaomin.auth.cas.service;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/17 下午4:42
 * @description 添加qq用户
 */
public class QQUserService {

    @NotNull
    private DataSource dataSource;

    @NotNull
    private JdbcTemplate jdbcTemplate;

    @NotNull
    private String sql;

    public QQUserService() {
    }

    /**
     * INSERT
     * @param openId
     * @param nickName
     * @param figureUrl
     */

    public void insert(String openId,String nickName,String figureUrl){
        this.jdbcTemplate.update(this.sql,openId,nickName,figureUrl);

    }

    public final void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }



    public void setSql(String sql) {
        this.sql = sql;
    }
}
