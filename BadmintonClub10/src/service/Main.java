package service;

import java.util.*;
import dao.*;
import dao.BadmintonClubDao;
import entity.*;
import impl.*;

public class Main {

	public static void main(String[] args) {
		BadmintonClubDao bcd = new BadmintonClubDaoImpl();
		BadmintonClub bc = new BadmintonClub();
		bc.setFlist(bcd.fieldInit());

		Scanner input = new Scanner(System.in);
		int choose;

		do {
			System.out.println("欢迎使用羽毛球运动协会管理系统:");
			System.out.println("***************************:");
			System.out.println("请输入数字:");
			System.out.println("1、查看比赛信息");
			System.out.println("2、查看场地");
			System.out.println("3、查看运动员信息");
			System.out.println("4、更新运动员信息");
			System.out.println("5、创建比赛");
			System.out.println("6、删除比赛");
			System.out.println("7、场地预定");
			System.out.println("8、取消预定");
			System.out.println("9、管理员登录");
			System.out.println("10、管理员注册");
			System.out.println("11、运动员参加比赛");
			System.out.println("12、进行比赛");
			System.out.println("0、退出系统");
			System.out.println("-------------------------------------");
			choose = input.nextInt();

			switch (choose) {
			case 1:// 查看比赛信息
				bcd.showCompetition();
				break;
			case 2:// 查看场地信息
				bcd.showField(bc);
				break;
			case 3:// 查看所有运动员信息
				bcd.showAthlete();
				break;
			case 4:// 更新运动员信息
				bcd.updateAthlete();
				break;
			case 5:
				bcd.createCompetition(bc);
				break;
			case 6:
				bcd.deleteCompetition();
				break;
			case 7:
				bcd.orderField(bc);
				break;

			case 8:
				bcd.cancelField(bc);
				break;

			case 9:
				bcd.adminLogin();
				break;
			case 10:
				bcd.register();
				break;
			case 11:
				bcd.addAthlete(bc);
				break;
			case 12:
				bcd.showResults();
			case 0:
				break;
			default:

			}
		} while (choose != 0);

	}

}
