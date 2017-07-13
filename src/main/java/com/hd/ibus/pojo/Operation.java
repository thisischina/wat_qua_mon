package com.hd.ibus.pojo;

import java.io.Serializable;
import java.util.Date;

public class Operation implements Serializable {

    private Integer id;
    private Integer userId;
    private String operationType;
    private String operationName;
    private Date acTime;

    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getOperationType() {
        return operationType;
    }


    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }


    public String getOperationName() {
        return operationName;
    }


    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }


    public Date getAcTime() {
        return acTime;
    }


    public void setAcTime(Date acTime) {
        this.acTime = acTime;
    }


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Operation other = (Operation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}