package entity;
import java.sql.Time;
import java.util.*;

public class Competition {
	private int id;//数据库里统计用
	private String name;//比赛名字
    private List<Athlete> athletes;//运动员泛型集合一场比赛设定为8个人
    private Field field;//场地
    private int is1;//用来判断是否已经预定过场地
    private int is2;//用来判断是否已经进行过
    private String time;//比赛时间
    
    //getter/setter方法
    public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int isIs1() {
		return is1;
	}
	public void setIs1(int is1) {
		this.is1 = is1;
	}
	public int isIs2() {
		return is2;
	}
	public void setIs2(int is2) {
		this.is2 = is2;
	}
	public void setId(int id) {
		this.id = id;
	}
    public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Athlete> getAthletes() {
        return athletes;
    }
    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }
    public int getId() {
		return id;
	}
    
    //构造函数
    public Competition() {
    	
    }
    
    // 添加运动员到比赛
    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }
}

