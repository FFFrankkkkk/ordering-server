package bean;

public class Userinformation {

	private Integer userInformationId;
	private Integer userId;
	private String sex;
	private String address;
	public Integer getUserInformationId() {
		return userInformationId;
	}
	public void setUserInformationId(Integer userInformationId) {
		this.userInformationId = userInformationId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}