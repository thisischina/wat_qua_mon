package com.hd.ibus.pojo;

/**
 * 角色表
 */

public class Role {
    private Integer roleId;

    private String name;

    private String power;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", power='" + power + '\'' ;
    }
}