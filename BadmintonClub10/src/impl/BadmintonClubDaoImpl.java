package impl;

import java.util.*;
import java.sql.*;
import entity.*;
import dao.BadmintonClubDao;
import dao.BaseDao;

public class BadmintonClubDaoImpl extends BaseDao implements BadmintonClubDao {

	private Connection conn = null;// 保存数据库的连接
	private PreparedStatement pstmt = null;// 用于执行SQL语句
	private ResultSet rs = null;// 用户保存查询结果表
	public static int competitionCount = 0;// 比赛的数量的统计
	public static int adminCount = 1;// 管理员的数量的统计
	public static boolean isAdmin = false;// 判断是否是管理员登录状态
	

	@Override
	public List<Field> fieldInit() {// 初始化场地
		// 1、预约时间表的初始化
		List<Reservation> reservationList1 = new ArrayList<Reservation>();
		List<Reservation> reservationList2 = new ArrayList<Reservation>();
		List<Reservation> reservationList3 = new ArrayList<Reservation>();
		List<Reservation> reservationList4 = new ArrayList<Reservation>();
		List<Reservation> reservationList5 = new ArrayList<Reservation>();
		List<Reservation> reservationList6 = new ArrayList<Reservation>();
		List<Reservation> reservationList7 = new ArrayList<Reservation>();
		List<Reservation> reservationList8 = new ArrayList<Reservation>();
		List<Reservation> reservationList9 = new ArrayList<Reservation>();
		Field field1 = new Field("东南1", reservationList1);
		Field field2 = new Field("东南2", reservationList2);
		Field field3 = new Field("东南3", reservationList3);
		Field field4 = new Field("西北1", reservationList4);
		Field field5 = new Field("西北2", reservationList5);
		Field field6 = new Field("西北3", reservationList6);
		Field field7 = new Field("西北4", reservationList7);
		Field field8 = new Field("西北5", reservationList8);
		Field field9 = new Field("西北6", reservationList9);
		List<Field> field = new ArrayList<Field>();
		field.add(field1);
		field.add(field2);
		field.add(field3);
		field.add(field4);
		field.add(field5);
		field.add(field6);
		field.add(field7);
		field.add(field8);
		field.add(field9);
		return field;
	}
	
	
	public void showField(BadmintonClub bc) {// 查看羽毛球协会的场地
		for (Field field : bc.getFlist()) {
			if (field.getReservations().size() == 0) {
				System.out.println("场地名：" + field.getPosition());
				System.out.println("预定时间：无");
			} else {
				System.out.println("场地名：" + field.getPosition());
				System.out.print("预定时间：");
				List<Reservation> reservations = field.getReservations();
				for (Reservation reservation : reservations) {
					System.out.println(reservation.getTimeSlot());
				}
			}
		}

	}

	public void showCompetition() {// 查看比赛信息
		String query = "SELECT * FROM competition WHERE id IS NOT NULL";
		try {
			conn = getConn();// 得到数据库连接
			pstmt = conn.prepareStatement(query);// 得到PreparedStatement对象
			rs = pstmt.executeQuery();// 执行sql语句

			while (rs.next()) {
				// 获取每列的值
				int id = rs.getInt("id");
				String name = rs.getString("Competitionname");
				String time = rs.getString("Time");
				String place = rs.getString("Place");
				int is2 = rs.getInt("is2");

				// 输出信息
				System.out.println("比赛ID: " + id);
				System.out.println("赛事名称: " + name);
				System.out.println("比赛时间: " + time);
				System.out.print("场地预定情况: ");
				if (place == null) {
					System.out.println("该场比赛还未预定场地");
				}else {
					System.out.println(place);
				}
				
				System.out.print("赛事进行情况: ");
				if (is2 == 0) {
					System.out.println("该场比赛还未进行");
				}else {
					System.out.println("该场比赛已经举行完毕");
				}

				System.out.println("--------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}

	}

	public void showAthlete() {// 查看所有运动员信息
		String query = "SELECT * FROM athlete WHERE id IS NOT NULL";
		try {
			conn = getConn();// 得到数据库连接
			pstmt = conn.prepareStatement(query);// 得到PreparedStatement对象
			rs = pstmt.executeQuery();// 执行sql语句

			while (rs.next()) {
				// 获取每列的值
				int id = rs.getInt("id");
				String name = rs.getString("Athletename");
				String gender = rs.getString("Gender");
				String level = rs.getString("Level");

				// 输出信息
				System.out.println("运动员ID: " + id);
				System.out.println("运动员姓名: " + name);
				System.out.println("运动员性别: " + gender);
				System.out.println("运动员级别: " + level);

				System.out.println("--------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}

	}

	public void updateAthlete() {// 更新运动员的信息
		// 要更新的数据信息
		int id = 1; // 要更新的特定id值
		String newName = "New Name";
		String newGender = "New Gender";
		String newLevel = "New Level";

		try {
			conn = getConn();// 得到数据库连接

			// 创建更新语句
			String sql = "UPDATE athlete SET Athletename = ?, Gender = ?, Level = ? WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);

			System.out.println("请输入需要更改的运动员编号：");
			Scanner input = new Scanner(System.in);
			id = input.nextInt();
			System.out.println("请输入新的姓名：");
			newName = input.next();
			System.out.println("请输入新的性别(1男，2女)：");
			newGender = input.next();
			System.out.println("请输入新的级别(a,b,c,d,e)：");
			newLevel = input.next();

			// 设置更新参数
			statement.setString(1, newName);
			statement.setString(2, newGender);
			statement.setString(3, newLevel);
			statement.setInt(4, id);

			// 执行更新操作
			int rowsAffected = statement.executeUpdate();

			// 检查更新结果
			if (rowsAffected > 0) {
				System.out.println("数据更新成功！");
			} else {
				System.out.println("未找到匹配的数据，更新失败！");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
	}

	public void createCompetition(BadmintonClub bc) {// 创建比赛
		String competition_name;
		String competition_time;
		try {
			conn = getConn();// 得到数据库连接

			// 创建更新语句
			String insertQuery = "INSERT INTO competition (id, Competitionname, Time) VALUES (?, ?, ?)";
			// 创建PreparedStatement对象
			PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

			System.out.println("请输入比赛名称：");
			Scanner input = new Scanner(System.in);
			competition_name = input.next();
			System.out.println("请输入比赛时间：");
			competition_time = input.next();
			
			// 设置name和time的值
			competitionCount++;
			preparedStatement.setInt(1, competitionCount);
			preparedStatement.setString(2, competition_name);
			preparedStatement.setString(3, competition_time);

			// 执行插入操作
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("插入成功！");
				Competition c = new Competition();
				c.setName(competition_name);
				c.setTime(competition_time);
				bc.addCompetition(c);
			} else {
				System.out.println("插入失败！");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
	}

	public void deleteCompetition() {// 删除比赛
		int idToDelete = 1; // 要删除的id值
		try {
			conn = getConn();// 得到数据库连接

			String deleteQuery = "DELETE FROM competition WHERE id = ?";

			// 创建PreparedStatement对象
			PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);

			System.out.println("请输入需要删除的比赛的id的值：");
			Scanner input = new Scanner(System.in);
			idToDelete = input.nextInt();

			// 设置id的值
			preparedStatement.setInt(1, idToDelete);

			// 执行删除操作
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败，未找到匹配的行！");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
	}

	public void orderField(BadmintonClub bc) {// 场地预定
		String name;// 场地名称
		int id;
		System.out.println("请输入需要预定的场地名称的值：");
		Scanner input = new Scanner(System.in);
		name = input.next();
		System.out.println("请输入比赛的id：");
		id = input.nextInt();

		try {
			conn = getConn();// 得到数据库连接

			// 创建查询语句
			String query = "SELECT Time FROM competition WHERE id = ?";

			// 创建预编译语句
			PreparedStatement statement = conn.prepareStatement(query);

			// 设置参数
			statement.setInt(1, id);

			// 执行查询
			ResultSet resultSet = statement.executeQuery();

			// 处理结果集
			if (resultSet.next()) {
				String time = resultSet.getString("time");
				System.out.println("比赛时间: " + time);
				if (bc.findFieldByName(name).isReserved(time)) {
					System.out.println("该场地的这段时间已经被预定，预定失败!");
				} else {
					bc.findFieldByName(name).reserve(time);
					// 更新比赛表中是否被预定的信息
					String updateQuery1 = "UPDATE competition SET is1 = ? WHERE id = ?";
					PreparedStatement updateStatement1 = conn.prepareStatement(updateQuery1);
					updateStatement1.setInt(1, 1);
					updateStatement1.setInt(2, id);
					updateStatement1.executeUpdate();
					// 更新比赛表中预定场地的信息
					String updateQuery2 = "UPDATE competition SET Place = ? WHERE id = ?";
					PreparedStatement updateStatement2 = conn.prepareStatement(updateQuery2);
					updateStatement2.setString(1, name);
					updateStatement2.setInt(2, id);
					updateStatement2.executeUpdate();

					System.out.println("预定成功!");

				}
			} else {
				System.out.println("没有比赛记录！");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}

	}

	public void cancelField(BadmintonClub bc) {// 取消场地预定
		String name;// 场地名称
		int id;
		System.out.println("请输入需要取消预定的场地名称的值：");
		Scanner input = new Scanner(System.in);
		name = input.next();
		System.out.println("请输入比赛的id：");
		id = input.nextInt();

		try {
			conn = getConn();// 得到数据库连接

			// 创建查询语句
			String query = "SELECT Time FROM competition WHERE id = ?";

			// 创建预编译语句
			PreparedStatement statement = conn.prepareStatement(query);

			// 设置参数
			statement.setInt(1, id);

			// 执行查询
			ResultSet resultSet = statement.executeQuery();

			// 处理结果集
			if (resultSet.next()) {
				String time = resultSet.getString("time");
				System.out.println("比赛时间: " + time);
				if (bc.findFieldByName(name).isReserved(time)) {
					bc.findFieldByName(name).cancelReservation(time);
					// 更新比赛表中是否被预定的信息
					String updateQuery1 = "UPDATE competition SET is1 = ? WHERE id = ?";
					PreparedStatement updateStatement1 = conn.prepareStatement(updateQuery1);
					updateStatement1.setInt(1, 0);
					updateStatement1.setInt(2, id);
					updateStatement1.executeUpdate();
					// 更新比赛表中预定场地的信息
					String updateQuery2 = "UPDATE competition SET Place = ? WHERE id = ?";
					PreparedStatement updateStatement2 = conn.prepareStatement(updateQuery2);
					updateStatement2.setString(1, null);
					updateStatement2.setInt(2, id);
					updateStatement2.executeUpdate();

					System.out.println("该场地取消预定成功!");
				} else {
					System.out.println("该时间没有被预定!");

				}
			} else {
				System.out.println("没有比赛记录！");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
	}

	public void adminLogin() {// 管理员登录
		// 输入的用户名和密码
		String inputUsername = "input-username";
		String inputPassword = "input-password";
		try {
			conn = getConn();// 得到数据库连接

			// 创建PreparedStatement对象
			String sql = "SELECT Adminname, Password FROM admin";
			PreparedStatement stmt = conn.prepareStatement(sql);

			boolean found = false;
			while (!found) {
				System.out.print("请输入用户名：");
				Scanner input = new Scanner(System.in);
				inputUsername = input.next();
				System.out.print("\n请输入密码：");
				inputPassword = input.next();

				// 执行查询
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					String dbUsername = rs.getString("Adminname");
					String dbPassword = rs.getString("Password");

					// 比较用户名和密码
					if (dbUsername.equals(inputUsername) && dbPassword.equals(inputPassword)) {
						System.out.println("登录成功");
						isAdmin = true;
						found = true;
						break;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
	}

	public void register() {//管理员账号注册功能
		if( isAdmin == false ) {
			System.out.println("清登陆后使用注册功能");
			return;
		}
		// 输入的用户名和密码
		String inputUsername = "input-username";
		String inputPassword = "input-password";
		try {
			conn = getConn();// 得到数据库连接

			// 创建PreparedStatement对象
			String insertQuery = "INSERT INTO admin (id, Adminname, Password) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(insertQuery);

			System.out.println("请输入用户名：");
			Scanner input = new Scanner(System.in);
			inputUsername = input.next();
			System.out.println("请输入密码：");
			inputPassword = input.next();
			
			// 设置name和time的值
			adminCount++;
			stmt.setInt(1, competitionCount);
			stmt.setString(2, inputUsername);
			stmt.setString(3, inputPassword);

			// 执行插入操作
			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("注册成功！");
			} else {
				System.out.println("注册失败！");
			}
			
				
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
	}
	
	public void addAthlete(BadmintonClub bc) {//运动员参加比赛
		try {
			conn = getConn();// 得到数据库连接
			
			System.out.print("请输入举办的的比赛id：");
			Scanner input=new Scanner(System.in);
			int id = input.nextInt();
			
			// 创建查询语句
			String query = "SELECT * FROM competition WHERE id = ?";
			// 创建预编译语
			PreparedStatement statement1 = conn.prepareStatement(query);
			// 设置参数
			statement1.setInt(1, id);
			// 执行查询
			ResultSet resultSet = statement1.executeQuery();
			//得到比赛举办的时间
			String time = null;
			String competitionName = null;
			if(resultSet.next()) {
				time = resultSet.getString("Time");
				competitionName = resultSet.getString("Competitionname");
			}
			
			System.out.println("添加运动员：");
			int choose = 1;
			String name;
			do {
				System.out.print("输入运动员名称：");
				name = input.next();
				//判断这名运动员再time时间是否有要参加的比赛
				int flag = 0;
				String query2 = "SELECT Time FROM score WHERE  Athletename= ?";
				PreparedStatement statement2 = conn.prepareStatement(query2);
				statement2.setString(1, name);
				ResultSet resultSet2 = statement2.executeQuery();
				if (resultSet2.next()) {
					String athleteTime = resultSet2.getString("Time");
					if( athleteTime.equals(time)) {
						flag = 1;
					}
				}
				
				if( flag == 1) {
					System.out.println("该运动员在"+time+"时间已经有参加的比赛了");
				}else {
					//进行插入操作
					String insertQuery = "INSERT INTO score (Mid, Athletename, Time) VALUES (?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, name);
					preparedStatement.setString(3, time);
					// 执行插入操作
					int rowsAffected = preparedStatement.executeUpdate();
					
					if (rowsAffected > 0) {
						
						
						//向比赛的athlete表中添加运动员
						
						int Aid = 0;
						String Aname = null;
						String Alevel = null;
						String query3 = "SELECT * FROM athlete WHERE Athletename = ?";
						PreparedStatement statement3 = conn.prepareStatement(query3);
						statement3.setString(1, name);
						ResultSet rs = statement3.executeQuery(); // 执行查询

						if (rs.next()) {
							Aid = rs.getInt("id");
							Aname = rs.getString("Athletename");
							Alevel = rs.getString("Level");		
						}
						Athlete a = new Athlete(Aid, Aname, Alevel);
						Competition competition=new Competition();
						competition = bc.findCompetitionByName(competitionName);
						competition.addAthlete(a);
						
						System.out.println("运动员参加比赛成功！");
						
					} else {
						System.out.println("运动员参加比赛失败！");
					}
				}
				
				System.out.print("输入0退出添加运动员：");
				choose = input.nextInt();
			}while( choose != 0 );
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
	}
	
	public void showResults() {// 举行比赛
		Scanner input=new Scanner(System.in);
		this.adminLogin();
		int choose=1;
		Competition competition=new Competition();
		do {
			System.out.print("请输入要进行的比赛名称：");
			String cname=input.next();
			try {
				conn = getConn(); // 获取数据库连接
				String query = "SELECT * FROM competition WHERE Competitionname = ?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, cname); // 设置参数值
				ResultSet rs = pstmt.executeQuery(); // 执行查询

				if (rs.next()) {
				    competition.setId(rs.getInt("Id"));
				    competition.setName(rs.getString("Competitionname"));
				    Field field1 = new Field();
				    field1.setPosition(rs.getString("Place"));
				    competition.setField(field1);
				    competition.setTime("Time");
				    competition.setIs1(rs.getInt("is1"));
				    competition.setIs2(rs.getInt("is2"));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
				this.closeAll(conn, pstmt, rs);
			}
			
	 		if(competition==null) {
	 			System.out.println("未找到，是否要重新进行输入?1:是 2:退出");
	 			choose=input.nextInt();	
	 		}else {
	 			break;
	 		}
		}while(choose==1);
		
		if(choose==1) {//等于一从上面那个循环出来了，证明是找到了
			if(competition.isIs2()==1) { //为true证明已经比过了，不能再进行，直接退出这个函数，不再重新进行选择
				System.out.println("该比赛已经结束");
			}else {//为false，证明未比赛过
				if(competition.isIs1()==0) {
					System.out.println("该比赛未预约场地，不能进行比赛");
				}else {//已经预约场地了//比赛未进行，已经预约了场地，有运动员报名，可以进行比赛
						//先把比赛的运动员拿出来
						List<Athlete> athleteList=competition.getAthletes();
						int i=0;
						int[] a=new int[athleteList.size()];
						for(i=0;i<athleteList.size();i++) {
							Athlete athlete = athleteList.get(i);;
							switch(athlete.getLevel()) {
							case (String) "a":
								a[i]=(int)(Math.random()*100);
								athlete.setMark(a[i]);
								break;
							case (String) "b":
								a[i]=(int)(Math.random()*90);
								athlete.setMark(a[i]);
								break;
							case (String) "c":
								a[i]=(int)(Math.random()*80);
								athlete.setMark(a[i]);
								break;
							case (String) "d":
								a[i]=(int)(Math.random()*70);
								athlete.setMark(a[i]);
								break;
							case (String) "e":
								a[i]=(int)(Math.random()*60);
								athlete.setMark(a[i]);
								break;
							default:
								break;
						    }
					    }
						//给比赛分数排个名
						int j=0;//外层循环用，从左往右，依次替换为最大值
						int w=0;//内层循环用，用来找最大值
						int n=0;//用来记录每次最大值的下标
						int max=0;
						for(j=0;j<athleteList.size();j++) {
							for(w=j;w<athleteList.size();w++) {
								Athlete athlete = athleteList.get(w);
								if(a[w]>max) {
									max=a[w];
									n=w;
								}
							int p=0;//用于中间交换
							p=a[j];
							a[j]=max;
							a[n]=p;
							}
						}
						for(i=0;i<athleteList.size();i++) {//根据分数排名给运动员排名
							Athlete athlete = athleteList.get(i);;
							for(int c=0;c<athleteList.size();c++) {
								if(athlete.getMark()==a[c]) {
									athlete.setRank(c+1);
								}
							}
						}
						//接下来是把比赛表中那个那个比赛的is2改为1，然后把这次比赛的成绩记录到ｓｃｏｒｅ表中
						competition.setIs2(1);
						//更新比赛表
						try {
							conn=getConn();//得到数据库连接
					        String sql2 = "UPDATE competition SET is2 = ? WHERE Competitionname = ?";
					        pstmt = conn.prepareStatement(sql2);
					        pstmt.setInt(1, 1);
					        pstmt.setString(2, competition.getName());  
					        pstmt.executeUpdate();
						}catch(SQLException e) {
							e.printStackTrace();
						}catch(ClassNotFoundException e) {
							e.printStackTrace();
						}finally {
							this.closeAll(conn, pstmt, rs);
						}
						//更新score表
						try {
							conn=getConn();//得到数据库连接
							for(i=0;i<athleteList.size();i++) {//根据分数排名给运动员排名
								//更新比赛表中是否被预定的信息
			        			String updateQuery1 = "UPDATE score SET Score = ?, Rank = ? WHERE Athletename = ?";
			                    PreparedStatement updateStatement1 = conn.prepareStatement(updateQuery1);
			                    Athlete athlete = athleteList.get(i);
			                    updateStatement1.setInt(1, athlete.getMark());
			                    updateStatement1.setInt(2, athlete.getRank());
			                    updateStatement1.setString(3,athlete.getName());
			                    updateStatement1.executeUpdate();
								}
						}catch(SQLException e) {
							e.printStackTrace();
						}catch(ClassNotFoundException e) {
							e.printStackTrace();
						}finally {
							this.closeAll(conn, pstmt, rs);
						}
						
						}
					}
				}
          
		}
		
	
}

