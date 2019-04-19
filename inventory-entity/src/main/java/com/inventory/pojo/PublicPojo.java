package com.inventory.pojo;

/**
 * @Description:POJO类公有变量
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */
public class PublicPojo {

    /**
     * 创建人
     */
    protected Integer createOp;

    /**
     * 创建时间
     */
    protected String createDate;

    /**
     * 修改人
     */
    protected Integer modifyOp;

    /**
     * 修改时间
     */
    protected String modifyDate;

    /**
     * 删除状态，默认0,删除为1
     */
    protected String deleteState;

    /**
     * 删除时间
     */
    protected String deleteDate;

    public Integer getCreateOp() {
        return createOp;
    }

    public void setCreateOp(Integer createOp) {
        this.createOp = createOp;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getModifyOp() {
        return modifyOp;
    }

    public void setModifyOp(Integer modifyOp) {
        this.modifyOp = modifyOp;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }
}
