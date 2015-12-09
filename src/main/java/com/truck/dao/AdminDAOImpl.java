package com.truck.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.truck.domain.Category;
import com.truck.domain.Messages;
import com.truck.domain.Subcategory;
import com.truck.domain.Userdetails;
import com.truck.security.Encryption;
import com.truck.util.AutoGeneratedKey;
@Component
public class AdminDAOImpl  implements AdminDAO {

	
	@Autowired
	private NamedParameterJdbcTemplate namedParmJdbcTemplate;
	
	Encryption encpSha=new Encryption();

	
	// CATEGORY
	
	private final String ADD_CATEGORY = "INSERT INTO truckdb.category(name,icon) values(:name,:icon)";	
	
	private final String RETRIVE_CATEGORY="select * from truckdb.category";
	
	private final String UPDATE_CATEGORY="update truckdb.category set name=:name,icon=:icon where id=:id";
	
	private final String DELETE_CATEGORY="delete from truckdb.category where id=:id";
	
	private final String RETRIVE_CATEGORY_BY_ID="select * from truckdb.category where id=:id";
	
	private final String ADD_SUB_CATEGORY = "INSERT INTO truckdb.subcategory(name,categoryid,icon) values(:name,:categoryid,:icon)";	
	
	private final String RETRIVE_SUB_CATEGORY="SELECT a.*,b.name as category FROM truckdb.subcategory a,truckdb.category b where b.id=a.categoryid";			

    private final String UPDATE_SUB_CATEGORY="update truckdb.subcategory set name=:name,categoryid=:categoryid,icon=:icon where id=:id";
	
	private final String DELETE_SUB_CATEGORY="delete from truckdb.subcategory where id=:id";
	
	private final String RETRIVE_SUB_CATEGORY_BY_ID="select * from truckdb.subcategory where id=:id";

	private final String RETRIVE_PRODUCT_OWNER="select a.shortname,b.mobile,a.username,a.creates,a.id from truckdb.user a,truckdb.userdetails b where  a.id=b.userid and a.role='proOwner'";

	private final String RETRIVE_TRANSPORTER="select a.shortname,b.mobile,a.username,a.creates,a.id from truckdb.user a,truckdb.userdetails b where  a.id=b.userid and a.role='vclOwner'";

	private final String RETRIVE_MESSAGES="select * from truckdb.messages group by messageid order by id desc";

	private final String UPDATE_MESSAGE_FLAG="update truckdb.messagedetails set isflag=1 where id=:id";

	@Override
	public long addCategory(Category category) {
		
	      Map<String, String> paramMap = new HashMap<String, String>();
	        
			paramMap.put("name",category.getName());
			paramMap.put("icon",category.getIcon());
			
		//	return namedParmJdbcTemplate.update(ADD_USER, paramMap);
		
			SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
			AutoGeneratedKey genKey = AutoGeneratedKey.getInstance();
			namedParmJdbcTemplate.update(ADD_CATEGORY, paramSource, genKey.getHolder(), genKey.getPKName("id"));
			return genKey.getValue();
		
	}
    
	@Override
	public void updateCategory(Category category) {
		   Map<String, String> paramMap = new HashMap<String, String>();
	        
			paramMap.put("id",String.valueOf(category.getId()));
			paramMap.put("name",category.getName());
			paramMap.put("icon", category.getIcon());
		
	        namedParmJdbcTemplate.update(UPDATE_CATEGORY, paramMap);
		
	}
	
	
	@Override
	public List<Category> retriveCategoryAd() {
		Map<String, String> paramMap = new HashMap<String, String>();
		return namedParmJdbcTemplate.query(RETRIVE_CATEGORY, paramMap,new BeanPropertyRowMapper<Category>(Category.class));
	}

	@Override
	public void deleteCategoryByID(int id) {
		// TODO Auto-generated method stub
		
		 Map<String, String> paramMap = new HashMap<String, String>();
	        
			paramMap.put("id",String.valueOf(id));			
		
	        namedParmJdbcTemplate.update(DELETE_CATEGORY, paramMap);
	       
	}

	
	
	
	
	@Override
	public List<Subcategory> retriveSubCategoryAd() {
		Map<String, String> paramMap = new HashMap<String, String>();
		//paramMap.put("categoryid", String.valueOf(id));
		return namedParmJdbcTemplate.query(RETRIVE_SUB_CATEGORY, paramMap,new BeanPropertyRowMapper<Subcategory>(Subcategory.class));
		
	}

	@Override
	public List<Category> getCategoryByID(int id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", String.valueOf(id));
		return namedParmJdbcTemplate.query(RETRIVE_CATEGORY_BY_ID, paramMap,new BeanPropertyRowMapper<Category>(Category.class));
		
	}


	@Override
	public long addSubCategory(Subcategory subcategory) {
		  Map<String, String> paramMap = new HashMap<String, String>();
	        
			paramMap.put("name",subcategory.getName());
		paramMap.put("icon",subcategory.getIcon());
			paramMap.put("categoryid",String.valueOf(subcategory.getCategoryid()));
		//	return namedParmJdbcTemplate.update(ADD_USER, paramMap);
		
			SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
			AutoGeneratedKey genKey = AutoGeneratedKey.getInstance();
			namedParmJdbcTemplate.update(ADD_SUB_CATEGORY, paramSource, genKey.getHolder(), genKey.getPKName("id"));
			return genKey.getValue();
	}

	@Override
	public void updateSubCategory(Subcategory subcategory) {
		 Map<String, String> paramMap = new HashMap<String, String>();
	        
			paramMap.put("id",String.valueOf(subcategory.getId()));
			paramMap.put("name",subcategory.getName());
			paramMap.put("icon", subcategory.getIcon());
			paramMap.put("categoryid", String.valueOf(subcategory.getCategoryid()));
			
	        namedParmJdbcTemplate.update(UPDATE_SUB_CATEGORY, paramMap);
		
	}

	@Override
	public void deleteSubCategoryByID(int id) {
		 Map<String, String> paramMap = new HashMap<String, String>();	        
			paramMap.put("id",String.valueOf(id));
		
	        namedParmJdbcTemplate.update(DELETE_SUB_CATEGORY, paramMap);
		
	}

	@Override
	public List<Subcategory> getSubCategoryByID(int id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", String.valueOf(id));
		return namedParmJdbcTemplate.query(RETRIVE_SUB_CATEGORY_BY_ID, paramMap,new BeanPropertyRowMapper<Subcategory>(Subcategory.class));
	
	}

	@Override
	public List<Userdetails> retriveTransporter() {
		Map<String, String> paramMap = new HashMap<String, String>();
		return namedParmJdbcTemplate.query(RETRIVE_TRANSPORTER, paramMap,new BeanPropertyRowMapper<Userdetails>(Userdetails.class));

	}

	@Override
	public List<Userdetails> retriveProductOwner() {
		Map<String, String> paramMap = new HashMap<String, String>();
		return namedParmJdbcTemplate.query(RETRIVE_PRODUCT_OWNER, paramMap,new BeanPropertyRowMapper<Userdetails>(Userdetails.class));

	}

	@Override
	public List<Messages> retriveMessagesGroupByMessageID() {
		Map<String, String> paramMap = new HashMap<String, String>();
		return namedParmJdbcTemplate.query(RETRIVE_MESSAGES, paramMap,new BeanPropertyRowMapper<Messages>(Messages.class));
	}

	@Override
	public void updateflag(int id) {
		 Map<String, String> paramMap = new HashMap<String, String>();
	        
			paramMap.put("id",String.valueOf(id));			
		
	        namedParmJdbcTemplate.update(UPDATE_MESSAGE_FLAG, paramMap);
	        
		
	}

	
	


}
