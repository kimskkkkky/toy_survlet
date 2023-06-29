<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.HashMap, java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <title> 설문 조사 </title>
</head>
<body>
    <div class="container bg-info p-0 row mx-auto">
        <div class="fs-4 py-3 d-flex justify-content-start col-6">
            <button type="submit" formaction="" class=" btn btn-info text-light">회원관리</button>
            <button type="submit" formaction="" class=" btn btn-info text-light">설문하기</button>
            <button type="submit" formaction="" class=" btn btn-info text-light">설문통계</button>
        </div>
        <div class="fs-4 py-3 d-flex justify-content-end col-6">
            <button type="submit" formaction="" class="btn btn-info text-light">로그인</button>
        </div>
    </div>
    <br>
    <form method="get" action="/poll/SurveyDone"> 
    
    <div class=" container mx-auto">
        <%
        ArrayList surveyList = (ArrayList)request.getAttribute("surveyList");
        String compare = "";
        for (int i = 0; i < surveyList.size(); i = i + 1) {
            HashMap survey = (HashMap) surveyList.get(i);
            String questions = (String) survey.get("QUESTIONS");
            String questionsId = (String) survey.get("QUESTIONS_ID");
            String choice = (String) survey.get("CHOICE");
            String choiceId = (String) survey.get("CHOICE_ID");
            if (!compare.equals(questionsId)) {
        %>
        <p> <%= questions %> </p>
        <label><input type="radio" name="<%=questionsId%>" value="<%=choiceId%>"> <%=choice %> <%=choiceId%> </label> <br>
<%
        compare = questionsId;
    } else {
%>
        <label><input type="radio" name="<%=questionsId%>" value="<%=choiceId%>"> <%=choice %> <%=choiceId%> </label> <br>
<%
    }
}
%>
</div>
<div class="container bg-white fs-6 py-6 row mx-auto my-3">
    <div class="text-center d-flex justify-content-center py-2">
        <button type="reset" class=" btn btn-white mx-2 btn-outline-dark">초기화</button>
        <button type="submit" class="btn btn-white btn-outline-dark bg-info">설문 제출</button>
    </div>
</div>
</form>
</body>
</html>