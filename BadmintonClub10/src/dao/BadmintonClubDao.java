package dao;

import java.util.List;

import entity.Athlete;
import entity.BadmintonClub;
import entity.Competition;
import entity.Field;

public interface BadmintonClubDao {
	// 协会的场地管理功能
	public List<Field> fieldInit();// 初始化羽毛球协会管理的信息

	public void showField(BadmintonClub bc);// 查看羽毛球协会的场地

	public void orderField(BadmintonClub bc);// 场地预定功能

	public void cancelField(BadmintonClub bc);// 场地预定功能
	// 协会的比赛管理功能

	public void createCompetition(BadmintonClub bc);// 创建比赛

	public void showCompetition(); // 查看出未举办的比赛信息

	public void deleteCompetition();// 删除比赛

	public void showResults();// 举行比赛，需要协会人员登录，登陆后可以选择输入一个比赛名称然后举行比赛
	
	public void addAthlete(BadmintonClub bc);//运动员参加比赛
	// 协会的运动员管理功能

	public void showAthlete();// 输出数据库中运动员的信息

	public void updateAthlete();// 更新数据库中的运动员信息
	//协会管理功能
	public void adminLogin();// 协会的管理员登录
	
	public void register();// 协会的管理员注册
	
}
