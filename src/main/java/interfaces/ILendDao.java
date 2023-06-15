package interfaces;

import java.util.List;

import bean.LendBean;

public interface ILendDao {
	public List<LendBean> findAll();
	public List<LendBean> findByStatus(boolean status);
	public LendBean findOne(Long id);
	public void create(LendBean lend);
	public void delete(Long id);
	public void update(LendBean lend);
}
