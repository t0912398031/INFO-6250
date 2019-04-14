package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
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


import com.neu.edu.dao.BitcoinDao;

import com.neu.edu.dao.ClientDao;
import com.neu.edu.dao.OrderDao;
import com.neu.edu.dao.RecordDao;
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
	
	@RequestMapping(value = "/back", method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request) throws ClientException {
		List<Client> clients = clientDao.list();

        ModelAndView mv = new ModelAndView();
		mv.addObject("clients", clients);
		mv.setViewName("admin");
        
        return mv;
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView user(HttpServletRequest request) throws ClientException{

		List<Client> clients = clientDao.list();

        ModelAndView mv = new ModelAndView();
		mv.addObject("clients", clients);
		mv.setViewName("user");
        
        return mv;
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request) throws ClientException{

		System.out.println(request.getParameter("delete"));
		Client user = clientDao.get(Long.parseLong(request.getParameter("delete")));
		clientDao.delete(user);
		
		List<Client> clients = clientDao.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("clients", clients);
		mv.setViewName("user");
        
        return mv;
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
		
//		HttpSession session = request.getSession();
//		Client loggeduser = (Client) session.getAttribute("USER");
		
		List<Order> buyorders = orderDao.listByType("buy");
		List<Order> sellorders = orderDao.listByType("sell");
		
		String search = request.getParameter("search")==null? "":request.getParameter("search");
		
		Comparator<Order> orderDateComparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
            	switch(search){
            		case "date":
            			return o1.getDate().compareTo(o2.getDate());
            		case "userId":
            			return Long.compare(o1.getUserId(), o2.getUserId());
    				default :return Long.compare(o1.getOrderId(), o2.getOrderId());
                }

            }
        };
		
        Collections.sort(buyorders, orderDateComparator);
        Collections.sort(sellorders, orderDateComparator);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("buyorders", buyorders);
		mv.addObject("sellorders", sellorders);
		mv.setViewName("adminorderlist");
		request.setAttribute("admin", "admin");
		return mv;
	}
	
	@RequestMapping(value = "/match", method = RequestMethod.POST)
	public ModelAndView match(@ModelAttribute("order") Order order, HttpServletRequest request)
			throws CategoryException, AdvertException, ClientException, OrderException, BitcoinException {

		List<Order> buyOrders = orderDao.listByTypeAndStatus("buy", "Pending");
		List<Order> sellOrders = orderDao.listByTypeAndStatus("sell", "Pending");

		for(Order buyOrder : buyOrders) {
			for(Order sellOrder : sellOrders) {
				match(buyOrder, sellOrder);
			}
		}

		List buyorders = orderDao.listByType("buy");
		List sellorders = orderDao.listByType("sell");
		
		String search = request.getParameter("search")==null? "":request.getParameter("search");
		
		Comparator<Order> orderDateComparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
            	switch(search){
            		case "date":
            			return o1.getDate().compareTo(o2.getDate());
            		case "userId":
            			return Long.compare(o1.getUserId(), o2.getUserId());
    				default :return Long.compare(o1.getOrderId(), o2.getOrderId());
                }

            }
        };
		
        Collections.sort(buyorders, orderDateComparator);
        Collections.sort(sellorders, orderDateComparator);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("buyorders", buyorders);
		mv.addObject("sellorders", sellorders);
		mv.setViewName("adminorderlist");
		request.setAttribute("admin", "admin");
		return mv;
	}
	
	
	public void match(Order buyOrder, Order sellOrder) throws ClientException, BitcoinException, OrderException{
		if(buyOrder.getUserId()==sellOrder.getUserId()) return;
		
		double buyPrice = buyOrder.getPrice();
		double sellPrice = sellOrder.getPrice();
		
		/*Price too high for buyer*/	
		if(buyPrice < sellPrice) return;
		
		/*Get rest buy and sell amount of the order*/
		int buyAmount = buyOrder.getAmount();
		int sellAmount = sellOrder.getAmount();
		
		if(buyOrder.getRecords().size()!=0) {
			for(Record r: buyOrder.getRecords()) {
				buyAmount -= r.getAmount();
			}
		}
		if(sellOrder.getRecords().size()!=0) {
			for(Record r: sellOrder.getRecords()) {
				sellAmount -= r.getAmount();
			}
		}

		Client buyer = clientDao.get(buyOrder.getUserId());
		Client seller = clientDao.get(sellOrder.getUserId());

	  System.out.println("sellAmount"+sellAmount);
	  System.out.println("buyAmount"+buyAmount);
//      int dealAmount = buyAmount;
	  
	  
	  int dealAmount = Math.min(buyAmount, sellAmount); 
	  
	  /*Seller bitcoins not enough*/
      int bitCoinOfSeller = seller.getBitcoins().size();
      System.out.println("sellerbitcoinAmount"+bitCoinOfSeller);
      dealAmount = Math.min(dealAmount, bitCoinOfSeller); 
//      if(dealAmount > sellAmount) dealAmount = Math.min(sellAmount, bitCoinOfSeller); 
      
      /*Buyer balance not enough*/
      if(buyer.getBalance() < sellPrice*dealAmount){ 
          dealAmount = (int)Math.floor(buyer.getBalance()/sellPrice);
      }        
      System.out.println("dealAmount"+dealAmount);
      if(dealAmount==0) return; 
		
      
      
    /**
     * Begin to transaction
    */       
      System.out.println("buyerId"+buyer.getUserId());
      System.out.println("sellerId"+seller.getUserId());
      
//      Set sellerBitcoins = seller.getBitcoins();
      Set buyerBitcoins = buyer.getBitcoins();


      List<Bitcoin> sellerBitcoins = new ArrayList<Bitcoin>(seller.getBitcoins());
//      List<Bitcoin> buyerBitcoins = new ArrayList<Bitcoin>(buyer.getBitcoins());
    System.out.println("dealAmount"+dealAmount);
    System.out.println("buyercoins"+buyerBitcoins.size());
    System.out.println("sellercoins"+sellerBitcoins.size());
  
    
    Bitcoin sellerBitcoin = null;
    
      for(int i = 0; i< dealAmount; i++){
    	  sellerBitcoin = sellerBitcoins.get(i);

    	  System.out.println(sellerBitcoin.getId());
    	  
    	  buyerBitcoins.add(sellerBitcoin);

    	  System.out.println("transfer");
      }
      System.out.println("buyercoins"+buyerBitcoins.size());
      System.out.println("sellercoins"+sellerBitcoins.size());

//    clientDao.update(buyer);
//    clientDao.update(seller);
    

    
    System.out.println("buyercoins"+clientDao.get(buyOrder.getUserId()).getBitcoins().size());
    System.out.println("sellercoins"+clientDao.get(sellOrder.getUserId()).getBitcoins().size());
    System.out.println("bitcoin transfer complete");
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
    System.out.println("balance transfer complete");
    
    
//    Order buyorder= orderDao.get(buyOrder.getOrderId());
//    Order sellorder = orderDao.get(sellOrder.getOrderId());
    
//    Set<Record> buyOrderRecords = buyOrder.getRecords() == null? new HashSet<Record>():buyOrder.getRecords();
    Set<Record> buyOrderRecords = buyOrder.getRecords();
    buyOrderRecords.add(new Record(dealAmount, sellPrice, buyOrder.getOrderId(), "buy", seller.getUserId()));
//    buyOrder.setRecords(buyOrderRecords);

//    Set<Record> sellOrderRecords = sellOrder.getRecords() == null? new HashSet<Record>():sellOrder.getRecords();
    Set<Record> sellOrderRecords = sellOrder.getRecords();
    sellOrderRecords.add(new Record(dealAmount, sellPrice, sellOrder.getOrderId(), "sell", buyer.getUserId()));
//    sellOrder.setRecords(sellOrderRecords);
    buyOrder.setStatus("Trading");
    sellOrder.setStatus("Trading");
    
    int buyRecordAmount = 0;
    int sellRecordAmount = 0;
	
	if(buyOrder.getRecords().size()!=0) {
		for(Record r: buyOrder.getRecords()) {
			buyRecordAmount += r.getAmount();
		}
	}
	if(sellOrder.getRecords().size()!=0) {
		for(Record r: sellOrder.getRecords()) {
			sellRecordAmount += r.getAmount();
		}
	}
    if( buyOrder.getAmount() == buyRecordAmount) buyOrder.setStatus("Filled");
    if( sellOrder.getAmount() == sellRecordAmount) sellOrder.setStatus("Filled");
//    orderDao.update(buyOrder);
//    orderDao.update(sellOrder);
    
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