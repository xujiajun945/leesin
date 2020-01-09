package com.dabanjia.leesin.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xujiajun
 * @since 2019/12/24
 */
@Setter
@Getter
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = -1913730620694696494L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐值
	 */
	private String salt;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 是否删除
	 */
	private Boolean deleted;
}
