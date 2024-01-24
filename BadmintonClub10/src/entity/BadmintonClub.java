package entity;

import java.util.ArrayList;
import java.util.List;

public class BadmintonClub {
	private List<Competition> olist = new ArrayList<Competition>(); // 比赛表，所有的比赛都在这里面登记
	private List<Field> flist = new ArrayList<Field>(); // 用来记录那九个场地的

	public List<Field> getFlist() {
		return flist;
	}

	public void setFlist(List<Field> flist) {
		this.flist = flist;
	}

	public List<Competition> getOlist() {
		return olist;
	}

	public void setOlist(List<Competition> olist) {
		this.olist = olist;
	}

	public Field findFieldByName(String name) {// 找到名称为name的特定场地
		for (Field field : flist) {
			if (field.getPosition().equals(name)) {
				return field;
			}
		}
		return null;
	}
	
	public Competition findCompetitionByName(String name) {// 找到名称为name的特定比赛
		for (Competition Competition : olist) {
			if (Competition.getName().equals(name)) {
				return Competition;
			}
		}
		return null;
	}
	
	public void addCompetition(Competition competition) {// 添加比赛
		olist.add(competition);
	}
}
