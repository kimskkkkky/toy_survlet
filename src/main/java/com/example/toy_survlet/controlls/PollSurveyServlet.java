package com.example.toy_survlet.controlls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.toy_survlet.daos.PollsDao;

// /helloWorldJSPServlet?company=YoJuLab
@WebServlet(urlPatterns = "/poll/SurveyServlet")
public class PollSurveyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // String contents = "Yoju Lab !";
            String contents = request.getParameter("company");

            PollsDao pollsDao = new PollsDao();
            ArrayList surveyList = pollsDao.SelectWithSearch(contents);
            String compare ="";
            for(int i = 0; i <surveyList.size(); i=i+1){
            HashMap survey = (HashMap) surveyList.get(i);
            String question = (String)survey.get("QUESTIONS");
            String questionId = (String)survey.get("QUESTIONS_ID");
            String choice = (String)survey.get("CHOICE");
            if(!compare.equals(questionId)){            
            System.out.println(question);   
            System.out.println(choice);
                compare = questionId;
            }else {
                System.out.println(choice);
            }
            }

            request.setAttribute("contents", contents);
            request.setAttribute("surveyList", surveyList);
            // 다음 파일 호출
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/surveys/survey.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
