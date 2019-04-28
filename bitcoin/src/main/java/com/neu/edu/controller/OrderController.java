package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
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
import com.neu.edu.exception.RecordException;

import com.neu.edu.pojo.Bitcoin;

import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.Record;


@Controller
@RequestMapping("/signin/order/*")
public class OrderController {

//	private static final Log LOGGER = LogFactory.getLog(OrderController.class);
	
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

	@RequestMapping("/back")
	public String back(HttpServletRequest request) throws ClientException {
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		
		Client loggedUser = clientDao.get(loggeduser.getUserId());
		session.setAttribute("USER", loggedUser);
		return "signin";
	}
	
	
	public ModelAndView viewOrderList(Client loggeduser) throws OrderException{
		orderDao.close();
		List<Order> o = orderDao.listByUser((loggeduser.getUserId()));
		
        Collections.sort(o, orderDateComparator);
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("orders", o);
		mv.setViewName("orderlist");
		return mv;		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView view(@ModelAttribute("order") Order order, HttpServletRequest request)
			throws ClientException, OrderException {
		
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");

		return viewOrderList(loggeduser);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createOrder(@ModelAttribute("order") Order order, HttpServletRequest request)
			throws OrderException, ClientException, BitcoinException {
		
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		
		if (order.getAmount() == 0) {
            request.setAttribute("error", "* amount must > 0");
            return new ModelAndView("signin");
        }	
		
		if (order.getType().equalsIgnoreCase("sell") && order.getAmount() > loggeduser.getBitcoins().size()) {
            request.setAttribute("error", "* amount must < " + loggeduser.getBitcoins().size());
            return new ModelAndView("signin");
        }
		
		if (order.getType().equalsIgnoreCase("buy") && order.getPrice()*order.getAmount() > loggeduser.getBalance()) {
            request.setAttribute("error", "* requested order price exceeds your balance");
            return new ModelAndView("signin");
        }
		
		

//		LOGGER.debug(order);
//		order.setStatus("Pending");
		order.setStatus("Create");
		order.setUserId(loggeduser.getUserId());
		
		Client u = clientDao.get(loggeduser.getUserId());
		
	    Set<Order> orders = u.getOrders();
		orders.add(order);
//		u.getOrders().add(order);
		clientDao.update(u);

		return viewOrderList(loggeduser);
	}
	
//	@RequestMapping(value = "/pending", method = RequestMethod.POST)
//	public ModelAndView pendingOrder(@ModelAttribute("order") Order order, HttpServletRequest request)
//			throws OrderException, ClientException, BitcoinException {
//		
//		HttpSession session = request.getSession();
//		Client loggeduser = (Client) session.getAttribute("USER");
//		
//		List<Order> orders = orderDao.listByUser(loggeduser.getUserId());
//		for(Order o: orders) {
//			if (o.getStatus().equalsIgnoreCase("Create")) {
//				o.setStatus("Pending");
//			}
//		}
//		return viewOrderList(loggeduser);
//	}
	
	
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request) throws OrderException, ClientException{

		Order order = orderDao.get(Long.parseLong(request.getParameter("delete")));
		if(order  != null) {
			orderDao.delete(order);
		}
		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		Client u = clientDao.get(loggeduser.getUserId());
		List<Order> o = new ArrayList<Order>(u.getOrders());

        Collections.sort(o, orderDateComparator);
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("orders", o);
		mv.setViewName("orderlist");
		return mv;
	}
	
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	public ModelAndView record(HttpServletRequest request) throws OrderException, RecordException{;
		Order order = orderDao.get(Long.parseLong(request.getParameter("record")));
		List<Record> records = null;
		if(order!=null) {
			records = recordDao.listByOrderId(order.getOrderId());
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("records", records);
		mv.addObject("order", order);
		mv.setViewName("recordlist");
		return mv;

	}

	@RequestMapping(value = "/match", method = RequestMethod.POST)
	public ModelAndView match(@ModelAttribute("order") Order order, HttpServletRequest request)
			throws CategoryException, AdvertException, ClientException, OrderException, BitcoinException {

		HttpSession session = request.getSession();
		Client loggeduser = (Client) session.getAttribute("USER");
		
		List<Order> orders = orderDao.listByUser(loggeduser.getUserId());
		for(Order o: orders) {
			if (o.getStatus().equalsIgnoreCase("Create")) {
				o.setStatus("Pending");
			}
		}
//		orderDao.close();
		
		
		
		
		List<Order> buyOrders = orderDao.listByTypeAndStatus("buy", "Pending", "Trading");
		List<Order> sellOrders = orderDao.listByTypeAndStatus("sell", "Pending", "Trading");

		for(Order buyOrder : buyOrders) {
			for(Order sellOrder : sellOrders) {
				match(buyOrder, sellOrder);
			}
		}
		
//		HttpSession session = request.getSession();
//		Client loggeduser = (Client) session.getAttribute("USER");
		
		return viewOrderList(loggeduser);
	}
	
	
	
	
	public void match(Order b, Order s) throws ClientException, BitcoinException, OrderException{
		Order buyOrder = orderDao.get(b.getOrderId());
		Order sellOrder = orderDao.get(s.getOrderId());
		
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
	  
	  int dealAmount = Math.min(buyAmount, sellAmount); 
	  
	  /*Seller bitcoins might not enough*/
      int bitCoinOfSeller = seller.getBitcoins().size();
      System.out.println("sellerbitcoinAmount"+bitCoinOfSeller);
      dealAmount = Math.min(dealAmount, bitCoinOfSeller);  
      
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

      Set buyerBitcoins = buyer.getBitcoins();

      List<Bitcoin> sellerBitcoins = new ArrayList<Bitcoin>(seller.getBitcoins());

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
      System.out.println("sellercoins"+seller.getBitcoins().size());

    
    

    
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
    System.out.println("seller balance" + seller.getBalance());
    System.out.println("buyer balance" + buyer.getBalance());
    seller.setBalance(seller.getBalance() + sellPrice * dealAmount);
    buyer.setBalance(buyer.getBalance() - sellPrice * dealAmount);
    
    
    System.out.println("balance transfer complete");
    System.out.println("seller balance" + seller.getBalance());
    System.out.println("buyer balance" + buyer.getBalance());
    send(buyer.get);
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
    if( buyOrder.getAmount() == buyRecordAmount){
    	buyOrder.setStatus("Filled");
    	buyOrder.setDealdate(new Date());
    }
    if( sellOrder.getAmount() == sellRecordAmount) {
    	sellOrder.setStatus("Filled"); 
    	sellOrder.setDealdate(new Date());
    } 
    
    
    clientDao.update(buyer);
    clientDao.update(seller);
    
    orderDao.update(buyOrder);
    orderDao.update(sellOrder);
    
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
	
	public static void send(String email) throws EmailException {
        Email e = new SimpleEmail();
//        e.setHostName("mail.myserver.com");
        e.setSmtpPort(465);
        e.setAuthenticator(new DefaultAuthenticator("li.chun@husky.neu.edu", "tonyya66666"));
        e.setSSLOnConnect(true);
        e.setCharset("UTF-8");
        e.setSubject("TestMail");
        e.setFrom("li.chun@husky.neu.edu");
        e.setMsg("your order has been matched");
        e.addTo(email);
        e.send();
        System.out.println("Success!");
    }
	
}