package model;

import java.util.List;

import dao.MutterDao;

public class GetMutterListLogic {

	public List<Mutter> excute() {
		MutterDao dao = new MutterDao();
		List<Mutter> mutterList = dao.findAll();
		return mutterList;
	}
}
