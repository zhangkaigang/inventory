package com.inventory.dao.system;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/11
 * @Version:1.0
 */
public interface EnumsDao {

    List<Map<String,Object>> getAllEnums();

    List<Map<String,Object>> getAllEnumsKeyDescId();
}
