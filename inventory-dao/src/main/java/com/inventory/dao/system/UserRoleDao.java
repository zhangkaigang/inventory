package com.inventory.dao.system;

import com.inventory.po.system.UserRole;

import java.util.List;

public interface UserRoleDao {

    int addUserRole(UserRole userRole);

    int deleteUserRole(int userId);

    List<String> queryRolesByUserId(int userId);

}
