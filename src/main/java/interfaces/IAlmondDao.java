package interfaces;

import java.util.List;

import bean.AlmondBean;

public interface IAlmondDao {
	public List<AlmondBean> findAll();
	public void create(AlmondBean almond);
	public AlmondBean findOne(Long id);
	public void update(AlmondBean almond);
	public void delete(Long id);
}
