package com.debug.pmp.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//用户实体
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId
	private Long userId;

	@NotBlank(message="用户名不能为空")
	private String username;

	private String name;

	//@NotBlank(message="密码不能为空")
	private String password;

	private String salt;

	@NotBlank(message="邮箱不能为空!")
	private String email;

	@NotBlank(message="手机号不能为空!")
	private String mobile;

	private Integer status;

	//@TableField：字段属性不为数据库表字段，但又是必须使用的
	@TableField(exist=false)
	private List<Long> roleIdList;

	private Date createTime;

	@NotNull(message="部门Id不能为空!")
	private Long deptId;

	@TableField(exist=false)
	private String deptName;

	@TableField(exist=false)
	private List<Long> postIdList;

	@TableField(exist=false)
	private String postName;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Long> getPostIdList() {
		return postIdList;
	}

	public void setPostIdList(List<Long> postIdList) {
		this.postIdList = postIdList;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
}
