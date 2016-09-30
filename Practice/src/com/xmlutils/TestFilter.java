package com.xmlutils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;





import com.multiplefilters.MultipleParamEqualsFilter;
import com.multiplefilters.QueryFilter;
import com.sun.rowset.*;


public class TestFilter {
	static FilteredRowSet filterObj=null;
	static int count = 0;
	 public static void viewFilteredRowSet(FilteredRowSet frs) throws SQLException {

		    if (frs == null) {
		      return;
		    }

		    CachedRowSet crs = (CachedRowSet)frs;

		    while (crs.next()) {
		      if (crs == null) {
		        break;
		      }
		      System.out.println(
		    		  crs.getString("OFFICER_IND")+" , "+
		    				  crs.getString("STATUS_CODE")+" ,"+
		        crs.getString("SDSV_CODE") + ", " +
		        crs.getString("SDSV_SUBCODE") + ", " +
		        crs.getString("STATUS_SUBCODE"));
		      count ++;
		    }
		    //frs.beforeFirst();
		    System.out.println("Number of rows"+count);
		    count = 0;
		  }

	 
	public static void main(String[] args) throws SQLException 
	{

	 EqualsFilter statusCodeFilter =  new EqualsFilter("A", "STATUS_CODE");
	 NotEqualFilter neFilter =  new NotEqualFilter("AFD", "STATUS_SUBCODE");
	 MultipleParamEqualsFilter sdsvCodeFilter = new MultipleParamEqualsFilter(new String[]{"ASSET_ALLOC","ROLLOUT","PORTFOLIO", "ADVICE"}, "SDSV_CODE");
	 EqualsFilter sdsvCode = new EqualsFilter("FINANCIAL_ENGINES", "SDSV_SUBCODE");
	 NotEqualFilter resFilter = new NotEqualFilter("Y", "OFFICER_IND");
	 EqualsFilter termDate = new EqualsFilter(null, "termdate");
	 EqualsFilter empTermDate = new EqualsFilter(null, "EMP_TERMDATE");
	 EqualsFilter primaryResInd = new EqualsFilter("Y", "primary_res_ind");
	MultipleParamEqualsFilter me = new MultipleParamEqualsFilter(new String[]{"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT",
			"NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY","DC","GU","VI","AA","AE","AP"}, "state_code");
	//EqualsFilter primarysInd = new EqualsFilter(new QueryFilter(), "primary_res_ind");;
	
	 
	    String url = "jdbc:oracle:thin:@DINSTDB:1521/dinstmain.isis.gwl.com";
	    String userName = "KRSBHR";
	    String passWord = "db123";
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	    	Connection conn = DriverManager.getConnection(url, userName, passWord);
	    	Statement stmt = conn.createStatement();
	    	
	    	ResultSet rs = stmt.executeQuery("select distinct "
	    			+ "B.GC_ID,A.ga_ID,B.ind_id, G.SDSV_CODE, G.sdsv_subcode, I.STATE_CODE, "
	    			+ "B.OFFICER_IND, F.SSN, G.termdate, A.status_code, b.emp_termdate, i.primary_res_ind,"
	    			+ "a.STATUS_SUBCODE,A.part_disb_hold_code"
	    			+ " from "
	    			+"part_agrmt A, EMPLOYMENT B, plan d,  individual F, Ind_Address I,"
	    			+"(Select * from INV_ACCT G "
	    			+ "where ((ID in (Select IVA_ID from INVOPT_BAL where Amount > 0)) or (ind_id in (select ind_id from invopt_alloc where Amount > 0)))) E,"
	    			+" GA_SERVICE G where 1 =1  and B.GC_ID = d.GC_ID and A.ind_id = B.ind_id and A.ind_id = E.ind_id and A.ind_id = F.id and A.ind_id = I.ind_id"
	    			+" and A.ga_ID = G.ga_Id"
	    			+" and A.ga_id like '95120%'");

	    	;
	    	
	    	
//	    	FilteredRowSet filterObj=null;
//	        for(Predicate p : filterList)
//	        {
//	        	filterObj = new FilteredRowSetImpl();
//	        	filterObj.populate(rs);
//	        	filterObj.setFilter(p);
//	        	rs = filterObj;
//	        }
	       
		  
	        TestFilter.viewFilteredRowSet(TestFilter.filterRecordSet(rs, statusCodeFilter,neFilter,sdsvCodeFilter,sdsvCode,resFilter,termDate,empTermDate,primaryResInd,me));
	        
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    }
	
	
	/**
	 * <p>
	 * Returns the filtered rowset object of the existing resultset.User needs to pass the number of filters as parameters and 
	 * the existing result set object on which the filtering is to be applied.
	 * </p>
	 * @param unfilteredRecoedSet
	 * @param predicates
	 * @return
	 * @throws SQLException
	 */
	public static FilteredRowSet filterRecordSet(ResultSet unfilteredRecoedSet,Predicate...predicates) throws SQLException
	{
		filterObj = new FilteredRowSetImpl();
		List<Predicate> filterList = Arrays.asList(predicates);
		   for(Predicate p : filterList)
	        {
	        	filterObj = new FilteredRowSetImpl();
	        	filterObj.populate(unfilteredRecoedSet);
	        	filterObj.setFilter(p);
	        	unfilteredRecoedSet = filterObj;
	        }
		return filterObj;
	}
	}


