package com.neu.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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

import com.neu.edu.exception.BitcoinException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.OrderException;

import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.Record;

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
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	public ModelAndView editpage(HttpServletRequest request) throws ClientException{

		Client user = clientDao.get(Long.parseLong(request.getParameter("edit")));

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("useredit");
        
        return mv;
	}
	
	@RequestMapping(value = "/user/edit/update", method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request) throws ClientException{

		Client user = clientDao.get(Long.parseLong(request.getParameter("user")));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setBalance((long)Double.parseDouble(request.getParameter("balance")));
		
		clientDao.update(user);
		
		List<Client> clients = clientDao.list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("clients", clients);
		mv.setViewName("user");
        
        return mv;
	}
//	
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView register(){
//		ModelAndView mv = new ModelAndView();
//
//		mv.setViewName("register");
//		return mv;
//	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public ModelAndView view(HttpServletRequest request)
			throws ClientException, OrderException {

		orderDao.close();
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
            		case "orderId":
            			return Long.compare(o1.getOrderId(), o2.getOrderId());
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
			throws ClientException, OrderException, BitcoinException {

		List<Order> buyOrders = orderDao.listByTypeAndStatus("buy", "Pending", "Trading");
		List<Order> sellOrders = orderDao.listByTypeAndStatus("sell", "Pending", "Trading");

		for(Order buyOrder : buyOrders) {
			for(Order sellOrder : sellOrders) {
				match(buyOrder, sellOrder);
			}
		}

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

    System.out.println("seller balance" + seller.getBalance());
    System.out.println("buyer balance" + buyer.getBalance());
    seller.setBalance(seller.getBalance() + sellPrice * dealAmount);
    buyer.setBalance(buyer.getBalance() - sellPrice * dealAmount);
    
    
    System.out.println("balance transfer complete");
    System.out.println("seller balance" + seller.getBalance());
    System.out.println("buyer balance" + buyer.getBalance());

    Set<Record> buyOrderRecords = buyOrder.getRecords();
    buyOrderRecords.add(new Record(dealAmount, sellPrice, buyOrder.getOrderId(), "buy", seller.getUserId()));

    Set<Record> sellOrderRecords = sellOrder.getRecords();
    sellOrderRecords.add(new Record(dealAmount, sellPrice, sellOrder.getOrderId(), "sell", buyer.getUserId()));

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
	}
}