package com.lhs.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.lhs.dao.UserDao;
import com.lhs.entity.User;
import com.lhs.exception.DaoException;
import com.lhs.jdbc.JdbcUtil;

public class UserDaoImpl implements UserDao {
	
	private JdbcUtil jdbcUtil=null;
	public void setJdbcUtil(JdbcUtil j)
	{
		jdbcUtil=j;
	}
	
	@Override
	public  Integer addUser(User user) {		
		Integer flag=0;
		if(user!=null)
		{			
			String sql="insert into t_user (username,password,sex,age,birth,hobby,motto,"
					+ "jsm) values(?,?,?,?,?,?,?,?)";

			Object[] objs=new Object[]{user.getUsername(),user.getPassword(),user.getSex(),user.getAge(),user.getBirth()
					,user.getHobby(),user.getMotto(),user.getJsm()};
			try{
				jdbcUtil.CreateConn();
				flag=jdbcUtil.executeUpdate(sql, objs);				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				jdbcUtil.DisposeRs();
			}
		}
		return flag;
	}

	@Override
	public Integer deleteUser(int id) {
		String sql="delete from t_user where id=?";
		Object[] p={id};
		
		Integer flag=-1;
		try{
			jdbcUtil.CreateConn();
			flag=jdbcUtil.executeUpdate(sql, p);
		}catch(DaoException e)
		{
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		return flag;
	}

	@Override
	public Integer deleteUser(User user) {
		return this.deleteUser(user.getId());
	}

	@Override
	public Integer updateUser(User user) {
		
		String sql="update t_user set ";
		ArrayList< Object> list=new ArrayList<Object>();

		String username=user.getUsername();
		if(username!=null && !username.equals(""))
		{
			sql+=" username=?";
			list.add(username);
		}
		
		String password=user.getPassword();
		if(password!=null && !password.equals(""))
		{
			sql+=",password=?";
			list.add(password);
		}
		
		String sex=user.getSex();
		if(sex!=null && !sex.equals(""))
		{
			sql+=",sex=? ";
			list.add(sex);
		}
		
		Integer age=user.getAge();
		if(age!=null)
		{
			sql+=",age=? ";
			list.add(age);
		}
		
		Date birth=user.getBirth();
		if(birth!=null )
		{
			sql+=",birth=?";
			list.add(birth);
		}
		
		String hobby=user.getHobby();
		if(hobby!=null && !hobby.equals(""))
		{
			sql+=",hobby=?";
			list.add(hobby);
		}
		
		String motto=user.getMotto();
		if(motto!=null && !motto.equals(""))
		{
			sql+=",motto=?";
			list.add(motto);
		}

		sql+=" where id=?";
		Integer id=user.getId();
		list.add(id);
		
		Integer flag=null;
		try{
			jdbcUtil.CreateConn();
			flag=jdbcUtil.executeUpdate(sql, list.toArray());
		}catch(DaoException e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		return flag;
	}

	@Override
	public User findUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> findUsers(User searchModel, int pageSize, int pageNum) {
		ArrayList<User> ulist=null;
		
		String sql="select * from t_user where 1=1 ";
		ArrayList< Object> list=new ArrayList<Object>();
		
		String username=searchModel.getUsername();
		if(username!=null && !username.equals(""))
		{
			sql+=" and username like ? ";
			list.add("%"+username+"%");
		}
		
		String sex=searchModel.getSex();
		if(sex!=null && !sex.equals(""))
		{
			sql+=" and sex like ?";
			list.add("%"+sex+"%");
		}
		
		sql+=" order by username,sex ";
		
		sql+=" limit ?,?";
		Integer startindex=(pageNum-1)*pageSize;
		list.add(startindex);
		list.add(pageSize);
		
		try{
			jdbcUtil.CreateConn();
			ArrayList<Object[]> users= jdbcUtil.executeQuery(sql, list.toArray());
			
			ulist=new ArrayList<User>();
			for(Object[] uobj:users)
			{
				Integer id=(Integer)uobj[0];
				String uid=(String)uobj[1];
				String pwd=(String)uobj[2];
				String Sex=((String)uobj[3]).equals("0")?"男":"女";
				Integer age=(Integer)uobj[4];
				Date	date=(Date)uobj[5];
				String hobby=(String)uobj[6];
				String motto=(String)uobj[7];
				String jsm=(String)uobj[8];
				
				ulist.add(new User(id,uid,pwd,Sex,age,date,hobby,motto,jsm));				
			}
			
		}catch(DaoException e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		return ulist;
	}

	@Override
	public ArrayList<User> findAllUsers() {
		ArrayList<User> ulist=null;
		
		String sql="select * from t_user";
		
		try{
			jdbcUtil.CreateConn();
			ArrayList<Object[]> users= jdbcUtil.executeQuery(sql, null);
			
			ulist=new ArrayList<User>();
			for(Object[] uobj:users)
			{
				Integer id=(Integer)uobj[0];
				String uid=(String)uobj[1];
				String pwd=(String)uobj[2];
				String sex=((String)uobj[3]).equals("0")?"男":"女";
				Integer age=(Integer)uobj[4];
				Date	date=(Date)uobj[5];
				String hobby=(String)uobj[6];
				String motto=(String)uobj[7];
				String jsm=(String)uobj[8];
				
				ulist.add(new User(id,uid,pwd,sex,age,date,hobby,motto,jsm));				
			}
		}catch(DaoException e)
		{
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		return ulist;
	}

	@Override
	public User findUser(String username) {
		
		String sql="select * from t_user where username=?";
		Object[] obj={username};		
		
		ArrayList<Object[]> olist=null;
		
		User user=null;
		try{
			jdbcUtil.CreateConn();
			olist=jdbcUtil.executeQuery(sql, obj);
			
			if(olist.size()>0)
			{
				Integer id=(Integer)olist.get(0)[0];
				String uid=(String)olist.get(0)[1];
				String pwd=(String)olist.get(0)[2];
				String sex=(String)olist.get(0)[3];
				Integer age=(Integer)olist.get(0)[4];
				Date	date=(Date)olist.get(0)[5];
				String hobby=(String)olist.get(0)[6];
				String motto=(String)olist.get(0)[7];
				String jsm=(String)olist.get(0)[8];
				
				user=new User(id,uid,pwd,sex,age,date,hobby,motto,jsm);
			}
						
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		return user;
	}

	@Override
	public Integer getUserNum() {
		
		String sql="select count(*) from t_user";
		Integer num=null;
		try{
			jdbcUtil.CreateConn();
			num=jdbcUtil.executeSingleQuery(sql, null);
		}catch(DaoException e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		return num;
	}

	@Override
	public Integer getUserNumbyFilter(User searchModel) {
		Integer count=null;
		
		String sql="select count(*) from t_user where 1=1 ";
		ArrayList< Object> list=new ArrayList<Object>();
		
		String username=searchModel.getUsername();
		if(username!=null && !username.equals(""))
		{
			sql+=" and username like ? ";
			list.add("%"+username+"%");
		}
		
		String sex=searchModel.getSex();
		if(sex!=null && !sex.equals(""))
		{
			sql+=" and sex like ?";
			list.add("%"+sex+"%");
		}
		
		try{
			jdbcUtil.CreateConn();
			count=jdbcUtil.executeSingleQuery(sql, list.toArray());
			
		}catch(DaoException e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		return count;
	}

}
