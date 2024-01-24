package entity;

import java.util.List;

public class Athlete {
	private long id;// 在数据库中的id
	private String name;// 运动员姓名
	private String gender;// 运动员性别
	private String level;// 运动员级别
	private int mark;// 运动员比赛分数
	private int rank;// 运动员一场比赛的排名
	private List<Reservation> reservations;// 运动员参赛时间表，用来防止同一时间参加多场比赛

	// getter/setter方法
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Athlete() {
		
	}
	
	public Athlete(int id, String name, String gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;	
	}
}
