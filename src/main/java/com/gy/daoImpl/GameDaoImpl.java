package com.gy.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gy.dao.GameDao;
import com.gy.model.Game;

/**
 * @author Chencongye
 * @version 0.0.1
 * @introduce 这是游戏数据库操作实现层
 * @date 2017.9.12
 */

@Repository
public class GameDaoImpl implements GameDao {

	/**
	 * 创建Hibernate的会话工厂类
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 创建事务
	 */
	private Transaction tx;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 创建获得Session对象
	 * @return
	 */
	private Session getSession(){
		return this.getSessionFactory().openSession();
	}
	
	/**
	 * 创建获得查询游戏功能
	 * @return Game
	 */
	@Override
	public Game query(int gameid) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Game game = null;
		try {
			game = (Game)session.get(Game.class, gameid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(session!=null){
				session.close();
			}
			/*if(sessionFactory!=null){
				sessionFactory.close();
			}*/
		}
		return game;
	}

	/**
	 * 创建获得查询所有游戏功能
	 * @return Game
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Game> queryAll() {
		// TODO Auto-generated method stub
		List<Game> games = new ArrayList<Game>();
		Session session = getSession();
		try {
			tx = session.beginTransaction();
			games = session.createQuery("from Game").list();
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(session!=null)
			{
				session.close();
			}
		}
		
		return games;
	}

	/**
	 * 根据条件来查询游戏
	 * @return Game
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Game querysql(String sql) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Game game = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			List<Game> list = new ArrayList<Game>();
			list = query.list();
			if(list!=null && list.size()>0){
				game = (Game)list.get(0);
			}
			else
			{
				System.err.println("数组越界,用户输入的内容有空值！！！");
			}
			tx.commit();
			return game;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			/*if(session!=null)
			{
				session.close();
			}*/
		}
		return game;
	}

	/**
	 * 根据条件来查询游戏
	 * @return Game
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Game queryBysql(String sql) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Game game = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			List<Game> list = new ArrayList<Game>();
			list = query.list();
			if(list!=null && list.size()>0){
				game = (Game)list.get(0);
			}
			else
			{
				System.err.println("数组越界,用户输入的内容有空值！！！");
			}
			tx.commit();
			return game;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(session!=null)
			{
				session.close();
			}
		}
		return game;
	}

	/**
	 * 创建添加游戏功能
	 * @return Game
	 */
	@Override
	public boolean save(Game game) {
		// TODO Auto-generated method stub
		Session session = getSession();
		boolean flag = false;
		try {
			tx = session.beginTransaction();
			int i = (Integer) session.save(game);
			if(i>0)
			{
				flag = true;
			}
			else{
				flag = false;
			}
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 实现更新或者添加游戏
	 * @return Game
	 */
	@Override
	public boolean saveorupdate(Game game) {
		// TODO Auto-generated method stub
		Session session = getSession();
		boolean flag = false;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(game);
			flag = true;
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 创建批量添加游戏功能
	 */
	@Override
	public void saveAll(Game[] games) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(games);
	}

	/**
	 * 创建删除游戏功能
	 * @return Game
	 */
	@Override
	public boolean delete(int gameid) {
		// TODO Auto-generated method stub
		Game game = query(gameid);
		Session session = getSession();
		boolean flag = false;
		try {
			tx = session.beginTransaction();
			session.delete(game);
			flag = true;
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(tx!=null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(session!=null) {
				session.close(); 
			}
		}
		return flag;
	}

	/**
	 * 创建删除所有游戏功能
	 * @return Game
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean deleteAll(Game[] games) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			getSession().delete(games);
			flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 创建更新游戏功能
	 * @return Game
	 */
	@Override
	public boolean update(Game game) {
		// TODO Auto-generated method stub
		Session session = getSession();
		boolean flag = false;
		try {
			tx = session.beginTransaction();
			session.update(game);
			flag = true;
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 创建批量更新游戏功能
	 * @return Game
	 */
	@Override
	public boolean updateAll(Game[] games) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 创建批量插入游戏功能
	 * @return Game
	 */
	@Override
	public int insert(String sql) {
		// TODO Auto-generated method stub
		Session session = getSession();
		int flag = 0;
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(sql);
			flag = query.executeUpdate();
			
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return flag;
	}

}
