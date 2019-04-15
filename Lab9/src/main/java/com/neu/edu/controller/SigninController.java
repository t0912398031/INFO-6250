package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.neu.edu.dao.BitcoinDao;
import com.neu.edu.dao.ClientDao;
import com.neu.edu.dao.OrderDao;
import com.neu.edu.dao.RecordDao;

import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.BitcoinException;
import com.neu.edu.exception.CategoryException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.OrderException;
import com.neu.edu.exception.RecordException;

import com.neu.edu.pojo.Bitcoin;

import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.Record;
import com.neu.edu.pojo.User;
import com.neu.service.Service;

@Controller
@RequestMapping("/signin/*")
public class SigninController {

	private static final Log LOGGER = LogFactory.getLog(SigninController.class);
	
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	BitcoinDao bitcoinDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	RecordDao recordDao;

	Comparator<Order> orderDateComparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
			return o1.getDate().compareTo(o2.getDate());       	
        }
    };
	
//	@RequestMapping("/")
//	public String viewHome() {
//		return "home";
//	}
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) throws CategoryException, ClientException {
		
    	return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView signin(HttpServletRequest request) throws CategoryException, ClientException {
		
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
        String password = request.getParameter("password");

		Client loggedUser = clientDao.authenticateLogin(userName, password);

        if (loggedUser == null) {
            request.setAttribute("error", "No user found, please check your username and password");
            return new ModelAndView("home");
        } else if(loggedUser.getUserName().equals("admin")&&loggedUser.getPassword().equals("123")){

        	session.setAttribute("USER", loggedUser);
        	session.setAttribute("admin", "admin");
            session.setAttribute("error", "");
            
            List<Client> clients = clientDao.list();

            ModelAndView mv = new ModelAndView();
    		mv.addObject("clients", clients);
    		mv.setViewName("admin");
            
            return mv;
        } else {
            session.setAttribute("USER", loggedUser);
            session.setAttribute("error", "");
            return new ModelAndView("signin");
        }
	}
	
	@RequestMapping("/back")
	public String back() {
		return "signin";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return ("redirect:/");
	}
	
	
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView register() throws CategoryException {
//		ModelAndView mv = new ModelAndView();
////		mv.addObject("categories", categoryDao.list());
////		mv.addObject("advert", new Advert());
//		mv.setViewName("register");
//		return mv;
//	}
	
//	public ModelAndView viewOrderList(Client loggeduser) throws OrderException{
//		List<Order> o = orderDao.listByUser((loggeduser.getUserId()));
//		
//        Collections.sort(o, orderDateComparator);
//	
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("orders", o);
//		mv.setViewName("orderlist");
//		return mv;		
//	}
//
//	@RequestMapping(value = "/order/create", method = RequestMethod.POST)
//	public ModelAndView createOrder(@ModelAttribute("order") Order order, HttpServletRequest request)
//			throws OrderException, ClientException {
//		
//		HttpSession session = request.getSession();
//		Client loggeduser = (Client) session.getAttribute("USER");
//		
//		if (order.getAmount() == 0) {
//            request.setAttribute("error", "* amount must > 0");
//            return new ModelAndView("signin");
//        }	
//		
//		if (order.getType().equalsIgnoreCase("sell") && order.getAmount() > loggeduser.getBitcoins().size()) {
//            request.setAttribute("error", "* amount must < " + loggeduser.getBitcoins().size());
//            return new ModelAndView("signin");
//        }
//		
//		if (order.getType().equalsIgnoreCase("buy") && order.getPrice()*order.getAmount() > loggeduser.getBalance()) {
//            request.setAttribute("error", "* requested order price exceeds your balance");
//            return new ModelAndView("signin");
//        }
//		
//		
//
//		LOGGER.debug(order);
//		order.setStatus("Pending");
//		order.setUserId(loggeduser.getUserId());
//		
//		Client u = clientDao.get(loggeduser.getUserId());
//		u.getOrders().add(order);
//		clientDao.update(u);
//		
//
////		List<Order> o = new ArrayList<Order>(u.getOrders());
////		
////        Collections.sort(o, orderDateComparator);
////		
////		ModelAndView mv = new ModelAndView();
////		mv.addObject("orders", o);
////
////		mv.setViewName("orderlist");
////		return mv;
//		return viewOrderList(loggeduser);
//	}
//	
//	
//	@RequestMapping(value = "/order", method = RequestMethod.POST)
//	public ModelAndView view(@ModelAttribute("order") Order order, HttpServletRequest request)
//			throws ClientException, OrderException {
//		
//		HttpSession session = request.getSession();
//		Client loggeduser = (Client) session.getAttribute("USER");
////		Client u = clientDao.get(loggeduser.getUserId());
//		
////		List<Order> o = new ArrayList<Order>(u.getOrders());
//
////		List<Order> o = orderDao.listByUser((loggeduser.getUserId()));
////		
////        Collections.sort(o, orderDateComparator);
////	
////		ModelAndView mv = new ModelAndView();
////		mv.addObject("orders", o);
////		mv.setViewName("orderlist");
////		return mv;
//		return viewOrderList(loggeduser);
//	}
	
	@RequestMapping(value = "/marketprice", method = RequestMethod.POST)
	public ModelAndView marketprice(HttpServletRequest request) throws OrderException, ClientException, RecordException{

		List<Record> records = recordDao.list();

		Gson gsonObj = new Gson();
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

		for (Record r: records) {	
			map = new HashMap<Object,Object>(); map.put("y", r.getPrice());  map.put("label", r.getDate().getTime()/1000); list.add(map);
		}
//		map = new HashMap<Object,Object>(); map.put("y", 17363);  map.put("label", "2005-06"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 28726);  map.put("label", "2006-07"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 35000);  map.put("label", "2007-08"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 25250);  map.put("label", "2008-09"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 19750);  map.put("label", "2009-10"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 18850);  map.put("label", "2010-11"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 26700);  map.put("label", "2011-12"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 16000);  map.put("label", "2012-13"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 19000);  map.put("label", "2013-14"); list.add(map);
//		map = new HashMap<Object,Object>(); map.put("y", 18000);  map.put("label", "2014-15"); list.add(map);	
		String dataPoints = gsonObj.toJson(list);
 
		ModelAndView mv = new ModelAndView();
		mv.addObject("dataPoints", dataPoints);
		mv.setViewName("marketprice");
		return mv;

	}
	
//	@RequestMapping(value = "/match", method = RequestMethod.POST)
//	public ModelAndView match(@ModelAttribute("order") Order order, HttpServletRequest request)
//			throws CategoryException, AdvertException, ClientException, OrderException, BitcoinException {
//		
////		Service service = new Service();
////		service.matchTradingRequest();
////		List<Order> orders = orderDao.list();
////		List<Record> records = recordDao.list();
////		for(Order o : orders) {
////			System.out.println(o.getPrice() + " " + o.getUSER_ID());
////		}
//		List<Order> buyOrders = orderDao.listByTypeAndStatus("buy", "Pending");
//		List<Order> sellOrders = orderDao.listByTypeAndStatus("sell", "Pending");
//
//		for(Order buyOrder : buyOrders) {
//			for(Order sellOrder : sellOrders) {
//				match(buyOrder, sellOrder);
//			}
//		}
//		List<Order> orders = orderDao.list();
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("orders", orders);
//		mv.setViewName("orderlist");
//		return mv;
//		
////		HttpSession session = request.getSession();
////		Client loggeduser = (Client) session.getAttribute("USER");
////		Client u = clientDao.get(loggeduser.getUserId());
////		Set orders = u.getOrders();
////		
////		ModelAndView mv = new ModelAndView();
////		mv.addObject("orders", orders);
////		mv.setViewName("orderlist");
////		return mv;
//	}
//	public void match(Order buyOrder, Order sellOrder) throws ClientException, BitcoinException{
//		int buyAmount = buyOrder.getAmount();
//		for(Record r: buyOrder.getRecords()) {
//			buyAmount -= r.getAmount();
//		}
//		int sellAmount = buyOrder.getAmount();
//		for(Record r: buyOrder.getRecords()) {
//			sellAmount -= r.getAmount();
//		}
//		
////		int buyAmount = buyOrder.getAmount();
//		double buyPrice = buyOrder.getPrice();
////		int sellAmount = sellOrder.getAmount();
//		double sellPrice = sellOrder.getPrice();    
//		if(buyPrice < sellPrice) return;
//		
//		Client buyer = clientDao.get(buyOrder.getClient().getUserId());
//		Client seller = clientDao.get(sellOrder.getClient().getUserId());
//
//      int dealAmount = buyAmount;
//      int bitCoinOfSeller = seller.getBitcoins().size();
//      if(buyAmount > sellAmount) dealAmount = Math.min(sellAmount, bitCoinOfSeller);           
//      if(buyer.getBalance() < sellPrice*dealAmount){ 
//          dealAmount = (int)Math.floor(buyer.getBalance()/sellPrice);
//      }                
//      if(dealAmount==0) return; 
//		
//      
//      
//    /**
//     * Begin to transaction
//    */       
//      
//      Set sellerBitcoins = seller.getBitcoins();
//      Set buyerBitcoins = buyer.getBitcoins();
//      for(int i = 0; i< dealAmount; i++){
//    	  Bitcoin sellerBitcoin = bitcoinDao.get(seller.getUserId());
//    	  buyerBitcoins.add(sellerBitcoin);
//    	  sellerBitcoins.remove(sellerBitcoin);
////    	  bitcoinDao.delete(sellerBitcoin);
//        
////        Coin coin = sellerAccount.getCoinCollection().getCoinList().get(0);
////        transactionHistory.createTransaction(coin.getCoinKey(), sellerAccount.getName(), buyerAccount.getName(),coin.getType());
//      }
//    buyer.setBitcoins(buyerBitcoins);
//    clientDao.update(buyer);
//        
////    /*Service Fee*/
////    double actualSpend = sellPrice * dealAmount * (1 + buyServiceRate);
////    double actualEarn = sellPrice * dealAmount * (1 + sellServiceRate);
////    double custodyBuyRequestEarn = sellPrice * dealAmount * buyServiceRate;
////    double custodySellRequestEarn = sellPrice * dealAmount * sellServiceRate;
////    sellerAccount.setDollorAccount(sellerAccount.getDollorAccount() + actualEarn);
////    buyerAccount.setDollorAccount(buyerAccount.getDollorAccount() - actualSpend);  
////    buyRequestCustodyAccount.setDollorAccount(buyRequestCustodyAccount.getDollorAccount() + custodyBuyRequestEarn);
////    sellRequestCustodyAccount.setDollorAccount(sellRequestCustodyAccount.getDollorAccount() + custodySellRequestEarn);
//    seller.setBalance(seller.getBalance() + sellPrice * dealAmount);
//    buyer.setBalance(buyer.getBalance() - sellPrice * dealAmount);
//    
//    Set<Record> buyOrderRecords = buyOrder.getRecords() == null? new HashSet<Record>():buyOrder.getRecords();
//    buyOrderRecords.add(new Record(dealAmount, sellPrice));
//    buyOrder.setRecords(buyOrderRecords);
//
//    Set<Record> sellOrderRecords = sellOrder.getRecords() == null? new HashSet<Record>():sellOrder.getRecords();
//    sellOrderRecords.add(new Record(dealAmount, sellPrice));
//    sellOrder.setRecords(sellOrderRecords);
//    
//    if( buyAmount == dealAmount) buyOrder.setStatus("Filled");
//    if( sellAmount == dealAmount) sellOrder.setStatus("Filled");
//    
//    
////    buyRequest.setAmount(buyRequest.getAmount() - dealAmount);
////    sellRequest.setAmount(sellRequest.getAmount() - dealAmount);
////    buyRequest.getDealRecord().addDeal(dealAmount, sellPrice);
////    sellRequest.getDealRecord().addDeal(dealAmount, sellPrice);
////    buyRequest.setStatus("Trading");
////    sellRequest.setStatus("Trading");  
////    if(buyRequest.getAmount() == 0) buyRequest.setStatus("Filled");
////    if(sellRequest.getAmount() == 0) sellRequest.setStatus("Filled");   
////		System.out.println("match");
////		Set<Record> buyRecords = buyOrder.getRecords();
//		
//	}
}