package com.multiplefilters;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.Predicate;

import com.sun.rowset.JdbcRowSetImpl;

public class Test implements Predicate{



	@Override
	public boolean evaluate(RowSet rs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean evaluate(Object value, int column) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean evaluate(Object value, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
