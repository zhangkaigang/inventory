package com.inventory.service.impl.system;

import com.inventory.dao.system.EnumsDao;
import com.inventory.service.system.EnumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/11
 * @Version:1.0
 */
@Service
public class EnumsServiceImpl implements EnumsService {

    @Autowired
    private EnumsDao enumsDao;

    private List<Map<String, Object>> enumsList = new ArrayList<>();
    private static  Map<String, String> enumsKeyDescIdMap = new HashMap<>();

    /**
     * 初始化枚举值放在map集合中
     */
    @PostConstruct
    public void init() {
        getAllEnums();
        getAllEnumsKeyDescId();
    }

    /**
     * 获取全部枚举值
     */
    @SuppressWarnings("unchecked")
    public void getAllEnums(){
        enumsList = enumsDao.getAllEnums();
    }

    @Override
    public String getEnumsIdByKeywordAndDesc(String keywordAndEnumsDesc) {
        if(enumsKeyDescIdMap.isEmpty()){
            getAllEnumsKeyDescId();
        }
        return enumsKeyDescIdMap.get(keywordAndEnumsDesc)==null?"":enumsKeyDescIdMap.get(keywordAndEnumsDesc);
    }

    /**
     * 获取全部枚举值
     */
    @SuppressWarnings("unchecked")
    public void getAllEnumsKeyDescId(){
        List<Map<String,Object>> allEnumsKeyDescIdList = enumsDao.getAllEnumsKeyDescId();
        for(Map<String, Object> map : allEnumsKeyDescIdList){
            enumsKeyDescIdMap.put(map.get("KEYDESC").toString(), map.get("ENUMS_ID").toString());
        }
    }
}
