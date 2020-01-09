package com.dabanjia.leesin.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@Setter
@Getter
@ToString
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -174334341752145737L;

	private Long id;

	private String phone;

	private String name;
}
