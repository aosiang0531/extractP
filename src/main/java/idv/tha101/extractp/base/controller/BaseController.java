package idv.tha101.extractp.base.controller;

import java.util.List;

public abstract class BaseController<T> {

	public abstract List<T> findAll();

	public abstract T findById(int id);

	public abstract T save(T vo);

	public abstract T update(T vo, int id);

	public abstract void deleteById(int id);
}
