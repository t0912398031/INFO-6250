package com.neu.edu.controller;

import java.util.HashSet;
import java.util.List;
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

import com.neu.edu.dao.AdvertDao;
import com.neu.edu.dao.BitcoinDao;
import com.neu.edu.dao.CategoryDao;
import com.neu.edu.dao.ClientDao;
import com.neu.edu.dao.OrderDao;
import com.neu.edu.dao.RecordDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.BitcoinException;
import com.neu.edu.exception.CategoryException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.OrderException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.Record;
import com.neu.edu.pojo.User;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Log LOGGER = LogFactory.getLog(AdminController.class);
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	BitcoinDao bitcoinDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	RecordDao recordDao;

	@RequestMapping("/")
	public String viewHome() {
		return "admin";
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	public ModelAndView signin(HttpServletRequest request) throws CategoryException, ClientException {
//		
//		HttpSession session = request.getSession();
//		String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        
//		Client loggedUser = clientDao.authenticateLogin(userName, password);
//		
//        if (loggedUser == null) {
//            session.setAttribute("error", "No user found, please check your username and password");
//            System.out.println("no user");
//            return new ModelAndView("home");
//        } else if(loggedUser.getUserName().equals("lipang")&&loggedUser.getPassword().equals("123")){
//            session.setAttribute("USER", loggedUser);
//            session.setAttribute("error", "");
//            
//            List<Client> clients = clientDao.list();
//            ModelAndView mv = new ModelAndView();
//    		mv.addObject("clients", clients);
//    		mv.setViewName("admin");
//            
//            return mv;
//        } else {
//            session.setAttribute("USER", loggedUser);
//            session.setAttribute("error", "");
//            return new ModelAndView("signin");
//        }
//	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		session.invalidate();
		System.out.println(request.getParameter("delete"));
		
		try {
			System.out.println("delete");
			Client user = clientDao.get(Long.parseLong(request.getParameter("delete")));
			clientDao.delete(user);
			
			List<Client> clients = clientDao.list();
			ModelAndView mv = new ModelAndView();
			mv.addObject("clients", clients);
			mv.setViewName("admin");
	        
	        return mv;
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

        return new ModelAndView("admin");
//		return ("redirect:/admin/");
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView register() throws CategoryException {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("categories", categoryDao.list());
//		mv.addObject("advert", new Advert());
		mv.setViewName("register");
		return mv;
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ModelAndView order(@ModelAttribute("order") Order order, HttpServletRequest request)
			throws CategoryException, AdvertException, ClientException {
		
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
//		System.out.println(loggeduser);
//		System.out.println(loggeduser.getOrders());
		order.setStatus("Pending");
		LOGGER.debug(order);
		try {
			Set orders;
			Client u = clientDao.get(loggeduser.getUserId());
			if(u.getOrders().size() == 0) {
				orders = new HashSet<Order>();
			} else {
				orders = u.getOrders();
			}
			orders.add(order);
			u.setOrders(orders);
			
//			for(Order o : loggeduser.getOrders()) {System.out.println(o.getPrice());}
//			System.out.println(loggeduser.getOrders());
			clientDao.update(u);
			
			Client u2 = clientDao.get(loggeduser.getUserId());
			orders = u2.getOrders();
			
			ModelAndView mv = new ModelAndView();
			mv.addObject("orders", orders);
//			mv.addObject("advert", new Advert());
			mv.setViewName("orderlist");
			return mv;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new ModelAndView("signin");
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public ModelAndView view(@ModelAttribute("order") Order order, HttpServletRequest request)
			throws CategoryException, AdvertException, ClientException, OrderException {
		
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		
//		Client u = clientDao.get(loggeduser.getUserId());
//		Set orders = u.getOrders();
		
		List orders = orderDao.list();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("orders", orders);
		mv.setViewName("orderlist");
		request.setAttribute("admin", "admin");
		return mv;
	}
	
	@RequestMapping(value = "/match", method = RequestMethod.POST)
	public ModelAndView match(@ModelAttribute("order") Order order, HttpServletRequest request)
			throws CategoryException, AdvertException, ClientException, OrderException, BitcoinException {
		
//		Service service = new Service();
//		service.matchTradingRequest();
//		List<Order> orders = orderDao.list();
//		List<Record> records = recordDao.list();
//		for(Order o : orders) {
//			System.out.println(o.getPrice() + " " + o.getUSER_ID());
//		}
		List<Order> buyOrders = orderDao.listByTypeAndStatus("buy", "Pending");
		List<Order> sellOrders = orderDao.listByTypeAndStatus("sell", "Pending");

		for(Order buyOrder : buyOrders) {
			for(Order sellOrder : sellOrders) {
				match(buyOrder, sellOrder);
			}
		}
		List<Order> orders = orderDao.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("orders", orders);
		mv.setViewName("orderlist");
		return mv;
		
//		HttpSession session = request.getSession();
//		Client loggeduser = (Client) session.getAttribute("USER");
//		Client u = clientDao.get(loggeduser.getUserId());
//		Set orders = u.getOrders();
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("orders", orders);
//		mv.setViewName("orderlist");
//		return mv;
	}
	public void match(Order buyOrder, Order sellOrder) throws ClientException, BitcoinException{
		int buyAmount = buyOrder.getAmount();
		for(Record r: buyOrder.getRecords()) {
			buyAmount -= r.getAmount();
		}
		int sellAmount = buyOrder.getAmount();
		for(Record r: buyOrder.getRecords()) {
			sellAmount -= r.getAmount();
		}
		
//		int buyAmount = buyOrder.getAmount();
		double buyPrice = buyOrder.getPrice();
//		int sellAmount = sellOrder.getAmount();
		double sellPrice = sellOrder.getPrice();    
		if(buyPrice < sellPrice) return;

		Client buyer = clientDao.get(buyOrder.getUser().getUserId());
		Client seller = clientDao.get(sellOrder.getUser().getUserId());

      int dealAmount = buyAmount;
      int bitCoinOfSeller = seller.getBitcoins().size();
      if(buyAmount > sellAmount) dealAmount = Math.min(sellAmount, bitCoinOfSeller);           
      if(buyer.getBalance() < sellPrice*dealAmount){ 
          dealAmount = (int)Math.floor(buyer.getBalance()/sellPrice);
      }                
      if(dealAmount==0) return; 
		
      
      
    /**
     * Begin to transaction
    */       
      System.out.println(buyer.getUserId());
      System.out.println(seller.getUserId());
      
      Set sellerBitcoins = seller.getBitcoins();
      Set buyerBitcoins = buyer.getBitcoins();
      System.out.println(buyerBitcoins.size());
      for(int i = 0; i< dealAmount; i++){
    	  Bitcoin sellerBitcoin = bitcoinDao.get(seller.getUserId());
    	  buyerBitcoins.add(sellerBitcoin);
    	  sellerBitcoins.remove(sellerBitcoin);
//    	  bitcoinDao.delete(sellerBitcoin);
    	  System.out.println("transfer");
//        Coin coin = sellerAccount.getCoinCollection().getCoinList().get(0);
//        transactionHistory.createTransaction(coin.getCoinKey(), sellerAccount.getName(), buyerAccount.getName(),coin.getType());
      }
      System.out.println(buyerBitcoins.size());
//    buyer.setBitcoins(buyerBitcoins);
    clientDao.update(buyer);
        
//    /*Service Fee*/
//    double actualSpend = sellPrice * dealAmount * (1 + buyServiceRate);
//    double actualEarn = sellPrice * dealAmount * (1 + sellServiceRate);
//    double custodyBuyRequestEarn = sellPrice * dealAmount * buyServiceRate;
//    double custodySellRequestEarn = sellPrice * dealAmount * sellServiceRate;
//    sellerAccount.setDollorAccount(sellerAccount.getDollorAccount() + actualEarn);
//    buyerAccount.setDollorAccount(buyerAccount.getDollorAccount() - actualSpend);  
//    buyRequestCustodyAccount.setDollorAccount(buyRequestCustodyAccount.getDollorAccount() + custodyBuyRequestEarn);
//    sellRequestCustodyAccount.setDollorAccount(sellRequestCustodyAccount.getDollorAccount() + custodySellRequestEarn);
    seller.setBalance(seller.getBalance() + sellPrice * dealAmount);
    buyer.setBalance(buyer.getBalance() - sellPrice * dealAmount);
    
    Set<Record> buyOrderRecords = buyOrder.getRecords() == null? new HashSet<Record>():buyOrder.getRecords();
    buyOrderRecords.add(new Record(dealAmount, sellPrice));
    buyOrder.setRecords(buyOrderRecords);

    Set<Record> sellOrderRecords = sellOrder.getRecords() == null? new HashSet<Record>():sellOrder.getRecords();
    sellOrderRecords.add(new Record(dealAmount, sellPrice));
    sellOrder.setRecords(sellOrderRecords);
    
    if( buyAmount == dealAmount) buyOrder.setStatus("Filled");
    if( sellAmount == dealAmount) sellOrder.setStatus("Filled");
    
    
//    buyRequest.setAmount(buyRequest.getAmount() - dealAmount);
//    sellRequest.setAmount(sellRequest.getAmount() - dealAmount);
//    buyRequest.getDealRecord().addDeal(dealAmount, sellPrice);
//    sellRequest.getDealRecord().addDeal(dealAmount, sellPrice);
//    buyRequest.setStatus("Trading");
//    sellRequest.setStatus("Trading");  
//    if(buyRequest.getAmount() == 0) buyRequest.setStatus("Filled");
//    if(sellRequest.getAmount() == 0) sellRequest.setStatus("Filled");   
//		System.out.println("match");
//		Set<Record> buyRecords = buyOrder.getRecords();
		
	}
}