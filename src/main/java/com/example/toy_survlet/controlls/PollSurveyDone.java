package com.example.toy_survlet.controlls;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.toy_survlet.daos.PollsDao;

@WebServlet(urlPatterns = "/poll/SurveyDone")
public class PollSurveyDone extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {    
            
        HashMap<String, Object> map = new HashMap<String, Object>();
    
        Enumeration<String> enumber = request.getParameterNames(); //getParameterNames는 key, value /name, value 에서 key, name 만 잡아올수 있음, 잡아와서 Enumeration<String>으로 바꿔주는 코드
    
        //print params
        while (enumber.hasMoreElements()) {
            String key = enumber.nextElement().toString();
            String value = request.getParameter(key);
            System.out.println(key+ ","+ value);
            map.put(key, value);  
        }
        PollsDao pollsDao = new PollsDao();
        pollsDao.Insert(map);
            //getParameterMap에 마우스를 놓고 오른쪽 키 누르고 'go to definition' 눌러서 데이터 타입 확인 가능, 하지만 arraylist라 다루기 힘들다.

            //JSP 호출
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/simples.jsp"); // 가는 방향 결정남
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
