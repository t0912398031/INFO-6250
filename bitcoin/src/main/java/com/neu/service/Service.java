package com.neu.service;

import java.util.HashSet;
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

import com.neu.edu.dao.AdvertDao;
import com.neu.edu.dao.CategoryDao;
import com.neu.edu.dao.ClientDao;
import com.neu.edu.dao.OrderDao;
import com.neu.edu.dao.UserDao;
import com.neu.edu.exception.AdvertException;
import com.neu.edu.exception.CategoryException;
import com.neu.edu.exception.ClientException;
import com.neu.edu.exception.OrderException;
import com.neu.edu.exception.UserException;
import com.neu.edu.pojo.Advert;
import com.neu.edu.pojo.Bitcoin;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Client;
import com.neu.edu.pojo.Order;
import com.neu.edu.pojo.User;
@Controller
public class Service {

	@Autowired
	ClientDao clientDao;
	
	@Autowired
	OrderDao orderDao;

	public void matchTradingRequest() throws OrderException{
        
		
		List<Order> buyOrders = orderDao.listByType("buy");
		List<Order> sellOrders = orderDao.listByType("sell");
		
		System.out.println(buyOrders);
		System.out.println(sellOrders);
		for(Order o: buyOrders) {
			
		}
//		int buyAmount = buyRequest.getAmount();
//        double buyPrice = buyRequest.getPrice();
//        int sellAmount = sellRequest.getAmount();
//        double sellPrice = sellRequest.getPrice();        
//        if(buyPrice < sellPrice) return;
//        
//        String buyer = buyRequest.getInvestmentEnterprise();
//        String seller = sellRequest.getInvestmentEnterprise();        
//        FinancialAccount buyerAccount = null;
//        FinancialAccount sellerAccount = null;     
//        for(FinancialAccount financialAccount: financialAccountDirectory.getFinancialAccountList()){
//            if(financialAccount.getName().equalsIgnoreCase(buyer)){
//                buyerAccount = financialAccount;
//            }    
//        }
//        for(FinancialAccount financialAccount: financialAccountDirectory.getFinancialAccountList()){
//            if(financialAccount.getName().equalsIgnoreCase(seller)){
//                sellerAccount = financialAccount;
//            }        
//        }       
//        
//        /**
//         * Coin validation , if fake coin exists return
//        */
//        if(!validateSellerCoin(sellerAccount)) {
//            sellRequest.setStatus("Suspended");
//            return;
//        }    
//        
//        /**
//         * calculate dealAmount
//         */
//        
//        int dealAmount = buyAmount;
//        int bitCoinOfSeller = sellerAccount.getCoinCollection().getCoinList().size();
//        if(buyAmount > sellAmount) dealAmount = Math.min(sellAmount, bitCoinOfSeller);           
//        if(buyerAccount.getDollorAccount() < sellPrice*dealAmount){ 
//            dealAmount = (int)Math.floor(buyerAccount.getDollorAccount()/sellPrice);
//        }                
//        if(dealAmount==0) return; 
//
//        /**
//         * custody rate
//        */ 
//        double buyServiceRate = buyRequest.getRate();
//        double sellServiceRate = sellRequest.getRate();
//        FinancialAccount buyRequestCustodyAccount = null;
//        FinancialAccount sellRequestCustodyAccount = null;
//
//        for(FinancialAccount financialAccount: financialAccountDirectory.getFinancialAccountList()){
//            if(financialAccount.getName().equalsIgnoreCase(buyRequest.getCustodyEnterprise())){
//                buyRequestCustodyAccount = financialAccount;
//            }    
//        }
//        for(FinancialAccount financialAccount: financialAccountDirectory.getFinancialAccountList()){
//            if(financialAccount.getName().equalsIgnoreCase(sellRequest.getCustodyEnterprise())){
//                sellRequestCustodyAccount = financialAccount;
//            }    
//        }
//            
//        /**
//         * Begin to transaction
//        */             
//
//        for(int i = 0; i< dealAmount; i++){
//            Coin coin = sellerAccount.getCoinCollection().getCoinList().get(0);
//            transactionHistory.createTransaction(coin.getCoinKey(), sellerAccount.getName(), buyerAccount.getName(),coin.getType());
//        }
//            
//        /*Service Fee*/
//        double actualSpend = sellPrice * dealAmount * (1 + buyServiceRate);
//        double actualEarn = sellPrice * dealAmount * (1 + sellServiceRate);
//        double custodyBuyRequestEarn = sellPrice * dealAmount * buyServiceRate;
//        double custodySellRequestEarn = sellPrice * dealAmount * sellServiceRate;
//        sellerAccount.setDollorAccount(sellerAccount.getDollorAccount() + actualEarn);
//        buyerAccount.setDollorAccount(buyerAccount.getDollorAccount() - actualSpend);  
//        buyRequestCustodyAccount.setDollorAccount(buyRequestCustodyAccount.getDollorAccount() + custodyBuyRequestEarn);
//        sellRequestCustodyAccount.setDollorAccount(sellRequestCustodyAccount.getDollorAccount() + custodySellRequestEarn);
//
//        buyRequest.setAmount(buyRequest.getAmount() - dealAmount);
//        sellRequest.setAmount(sellRequest.getAmount() - dealAmount);
//        buyRequest.getDealRecord().addDeal(dealAmount, sellPrice);
//        sellRequest.getDealRecord().addDeal(dealAmount, sellPrice);
//        buyRequest.setStatus("Trading");
//        sellRequest.setStatus("Trading");  
//        if(buyRequest.getAmount() == 0) buyRequest.setStatus("Filled");
//        if(sellRequest.getAmount() == 0) sellRequest.setStatus("Filled");                   
    }   
}