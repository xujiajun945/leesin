package com.dabanjia.leesin.base.dao;

import com.dabanjia.leesin.api.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xujiajun
 * @since 2019/12/24
 */
public interface UserMapper {

	/**
	 * 根据id获取
	 *
	 * @param id id
	 * @return user
	 */
	User getById(Long id);

	/**
	 * 插入
	 *
	 * @param user 用户信息
	 * @return 影响的条数
	 */
	Long insert(User user);

	/**
	 * 根据id移除
	 *
	 * @param id id
	 */
	void removeById(Long id);

	/**
	 * 根据id物理删除
	 *
	 * @param id id
	 */
	void deleteById(Long id);

	/**
	 * 根据id集合批量删除
	 *
	 * @param ids ids
	 * @return 影响的条数
	 */
	Long deleteByIds(@Param("ids") List<Long> ids);

	/**
	 * 根据id修改
	 *
	 * @param user 用户信息
	 */
	void updateById(User user);

	/**
	 * 根据ids批量获取用户列表
	 *
	 * @param ids ids
	 * @return 用户列表
	 */
	List<User> listByIds(@Param("ids") List<Long> ids);

	/**
	 * 根据手机号查询
	 *
	 * @param phone 手机号
	 * @return 用户信息
	 */
	User getByPhone(String phone);
}
