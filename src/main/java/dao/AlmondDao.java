package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AlmondBean;
import bean.ReaderBean;
import interfaces.IAlmondDao;
import interfaces.IReaderDao;
import util.DatabaseConnexion;

public class AlmondDao implements IAlmondDao{
	
	private IReaderDao readerDao;
	

	public AlmondDao() {
		super();
		readerDao = new ReaderDao();
	}

	@Override
	public List<AlmondBean> findAll() {
		List<AlmondBean> almonds = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ALMOND");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				AlmondBean almond = new AlmondBean();
				ReaderBean reader = readerDao.findOne(rs.getLong("reader"));
				almond.setId(rs.getLong("id"));
				almond.setReader(reader);
				almond.setAmount(rs.getDouble("amount"));
				almond.setPayed(rs.getBoolean("payed"));
				almonds.add(almond);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return almonds;
	}

	@Override
	public void create(AlmondBean almond) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO ALMOND (READER,AMOUNT,PAYED) VALUES (?,?,?) ");
			ps.setLong(1, almond.getReader().getId());
			ps.setDouble(2, almond.getAmount());
			ps.setBoolean(3, almond.isPayed());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AlmondBean findOne(Long id) {
		AlmondBean almond = null;
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ALMOND WHERE ID = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				almond = new AlmondBean();
				ReaderBean reader = readerDao.findOne(rs.getLong("reader"));
				almond.setAmount(rs.getDouble("amount"));
				almond.setPayed(rs.getBoolean("payed"));
				almond.setId(rs.getLong("id"));
				almond.setReader(reader);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return almond;
	}

	@Override
	public void update(AlmondBean almond) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE ALMOND SET READER= ?,AMOUNT = ?,PAYED = ? WHERE ID=? ");
			ps.setLong(1, almond.getReader().getId());
			ps.setDouble(2, almond.getAmount());
			ps.setBoolean(3, almond.isPayed());
			ps.setLong(4, almond.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Long id) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM ALMOND WHERE ID=? ");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
