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

import com.truck.domain.Items;
import com.truck.domain.Quote;
import com.truck.domain.Users;
import com.truck.security.Encryption;
import com.truck.util.AutoGeneratedKey;

@Component
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParmJdbcTemplate;

	Encryption encpSha = new Encryption();

	// User Details

	private final String ADD_USER = "INSERT INTO truckdb.user(username,role,password,email) values(:username,:role,:password,:email)";

	private final String CHECK_USEREXITS = "select id from truckdb.user where email=:email";

	private final String RETRIVE_ITEMS_BY_USERID = "SELECT a.itemdetailsid,a.coladdress,a.deladdress,a.creates,a.itemtypeid,a.itemdetailsid,a.millage,a.map,a.photos,a.description,(select count(c.itemid) from truckdb.quote c where a.id=c.itemid) as numquote,a.id FROM truckdb.items a where  a.userid=:userid";

	private final String GET_USERDETAILS_BY_USERID = "SELECT a.shortname,a.username,b.mobile FROM truckdb.user a,truckdb.userdetails b where a.id=:userid and a.id=b.userid";

	private final String UPDATE_SHORTNAME_BY_USERID = "update truckdb.user set shortname=:shortname where id=:id";

	private final String UPDATE_USERDETAILS_BY_USERID = "update truckdb.userdetails set mobile=:mobile where userid=:userid";

	// private final String
	// RETRIVE_EXPIRE_ITEMS_BY_USERID="SELECT a.itemdetailsid,a.coladdress,a.deladdress,a.creates,a.itemtypeid,a.itemdetailsid,a.millage,a.map,a.photos,a.description,(select count(c.itemid) from truckdb.quote c where a.id=c.itemid) as numquote,a.id FROM truckdb.items a where a.creates < DATE_ADD(CURDATE(), INTERVAL -10 DAY) and a.userid=:userid";

	private final String RETRIVE_EXPIRE_ITEMS_BY_USERID = "SELECT a.itemdetailsid,a.coladdress,a.deladdress,a.creates,a.itemtypeid,a.itemdetailsid,a.millage,a.map,a.photos,a.description,(select count(c.itemid) from truckdb.quote c where a.id=c.itemid) as numquote,a.id,d.shortname,b.amount FROM truckdb.items a,truckdb.quote b,truckdb.user d where a.creates < DATE_ADD(CURDATE(), INTERVAL -10 DAY) and a.userid=:userid and b.isaccept=0 and a.id=b.itemid and b.vclid=d.id";

	// private final String
	// RETRIVE_ITEMS_BY_USERID_FOR_ACTIVE_DELIVERY="SELECT a.itemdetailsid,a.coladdress,a.deladdress,a.creates,a.itemtypeid,a.itemdetailsid,a.millage,a.map,a.photos,a.description,(select count(c.itemid) from truckdb.quote c where a.id=c.itemid) as numquote,a.id FROM truckdb.items a where  a.userid=:userid order by a.id desc";

	// private final String
	// RETRIVE_ITEMS_BY_USERID_FOR_ACTIVE_DELIVERY="SELECT a.itemdetailsid,a.coladdress,a.deladdress,a.creates,a.itemtypeid,a.itemdetailsid,a.millage,a.map,a.photos,a.description,(select count(c.itemid) from truckdb.quote c where a.id=c.itemid) as numquote,a.id FROM truckdb.items a, truckdb.quote b where b.itemid=a.id and b.isaccept=0 and a.userid=:userid  order by a.id desc";

	private final String RETRIVE_ITEMS_BY_USERID_FOR_ACTIVE_DELIVERY = "SELECT a.id, a.itemdetailsid,a.coladdress,a.deladdress,a.creates,a.itemtypeid,a.itemdetailsid,a.millage, "
			+ "a.map,a.photos,a.description, count(quote.itemid) AS numquote "
			+ "FROM truckdb.items AS a "
			+ "LEFT OUTER JOIN truckdb.quote "
			+ "ON a.id=quote.itemid  WHERE  a.isremoved <>1 AND a.userid=:userid AND (quote.isaccept=0 OR quote.isaccept IS NULL) "
			+ "GROUP BY a.id";

	// Update Quote is Accept

	private final String ACCEPT_QUOTE_BY_USER_ID = "update truckdb.quote a,truckdb.items b set a.isaccept=1 where a.id=:id and a.itemid=b.id and b.userid=:userid";

	private final String RETRIVE_ACCEPT_QUOTES_BY_USERID = "SELECT a.id,a.itemid,a.amount,a.vehicle,a.timescale,a.information,a.itemid,a.vclid,b.email,c.description,c.coladdress,c.deladdress,b.shortname,a.creates,a.isaccept  FROM truckdb.quote a,truckdb.user b,truckdb.items c where c.id=a.itemid and b.id=c.userid  and c.userid=:userid";

	// Item Remove

	private final String SET_IS_REMOVED_BY_ITEM_ID = "update truckdb.items a set a.isremoved=1 where a.id=:id";

	private final String SET_IS_UPGRADE_BY_ITEM_ID = "update truckdb.items a set a.priority=1 where a.id=:id";

	//
	private final String GET_MESSAGE_BY_ITEM_ID = "SELECT COUNT(*) AS msgCount FROM messages a,messagedetails b WHERE a.itemid=:itemid AND a.messageid=b.messageuid";

	private final String UPDATE_ITEM_DETAILS = "Update items set deladdress =:deladdress, coladdress =:coladdress WHERE id =:id";
	private final String GET_ITEM_DETAILS_BASED_ID = "Select * from items where id=:id";
	private final String UPDATE_ITEM_ADDRESS_CODE = "Update items set colpostcode =:colpostcode, delpostcode =:delpostcode WHERE id =:id";

	@Override
	public long addProductOwner(Users user) {
		// TODO Auto-generated method stub

		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("username", user.getEmail());
		paramMap.put("role", "proOwner");
		paramMap.put("password",
				encpSha.encode(user.getPassword(), user.getEmail()));
		paramMap.put("email", user.getEmail());
		// return namedParmJdbcTemplate.update(ADD_USER, paramMap);

		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		AutoGeneratedKey genKey = AutoGeneratedKey.getInstance();
		namedParmJdbcTemplate.update(ADD_USER, paramSource, genKey.getHolder(),
				genKey.getPKName("id"));
		return genKey.getValue();

	}

	@Override
	public Integer checkUserExits(String email) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("email", email);
		// return namedParmJdbcTemplate.update(CHECK_USEREXITS, paramMap);
		return namedParmJdbcTemplate.queryForObject(CHECK_USEREXITS, paramMap,
				Integer.class);
	}

	@Override
	public void updateQuoteIsAccept(int id, int userid) {
		// TODO Auto-generated method stub

		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("id", String.valueOf(id));
		paramMap.put("userid", String.valueOf(userid));

		namedParmJdbcTemplate.update(ACCEPT_QUOTE_BY_USER_ID, paramMap);

	}

	@Override
	public List<Items> retriveItemsByUserID(int id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", String.valueOf(id));

		return namedParmJdbcTemplate.query(RETRIVE_ITEMS_BY_USERID, paramMap,
				new BeanPropertyRowMapper<Items>(Items.class));

	}

	@Override
	public List<Quote> retriveAcceptQuoteByUserID(int userid) {
		// TODO Auto-generated method stub
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", String.valueOf(userid));

		return namedParmJdbcTemplate.query(RETRIVE_ACCEPT_QUOTES_BY_USERID,
				paramMap, new BeanPropertyRowMapper<Quote>(Quote.class));

	}

	@Override
	public List<Items> retriveItemsForActiveDelivery(int id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", String.valueOf(id));

		return namedParmJdbcTemplate.query(
				RETRIVE_ITEMS_BY_USERID_FOR_ACTIVE_DELIVERY, paramMap,
				new BeanPropertyRowMapper<Items>(Items.class));

	}

	@Override
	public void deleteItem(int id) {

		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("id", String.valueOf(id));

		namedParmJdbcTemplate.update(SET_IS_REMOVED_BY_ITEM_ID, paramMap);

	}

	@Override
	public void UpGradeItem(int itemid) {

		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("id", String.valueOf(itemid));

		namedParmJdbcTemplate.update(SET_IS_UPGRADE_BY_ITEM_ID, paramMap);
	}

	@Override
	public List<Items> retriveExpiredItemsByUserID(int id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", String.valueOf(id));

		return namedParmJdbcTemplate.query(RETRIVE_EXPIRE_ITEMS_BY_USERID,
				paramMap, new BeanPropertyRowMapper<Items>(Items.class));

	}

	@Override
	public List<Users> getProOwnerDetailsByID(int id) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", String.valueOf(id));

		return namedParmJdbcTemplate.query(GET_USERDETAILS_BY_USERID, paramMap,
				new BeanPropertyRowMapper<Users>(Users.class));

	}

	@Override
	public void updateProfile(String mobile, int id) {
		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("userid", String.valueOf(id));
		paramMap.put("mobile", mobile);

		namedParmJdbcTemplate.update(UPDATE_USERDETAILS_BY_USERID, paramMap);

	}

	@Override
	public void updateUsername(String name, int id) {
		Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put("id", String.valueOf(id));
		paramMap.put("shortname", name);

		namedParmJdbcTemplate.update(UPDATE_SHORTNAME_BY_USERID, paramMap);

	}

	@Override
	public Integer getMessageCountByItemId(int id) {
		// TODO Auto-generated method stub
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("itemid", String.valueOf(id));
		return namedParmJdbcTemplate.queryForObject(GET_MESSAGE_BY_ITEM_ID,
				paramMap, Integer.class);

	}

	public void updateItemDetails(String colAddress, String delAddress, int id) {
		Map paramMap = new HashMap();
		paramMap.put("deladdress", delAddress);
		paramMap.put("coladdress", colAddress);
		paramMap.put("id", id);

		namedParmJdbcTemplate.update(UPDATE_ITEM_DETAILS, paramMap);
	}

	public void updateItemAddress(int id, String colPostalCode,
			String delPostalCode) {
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		paramMap.put("colpostcode", colPostalCode);
		paramMap.put("delpostcode", delPostalCode);
		
		namedParmJdbcTemplate.update(UPDATE_ITEM_ADDRESS_CODE, paramMap);
		
	}

	public List<Items> getItemDetailsBasedId(int id) {
		Map paramMap = new HashMap();
		paramMap.put("id", id);
		
		return namedParmJdbcTemplate.query(GET_ITEM_DETAILS_BASED_ID, paramMap,
				new BeanPropertyRowMapper<Items>(Items.class));
	}
}
