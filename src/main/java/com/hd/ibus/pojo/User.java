package com.hd.ibus.pojo;

public class User {
    private Integer id;

    private String account;

    private String password;

    private String name;

    private String tel;

    private String email;

    private Integer unitId;

    private Integer roleId;

    private String power;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }


//    @Resource
//    RoleService roleService;
//    public String getRoleName(Integer roleId){
//    PageHelp pageHelp;
//    Role r;
//    Role r1=null;
//    if(roleId!=null){
//        pageHelp=PageHelp.getInstance();
//        r=new Role();
//        pageHelp.setObject(r);
//        r1=roleService.getObject(pageHelp);
//    }
//    if(r1==null){
//        return "未设置";
//    }else{
//        return r1.getName();
//    }
//    }

}