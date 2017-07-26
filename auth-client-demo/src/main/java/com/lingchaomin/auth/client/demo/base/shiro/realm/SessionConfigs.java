package com.lingchaomin.auth.client.demo.base.shiro.realm;

/**
 * @author: mlc
 * @dat: 2016年6月25日
 * @Description: TODO
 */

public class SessionConfigs {

	/**
	 * session相关配置
	 */
	public static class SESSION {

		public static final String SESSION_QQUSER = "qquser";

		/**
		 * qq openId
		 */
		public final static String SESSION_OPEN_ID = "openId0829";

		/**
		 * qq昵称
		 */
		public final static String SESSION_NICK = "omNick";

		/**
		 * 等级
		 */
		public final static String SESSION_LEVEL = "level";

		/**
		 * 状态
		 */
		public final static String SESSION_STATUS = "omStatus";

		/**
		 * qq头像地址
		 */
		public final static String SESSION_QQIMG = "figureUrl";

		/**
		 * 部门id
		 */
		public final static String SESSION_DEPARTMENTID = "depId";

		/**
		 * 岗位Id
		 */
		public final static String SESSION_POSITIONID = "posId";

		/**
		 * 角色
		 */
		public final static String SESSION_ROLES = "roles";

		/**
		 * 登录IP
		 */
		public final static String LOGIN_IP = "loginIp";
	}

}
