package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDao {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/dokoTsubu";
	private final String JDBC_USER = "sa";
	private final String JDBC_ID = "";

	public List<Mutter> findAll() {

		//表示用のリストを作成＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿		
		List<Mutter> mutterList = new ArrayList<>();

		//JDBC読み込み＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBC読み込みエラー");
		}
		//JDBCを使ってセレクトを実行____________________________________________________

		try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_ID)) {

			String sql = "select id, name, text from mutters order by id desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String text = rs.getString("text");

				Mutter mutter = new Mutter(id,name, text);
				mutterList.add(mutter);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}

	//保存用メソッド＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿

	public boolean create(Mutter mutter) {

		//JDBC読み込み＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBC読み込みエラー");
		} //保存用のリストを作成＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿
		try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_ID)) {

			String sql = "INSERT INTO MUTTERS (NAME,TEXT)VALUES(?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, mutter.getuserName());
			pStmt.setString(2, mutter.getText());

			//ちゃんと挿入できたかの確認＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		

	} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	return true;
}}


