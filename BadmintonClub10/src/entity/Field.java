package entity;
import java.util.*;

public class Field {
	private String position;
	private List<Reservation> reservations;
	
	// getter和setter方法
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    
    //构造函数
    public Field() { //无参构造
		
	}
	
    public Field(String position,List<Reservation> reservations) {
    	this.position=position;
    	this.reservations=reservations;
    }
    
    //场地是否在某个时间被预定
    public boolean isReserved(String timeSlot) {
        for (Reservation reservation : reservations) {
            if (reservation.getTimeSlot().equals(timeSlot)) {
                return true;
            }
        }
        return false;
    }
    
    //预定在某个时间使用场地
    public void reserve(String timeSlot) {
        Reservation reservation = new Reservation(timeSlot);
        reservations.add(reservation);
    }
    
    //取消场地在某个时间的预定
    public void cancelReservation(String timeSlot) {//取消预订
        for (Reservation reservation : reservations) {
            if (reservation.getTimeSlot().equals(timeSlot)) {
                reservations.remove(reservation);
                break;
            }
        }
    }
    
	

}
