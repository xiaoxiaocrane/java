package entity;

public class Reservation {//预约时间表类
	private String timeSlot;

	//getter和setter
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	//构造函数
	Reservation(){
		
	}
	Reservation(String timeSlot){
		this.timeSlot=timeSlot;
	}
	
}
