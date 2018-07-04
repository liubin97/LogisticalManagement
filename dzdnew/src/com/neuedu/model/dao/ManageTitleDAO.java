package com.neuedu.model.dao;

import java.util.List;

import com.neuedu.model.po.OneTitle;
import com.neuedu.model.po.TwoTitle;

public interface ManageTitleDAO {

	void addOneTitle(OneTitle oneTitle);

	void addTwoTitle(TwoTitle twoTitle);

	List<OneTitle> selectOneList(String onetitle_name);

	List<TwoTitle> selectTwoList(String twotitle_name);

	List<OneTitle> selectOneList(String onetitle_name, int pageNum, int pageSize);

	List<TwoTitle> selectTwoList(String twotitle_name, int pageNum, int pageSize);

	int selectOnePage(String onetitle_name, int pageSize);

	int selectTwoPage(String twotitle_name, int pageSize);

	void updateTwoTitle(TwoTitle twoTitle);

	void updateOneTitle(OneTitle oneTitle);

	String selectOneNameById(int onetitle_id);

	void deleteTwoTitle(int twotitle_id);

	void deleteOneTitle(int onetitle_id);

	String oneTitleIsDelete(int onetitle_id);

	List<TwoTitle> selectTwoList(int onetitle_id);
	
	String selectTwoNameById(int twotitle_id);
	
	String selectOneNameByTwoId(int twotitle_id);

	List<OneTitle> selectOneTitleById(int onetitle_id);

	List<TwoTitle> selectTwoTitleById(int twotitle_id);

	String twoTitleIsDelete(int twotitle_id);

	int selectOneIdByTwoId(int twotitle_id);
}
