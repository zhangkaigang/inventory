package com.inventory.dao;

import com.inventory.po.UserRole;

import java.util.List;

public interface UserRoleDao {

    int addUserRole(UserRole userRole);

    int deleteUserRole(int userId);

    List<String> queryRolesByUserId(int userId);

}
