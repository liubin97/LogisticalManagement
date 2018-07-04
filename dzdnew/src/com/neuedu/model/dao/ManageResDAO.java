package com.neuedu.model.dao;

import com.neuedu.model.po.*;

public interface ManageResDAO {

	void addRes(ReserveInfo res);
	void updateRes(ReserveInfo res);
	boolean haveRes(ReserveInfo res);
	String acProname(int pro_id);
	String acWhname(int wh_id);
}
