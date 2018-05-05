package com.ssm.pojo;

import java.io.Serializable;

public class User implements Serializable{
    /**  
	 * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	 */ 
	private static final long serialVersionUID = 5074952466322171517L;
	private int id;
    private String name;
    private String pws;
	public String getPws() {
		return pws;
	}
	public void setPws(String pws) {
		this.pws = pws;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
