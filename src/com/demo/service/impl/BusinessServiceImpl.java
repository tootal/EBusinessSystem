package com.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.demo.service.BusinessService;
import com.demo.utils.DaoFactory;
import com.demo.utils.WebUtils;
import com.demo.dao.ClothesDao;
import com.demo.dao.CategoryDao;
import com.demo.dao.OrderDao;
import com.demo.dao.UserDao;
import com.demo.dao.AdminDao;
import com.demo.dao.StatisticsDao;
import com.demo.model.Clothes;
import com.demo.model.Cart;
import com.demo.model.CartItem;
import com.demo.model.Category;
import com.demo.model.Order;
import com.demo.model.OrderItem;
import com.demo.model.Page;
import com.demo.model.User;
import com.demo.model.Admin;
import com.demo.model.Statistics;

/**
 * 控制层-对应于系统的主要功能
 * @author MouseHappy
 */
public class BusinessServiceImpl implements BusinessService {
	
	
	private CategoryDao categoryDao = DaoFactory.getInstance().createDao("com.demo.dao.impl.CategoryDaoImpl", CategoryDao.class);
	private ClothesDao clothesDao = DaoFactory.getInstance().createDao("com.demo.dao.impl.ClothesDaoImpl", ClothesDao.class);
	private UserDao userDao = DaoFactory.getInstance().createDao("com.demo.dao.impl.UserDaoImpl", UserDao.class);
	private AdminDao adminDao = DaoFactory.getInstance().createDao("com.demo.dao.impl.AdminDaoImpl", AdminDao.class);
	private OrderDao orderDao = DaoFactory.getInstance().createDao("com.demo.dao.impl.OrderDaoImpl", OrderDao.class);
	private StatisticsDao statisticsDao = DaoFactory.getInstance().createDao("com.demo.dao.impl.StatisticsDaoImpl", StatisticsDao.class);
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#addCategory(com.demo.model.Category)
	 */
	@Override
	public void addCategory(Category category){
		categoryDao.add(category);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#findCategory(java.lang.String)
	 */
	@Override
	public Category findCategory(String id){
		return categoryDao.find(id);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#getAllCategory()
	 */
	@Override
	public List<Category> getAllCategory(){
		return categoryDao.getAll();
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#addClothes(com.demo.model.Clothes)
	 */
	@Override
	public void addClothes(Clothes clothes){
		clothesDao.add(clothes);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#modifyClothes(com.demo.model.Clothes)
	 */
	@Override
	public void modifyClothes(Clothes clothes){
		clothesDao.modify(clothes);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#deleteClothes(com.demo.model.Clothes)
	 */
	@Override
	public void deleteClothes(String id){
		clothesDao.delete(id);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#findClothes(java.lang.String)
	 */
	@Override
	public Clothes findClothes(String id){
		return clothesDao.find(id);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#getClothesPageData(java.lang.String)
	 */
	@Override
	public Page getClothesPageData(String pageNum){
		int totalrecord = clothesDao.getTotalRecord();// 获取数据总数
		Page page = null;
		if(pageNum == null){// 若参数为空，显示第一页
			page = new Page(1,totalrecord);
		}else{// 若参数不为空，显示第 pageNum 页
			page = new Page(Integer.parseInt(pageNum), totalrecord);
		}
		List<Clothes> list = clothesDao.getPageData(page.getStartIndex(), page.getPageSize());// 获取分页数据
		page.setList(list);
		return page;
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#getClothesPageData(java.lang.String, java.lang.String)
	 */
	@Override
	public Page getClothesPageData(String pageNum, String categoryId){
		int totalrecord = clothesDao.getTotalRecord(categoryId);// 获取数据总数
		Page page = null;
		if(pageNum == null){// 若参数为空，显示第一页
			page = new Page(1,totalrecord);
		}else{// 若参数不为空，显示第 pageNum 页
			page = new Page(Integer.parseInt(pageNum), totalrecord);
		}
		List<Clothes> list = clothesDao.getPageData(page.getStartIndex(), page.getPageSize(), categoryId);// 获取分页数据
		page.setList(list);
		return page;
	}

	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#buyClothes(com.demo.model.Cart, com.demo.model.Clothes)
	 */
	@Override
	public void buyClothes(Cart cart, Clothes clothes){
		cart.add(clothes);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#registerUser(com.demo.model.User)
	 */
	@Override
	public void registerUser(User user){
		userDao.add(user);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String id){
		return userDao.find(id);
	}

	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public User userLogin(String userName, String password){
		return userDao.find(userName, password);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#findAdmin(java.lang.Integer)
	 */
	@Override
	public Admin findAdmin(int id){
		return adminDao.find(id);
	}

	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#adminLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public Admin adminLogin(String userName, String password){
		return adminDao.find(userName, password);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#createOrder(com.demo.model.Cart, com.demo.model.User)
	 */
	@Override
	public void createOrder(Cart cart, User user){
		if(cart == null){
			throw new RuntimeException("对不起，您还没有购买任何商品");
		}
		Order order = new Order();
		order.setId(WebUtils.makeID());
		order.setOrderTime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		for(Map.Entry<String, CartItem> me : cart.getMap().entrySet()){
			//得到一个购物项就生成一个订单项
			CartItem cItem = me.getValue();
			OrderItem oItem = new OrderItem();
			oItem.setClothes(cItem.getClothes());
			oItem.setPrice(cItem.getPrice());
			oItem.setId(WebUtils.makeID());
			oItem.setQuantity(cItem.getQuantity());
			order.getOrderItems().add(oItem);
		}	
		orderDao.add(order);
	}

	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#listOrder(java.lang.String)
	 */
	@Override
	public List<Order> listOrder(String state) {
		return orderDao.getAll(Boolean.parseBoolean(state));	
	}

	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#findOrder(java.lang.String)
	 */
	@Override
	public Order findOrder(String orderId) {		
		return orderDao.find(orderId);
	}

	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#confirmOrder(java.lang.String)
	 */
	@Override
	public void confirmOrder(String orderId) {
		Order order = orderDao.find(orderId);
		order.setState(true);
		orderDao.update(order);
	}

	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#listOrder(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Order> listOrder(String state, String userId) {
		return orderDao.getAll(Boolean.parseBoolean(state), userId);
	}

	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#clientListOrder(java.lang.String)
	 */
	@Override
	public List<Order> clientListOrder(String userId) {	
		return orderDao.getAllOrder(userId);
	}
	
	/** (non-Javadoc)
	 * @see com.demo.service.BusinessService#showStatistics(java.lang.String)
	 */
	@Override
	public List<Statistics> showStatistics() {
		return statisticsDao.getStatistics();
	}
}
