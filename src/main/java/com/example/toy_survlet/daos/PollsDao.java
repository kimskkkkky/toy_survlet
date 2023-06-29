package com.example.toy_survlet.daos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.toy_survlet.common.Common;

public class PollsDao {
    public int Insert(HashMap <String, Object> map){
        int count = 0;
        try {
            Common commons = new Common();
            Statement statement = commons.getStatement(); // Editor in Workbanch
            String query = "insert into statistics\r\n" + //
                    "(STATISTICS_ID, RESPONDENTS_ID, QUESTIONS_ID, CHOICE_ID)\r\n" + //
                    "values\r\n" ;

            //print map
            int loops = 1;
            String userID = "R1"; // from session
           
            for(String key : map.keySet()){
                String uuid = commons.generateUUID();
                if(loops > 1){
                   query = query + ",";
                }
                query = query + "('"+uuid+"', '"+userID+"', '"+key+"', '"+map.get(key)+"')\r\n" ;
            // System.out.println(key +","+ map.get(key)); 
            loops = loops + 1;
        }
        query = query + ";";
        count = statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public ArrayList SelectWithSearch(String search) {
        ArrayList InforList = new ArrayList<>();
        try {
            Common commons = new Common();
            Statement statement = commons.getStatement(); // Editor in Workbanch
            String query = "SELECT T_QC.QUESTIONS_ID, T_QC.CHOICE_ID, T_Q.QUESTIONS, T_C.CHOICE\n" + //
                    "FROM db_survey.question_choice as T_QC\n" + //
                    "\tinner join questions as T_Q\n" + //
                    "\ton T_Q.QUESTIONS_ID = T_QC.QUESTIONS_ID\n" + //
                    "    inner join choice as T_C\n" + //
                    "\ton T_C.CHOICE_ID = T_QC.CHOICE_ID\n" + //
                    "    order by T_QC.QUESTIONS_ID, T_QC.CHOICE_ID\n" + //
                    ";";
            ResultSet resultSet = statement.executeQuery(query);

            HashMap InforRecord = new HashMap<>();
            while (resultSet.next()) {
                InforRecord = new HashMap<>();
                InforRecord.put("QUESTIONS_ID", resultSet.getString("QUESTIONS_ID"));
                InforRecord.put("CHOICE_ID", resultSet.getString("CHOICE_ID"));
                InforRecord.put("QUESTIONS", resultSet.getString("QUESTIONS"));
                InforRecord.put("CHOICE", resultSet.getString("CHOICE"));

                InforList.add(InforRecord);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return InforList;
    }
}
