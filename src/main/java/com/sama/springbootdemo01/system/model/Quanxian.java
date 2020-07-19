package com.sama.springbootdemo01.system.model;

/**
 * 权限表
 * @since 2019-03-06
 * @author fjk
 */
public class Quanxian {
    private Long id;
    //用户组id
    private Long groupid;
    //菜单id
    private Long menuid;
    //系统id
    private Long sysid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public Long getSysid() {
        return sysid;
    }

    public void setSysid(Long sysid) {
        this.sysid = sysid;
    }
}
