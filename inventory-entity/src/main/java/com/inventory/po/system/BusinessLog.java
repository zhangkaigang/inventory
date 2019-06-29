package com.inventory.po.system;

/**
 * @Description:业务日志
 * @Author:zhang.kaigang
 * @Date:2019/6/24
 * @Version:1.0
 */
public class BusinessLog {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 日志名称
     */
    private String logName;

    /**
     * 操作人ID
     */
    private Integer userId;

    /**
     * 操作人姓名(登录名)
     */
    private String operateName;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 简要操作信息
     */
    private String message;

    /**
     * 具体操作信息，点击查看详情时根据key和table进行更新展示
     */
    private String detail;

    /**
     * 创建时间
     */
    protected String createDate;

    //----冗余字段
    private String createDateStart;
    private String createDateEnd;

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }
}
