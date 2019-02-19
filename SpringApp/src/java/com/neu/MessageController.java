/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu;

import com.neu.Dao.MessageDao;
import com.neu.pojo.Login;
import com.neu.pojo.Message;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Chung-Yang Li
 */
public class MessageController extends AbstractController {
    
    public MessageController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        Login loggedUser = (Login) session.getAttribute("USER");
        ModelAndView mv = null;
        
        if(loggedUser == null){
            return new ModelAndView("loginPage");
        }
        MessageDao msgDao = new MessageDao();
        String option = request.getParameter("option" == null? "": request.getParameter("option"));
        switch(option){
            case"send":
                break;
            default:
                List<Message> userMessages = msgDao.getMessages(loggedUser.getUsername());
                
                mv = new ModelAndView("messageHome","userMessages", userMessages);
        
        }
        return mv;
    }
    
}
