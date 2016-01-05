package com.lhs.test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.dao.UserDao;
import com.lhs.entity.User;
import com.lhs.service.UserService;
import com.lhs.util.MD5Converter;

public class UserDaoImplTest {

	@Test
	public void testSetJdbcUtil() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		Date date=null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse("1994-10-23");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user=new User(null, "刘彩", "120312", "1", 34,date, "打篮球", "天生我材必有用", "user");
		
		UserDao userdao=(UserDao)Injection.getInstance().GetClassObject(UserDao.class);
		if(userdao.addUser(user)>0)
		{
			System.out.println("更新成功！");
		}
	}

	@Test
	public void testDeleteUserInt() {
		//产生测试语句
/*		StringBuilder sb=new StringBuilder();
		for(int i=0;i<100;i++)
		{
			sb.append("insert into user (username,password,sex,age,birth,hobby,motto,jsm) values(");
			sb.append("'user"+i+"',");
			sb.append("'"+MD5Converter.getString("123456")+"',");
			if(i%3==0)
				sb.append("'0',");
			else 
				sb.append("'1',");
			sb.append(new Random().nextInt(100)+",");
			sb.append("'1990-09-12',");
			sb.append("'其他','hello world!','user'");
			sb.append(");\n");
		}
		System.out.println(sb.toString());
*/	
		
		UserDao userdao=(UserDao)Injection.getInstance().GetClassObject(UserDao.class);
		if(userdao.deleteUser(16)>0)
			System.out.println("成功");
		else 
			System.out.println("失败！");
	}

	@Test
	public void testDeleteUserUser() throws InstantiationException {
	}

	@Test
	public void testUpdateUser() {
		UserDao userdao=(UserDao)Injection.getInstance().GetClassObject(UserDao.class);

		//Integer id, String username, String password, String sex, Integer age,	Date birth, String hobby, String motto, String jsm
		User user=new User(18,"ldfew","","0",123,new Date(),"","","");
		if(userdao.updateUser(user)>0)
		{
			System.out.println("success");
		}
	}

	@Test
	public void testFindUserInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUsers() {
		UserDao userdao=(UserDao)Injection.getInstance().GetClassObject(UserDao.class);
		
		User search=new User();
		search.setUsername("use");
		search.setSex("0");
/*		ArrayList<User> users=userdao.findUsers(search, 10, 1);
		
		for(User u:users)
		{
			System.out.println(u.toString());
		}
*/
		UserService userService=(UserService)Injection.getInstance().GetClassObject(UserService.class);
		
		Map map=userService.findUsers(new User(), 10, 1);
		System.out.println(map.get("total"));
	}

	@Test
	public void testFindAllUsers() {
		UserDao userdao=(UserDao)Injection.getInstance().GetClassObject(UserDao.class);
		
		ArrayList<User> users=userdao.findAllUsers();
		
/*		for(User u:users){
			System.out.println(u.toString());
		}
*/		
		HashMap map=new HashMap();
		map.put("name", "liuhonssen");
		map.put("age", 12);
		map.put("hobby", "dfwe");
		
		JSONObject json=new JSONObject();
		JSONArray arr=null;
		try {
			json.put("name", "liu");
			arr=new JSONArray();
			arr.put("12");
			arr.put("23");
			json.put("age",arr);
			json.append("age", "15");
			json.append("age", "19");
			json.append("tian","fwefew");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray a=new JSONArray();
		for(User u:users)
		{
			a.put(new JSONObject(u));
		}
		System.out.println(a.toString());
	}

	@Test
	public void testFindUserString() {
		UserDao userdao=(UserDao)Injection.getInstance().GetClassObject(UserDao.class);
		
		//User u=userdao.findUser("王五");
		
		System.out.println(userdao.getUserNum());
		
		//System.out.println(u.toString());
	}

}
