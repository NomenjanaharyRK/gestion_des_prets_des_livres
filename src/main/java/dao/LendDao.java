package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import bean.LendBean;
import bean.ReaderBean;
import interfaces.IBookDao;
import interfaces.ILendDao;
import interfaces.IReaderDao;
import util.DatabaseConnexion;

public class LendDao implements ILendDao {
	
	private IBookDao bookDao;
	private IReaderDao readerDao;

	public LendDao() {
		super();
		bookDao = new BookDao();
		readerDao = new ReaderDao();
	}

	@Override
	public List<LendBean> findAll() {
		List<LendBean> lends = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LEND");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LendBean lend = new LendBean();
				Long bookId = rs.getLong("book");
				Long readerId = rs.getLong("reader");
				
				BookBean book = bookDao.findOne(bookId);
				ReaderBean reader = readerDao.findOne(readerId);
				
				lend.setId(rs.getLong("id"));
				lend.setStartDate(rs.getDate("start_date"));
				lend.setEndDate(rs.getDate("end_date"));
				lend.setBook(book);
				lend.setReader(reader);
				lend.setFinished(rs.getBoolean("finished"));
				
				lends.add(lend);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lends;
	}

	@Override
	public List<LendBean> findByStatus(boolean status) {
		List<LendBean> lends = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LEND WHERE FINISHED=?");
			ps.setBoolean(1, status);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LendBean lend = new LendBean();
				Long bookId = rs.getLong("book");
				Long readerId = rs.getLong("reader");
				
				BookBean book = bookDao.findOne(bookId);
				ReaderBean reader = readerDao.findOne(readerId);
				
				lend.setId(rs.getLong("id"));
				lend.setStartDate(rs.getDate("start_date"));
				lend.setEndDate(rs.getDate("end_date"));
				lend.setBook(book);
				lend.setReader(reader);
				lend.setFinished(rs.getBoolean("finished"));
				
				lends.add(lend);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lends;
	}

	@Override
	public LendBean findOne(Long id) {
		LendBean lend = null;
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LEND WHERE ID=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				lend = new LendBean();
				Long bookId = rs.getLong("book");
				Long readerId = rs.getLong("reader");
				
				BookBean book = bookDao.findOne(bookId);
				ReaderBean reader = readerDao.findOne(readerId);
				
				lend.setId(rs.getLong("id"));
				lend.setStartDate(rs.getDate("start_date"));
				lend.setEndDate(rs.getDate("end_date"));
				lend.setBook(book);
				lend.setReader(reader);
				lend.setFinished(rs.getBoolean("finished"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lend;
	}

	@Override
	public void create(LendBean lend) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO LEND (BOOK,READER,START_DATE,END_DATE,FINISHED) VALUES (?,?,?,?,?) ");
			ps.setLong(1, lend.getBook().getId());
			ps.setLong(2, lend.getReader().getId());
			ps.setDate(3, lend.getStartDate());
			ps.setDate(4, lend.getEndDate());
			ps.setBoolean(5, lend.isFinished());
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
			PreparedStatement ps = connection.prepareStatement("DELETE FROM LEND WHERE ID = ? ");
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(LendBean lend) {
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE LEND SET BOOK = ?, READER = ?, START_DATE = ?, END_DATE = ?, FINISHED = ? WHERE ID = ?");
			ps.setLong(1, lend.getBook().getId());
			ps.setLong(2, lend.getReader().getId());
			ps.setDate(3, lend.getStartDate());
			ps.setDate(4, lend.getEndDate());
			ps.setBoolean(5, lend.isFinished());
			ps.setLong(6, lend.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LendBean> findAllLate() {
		List<LendBean> lends = new ArrayList<>();
		java.util.Date utilDate = new java.util.Date();
		Date todayDate = new Date(utilDate.getTime());
		Connection connection = DatabaseConnexion.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LEND WHERE FINISHED = ? AND END_DATE < ? ");
			ps.setBoolean(1, false);
			ps.setDate(2, todayDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LendBean lend = new LendBean();
				Long bookId = rs.getLong("book");
				Long readerId = rs.getLong("reader");
				
				BookBean book = bookDao.findOne(bookId);
				ReaderBean reader = readerDao.findOne(readerId);
				
				lend.setId(rs.getLong("id"));
				lend.setStartDate(rs.getDate("start_date"));
				lend.setEndDate(rs.getDate("end_date"));
				lend.setBook(book);
				lend.setReader(reader);
				lend.setFinished(rs.getBoolean("finished"));
				
				lends.add(lend);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lends;
	}

	@Override
	public List<LendBean> findAllByReader(Long readerId) {
		List<LendBean> lends = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		ReaderBean reader = readerDao.findOne(readerId);
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LEND WHERE READER = ?");
			ps.setLong(1, readerId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LendBean lend = new LendBean();
				Long bookId = rs.getLong("book");
				BookBean book = bookDao.findOne(bookId);				
				lend.setId(rs.getLong("id"));
				lend.setStartDate(rs.getDate("start_date"));
				lend.setEndDate(rs.getDate("end_date"));
				lend.setBook(book);
				lend.setReader(reader);
				lend.setFinished(rs.getBoolean("finished"));				
				lends.add(lend);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lends;
	}

	@Override
	public List<LendBean> findAllBetweenTwoDate(Long readerId, String start, String end) {
		List<LendBean> lends = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		ReaderBean reader = readerDao.findOne(readerId);
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LEND WHERE READER = ? AND START_DATE BETWEEN ? AND ?");
			ps.setLong(1, readerId);
			ps.setString(2, start);
			ps.setString(3, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LendBean lend = new LendBean();
				Long bookId = rs.getLong("book");
				BookBean book = bookDao.findOne(bookId);				
				lend.setId(rs.getLong("id"));
				lend.setStartDate(rs.getDate("start_date"));
				lend.setEndDate(rs.getDate("end_date"));
				lend.setBook(book);
				lend.setReader(reader);
				lend.setFinished(rs.getBoolean("finished"));				
				lends.add(lend);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lends;
	}

	@Override
	public List<LendBean> findAllInAMonth(String month, Long readerId) {
		List<LendBean> lends = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		ReaderBean reader = readerDao.findOne(readerId);
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LEND WHERE READER = ? AND MONTH(START_DATE) = MONTH(?) AND YEAR(START_DATE) = YEAR(?)");
			ps.setLong(1, readerId);
			ps.setString(2, month);
			ps.setString(3, month);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LendBean lend = new LendBean();
				Long bookId = rs.getLong("book");
				BookBean book = bookDao.findOne(bookId);				
				lend.setId(rs.getLong("id"));
				lend.setStartDate(rs.getDate("start_date"));
				lend.setEndDate(rs.getDate("end_date"));
				lend.setBook(book);
				lend.setReader(reader);
				lend.setFinished(rs.getBoolean("finished"));				
				lends.add(lend);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lends;
	}

	@Override
	public List<LendBean> findAllInAYear(String year, Long readerId) {
		List<LendBean> lends = new ArrayList<>();
		Connection connection = DatabaseConnexion.getConnection();
		ReaderBean reader = readerDao.findOne(readerId);
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LEND WHERE READER = ? AND YEAR(START_DATE) = YEAR(?)");
			ps.setLong(1, readerId);
			ps.setString(2, year);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LendBean lend = new LendBean();
				Long bookId = rs.getLong("book");
				BookBean book = bookDao.findOne(bookId);				
				lend.setId(rs.getLong("id"));
				lend.setStartDate(rs.getDate("start_date"));
				lend.setEndDate(rs.getDate("end_date"));
				lend.setBook(book);
				lend.setReader(reader);
				lend.setFinished(rs.getBoolean("finished"));				
				lends.add(lend);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lends;
	}
}
