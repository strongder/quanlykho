package Dao;

import java.util.ArrayList;
import java.util.List;

public interface DAOInterface<T> {

public int insert(T t);
	
	public int update(T t);
	
	public int delete(T t);
	
	public ArrayList<T> selectAll();
	
	public T selectById(String t);
}
