package interfaces;

import java.util.List;

import bean.ReaderBean;

public interface IReaderDao {
	public List<ReaderBean> findAll();
	public ReaderBean findOne(Long id);
	public void create(ReaderBean reader);
	public void update(ReaderBean reader);
	public void delete(Long id);
}
