package com.company.find.bean;

public class People {
	int id;
	String num;
	String name;
	String sex;
	String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public People(int id2,String num2, String name2, String sex2, String cclass2, String mobile2, String email2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.num=num2;
		this.name=name2;
		this.sex=sex2;
		this.cclass=cclass2;
		this.mobile=mobile2;
		this.email=email2;
	}
	public People() {
		
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	String cclass;
	String mobile;
	String email;
	String sc;
	
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String string) {
		this.num = string;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCclass() {
		return cclass;
	}
	public void setCclass(String cclass) {
		this.cclass = cclass;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String pname(Object name) {
	    String pname = name.toString();
	    
	    // �����Ǻŵ�λ��
	    int starIndex = pname.indexOf('*');
	    
	    // ����ҵ��Ǻ�
	    if (starIndex != -1) {
	        // ��ȡ�Ǻ�ǰ��Ĳ�����Ϊ����
	        String extractedName = pname.substring(0, starIndex);
	        return extractedName;
	    } else {
	        // ���û���ҵ��Ǻţ�����ԭʼ�ַ���
	        return pname;
	    }
	}

}
