package com.ozcanmuhammet.polling.util;

import com.ozcanmuhammet.polling.common.enums.Roles;
import com.ozcanmuhammet.polling.entity.User;

public class UserTestUtil {

	public static User getAdminUser() {
		User user = new User();
		user.setId(1L);
		user.setRoleId(Roles.ADMIN_USER.getValue());
		user.setUserName("admin");
		return user;
	}
	
	public static User getEndUser() {
		User user = new User();
		user.setId(1L);
		user.setRoleId(Roles.END_USER.getValue());
		user.setUserName("mumi");
		return user;
	}
	
}
