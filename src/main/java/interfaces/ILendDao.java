package interfaces;

import java.util.List;

import bean.LendBean;

public interface ILendDao {
	public List<LendBean> findAll();
	public List<LendBean> findByStatus(boolean status);
	public List<LendBean> findAllLate();
	public List<LendBean> findAllByReader(Long readerId);
	public List<LendBean> findAllInAMonth(String month,Long readerId);
	public List<LendBean> findAllInAYear(String year,Long readerId);
	public List<LendBean> findAllBetweenTwoDate(Long readerId,String start, String end);
	public LendBean findOne(Long id);
	public void create(LendBean lend);
	public void delete(Long id);
	public void update(LendBean lend);
}
