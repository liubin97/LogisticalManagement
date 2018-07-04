package com.neuedu.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.po.Manager;

public interface LoginDAO {

	Manager validateManager(String admin, String password);
	

}
