package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import interfaces.IBookDao;
import util.DatabaseConnexion;

public class BookDao implements IBookDao {

	@Override
	public List<BookBean> findAll() {
		List<BookBean> books = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM BOOK");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BookBean book = new BookBean();
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				book.setPublishedAt(rs.getString("published_at"));
				book.setStatus(rs.getBoolean("status"));
				book.setIllustration(rs.getString("illustration"));
				book.setNbPret(rs.getInt("nb_pret"));
				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public void create(BookBean book) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO BOOK (TITLE,DESCRIPTION,AUTHOR,ILLUSTRATION,PUBLISHED_AT,STATUS,NB_PRET) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getDescription());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getIllustration());
			ps.setString(5, book.getPublishedAt());
			ps.setBoolean(6, book.isStatus());
			ps.setInt(7, book.getNbPret());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(BookBean book) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE BOOK SET TITLE=?,DESCRIPTION=?,AUTHOR=?,ILLUSTRATION=?,PUBLISHED_AT=?, NB_PRET = ?, STATUS = ? WHERE ID=?");
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getDescription());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getIllustration());
			ps.setString(5, book.getPublishedAt());
			ps.setInt(6, book.getNbPret());
			ps.setBoolean(7, book.isStatus());
			ps.setLong(8, book.getId());
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
			PreparedStatement ps = connection.prepareStatement("DELETE FROM BOOK WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BookBean findOne(Long id) {
		Connection connection = DatabaseConnexion.getConnection();
		BookBean book = null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM BOOK WHERE ID=? ");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				book = new BookBean();
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				book.setIllustration(rs.getString("illustration"));
				book.setPublishedAt(rs.getString("published_at"));
				book.setStatus(rs.getBoolean("status"));
				book.setNbPret(rs.getInt("nb_pret"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public List<BookBean> findByStatus(boolean status) {
		List<BookBean> books = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM BOOK WHERE STATUS = ?");
			ps.setBoolean(1, status);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BookBean book = new BookBean();
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				book.setPublishedAt(rs.getString("published_at"));
				book.setStatus(rs.getBoolean("status"));
				book.setIllustration(rs.getString("illustration"));
				book.setNbPret(rs.getInt("nb_pret"));
				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

}
