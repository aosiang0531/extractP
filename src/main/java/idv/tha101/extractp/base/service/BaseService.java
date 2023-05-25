package idv.tha101.extractp.base.service;

import java.util.List;

public interface BaseService<T> {
	List<T> findAll();
	T findById(Integer id);
	T save(T vo);
	void deleteById(Integer id);
	
}
