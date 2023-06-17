package interfaces;

import java.util.List;

import bean.BookBean;

public interface IBookDao {
	public List<BookBean> findByStatus(boolean status);
	public List<BookBean> findAll();
	public List<BookBean> searchByTitleOrAuthor(String key);
	public BookBean findOne(Long id);
	public void create(BookBean book);
	public void update(BookBean book);
	public void delete(Long id);
}
