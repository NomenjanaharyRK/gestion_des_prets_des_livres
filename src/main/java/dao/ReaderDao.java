package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ReaderBean;
import interfaces.IReaderDao;
import util.DatabaseConnexion;

public class ReaderDao implements IReaderDao{

	@Override
	public List<ReaderBean> findAll() {
		List<ReaderBean> readers = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM READER");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReaderBean reader = new ReaderBean();
				reader.setId(rs.getLong("id"));
				reader.setName(rs.getString("name"));
				reader.setLastname(rs.getString("lastname"));
				reader.setAddress(rs.getString("address"));
				reader.setEmail(rs.getString("email"));
				reader.setPhone(rs.getString("phone"));
				reader.setCin(rs.getString("cin"));
				reader.setIllustration(rs.getString("illustration"));
				reader.setNbPretActuel(rs.getInt("nb_pret"));
				readers.add(reader);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return readers;
	}

	@Override
	public ReaderBean findOne(Long id) {
		Connection connection = DatabaseConnexion.getConnection();
		ReaderBean reader = null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM READER WHERE ID = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				reader = new ReaderBean();
				reader.setId(rs.getLong("id"));
				reader.setName(rs.getString("name"));
				reader.setLastname(rs.getString("lastname"));
				reader.setAddress(rs.getString("address"));
				reader.setEmail(rs.getString("email"));
				reader.setPhone(rs.getString("phone"));
				reader.setCin(rs.getString("cin"));
				reader.setIllustration(rs.getString("illustration"));
				reader.setNbPretActuel(rs.getInt("nb_pret"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reader;
	}

	@Override
	public void create(ReaderBean reader) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("INSERT INTO READER (NAME,LASTNAME,ADDRESS,EMAIL,PHONE,CIN,ILLUSTRATION,NB_PRET) VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, reader.getName());
			ps.setString(2, reader.getLastname());
			ps.setString(3, reader.getAddress());
			ps.setString(4, reader.getEmail());
			ps.setString(5, reader.getPhone());
			ps.setString(6, reader.getCin());
			ps.setString(7, reader.getIllustration());
			ps.setInt(8, reader.getNbPretActuel());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ReaderBean reader) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement
					("UPDATE READER SET NAME=?,LASTNAME=?,ADDRESS=?,EMAIL=?,PHONE=?,CIN=?,ILLUSTRATION=? WHERE ID=?");
			ps.setString(1, reader.getName());
			ps.setString(2, reader.getLastname());
			ps.setString(3, reader.getAddress());
			ps.setString(4, reader.getEmail());
			ps.setString(5, reader.getPhone());
			ps.setString(6, reader.getCin());
			ps.setString(7, reader.getIllustration());
			ps.setLong(8, reader.getId());
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
			PreparedStatement ps = connection.prepareStatement("DELETE FROM READER WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}
