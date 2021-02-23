package models.validators;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import models.Report;


public class ReportValidator {

    public static List<String> validate(Report r){
        List<String> errors=new ArrayList<String>();

        String title_error=_validateTitle(r.getTitle());
        if(!title_error.equals("")){
            errors.add(title_error);
        }

        String content_error=_validateContent(r.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }

        String commuting_at_error=_validateWorking_at(r.getCommuting_at(),r.getLeaving_at());
        if(!commuting_at_error.equals("")){
            errors.add(commuting_at_error);
        }





        return errors;


    }

    private  static String _validateTitle(String title){
        if(title == null || title.equals("")) {
            return "タイトルを入力してください。";
            }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
            }


        return "";
    }

    private static String _validateWorking_at(Time commuting_at,Time leaving_at) {
        if(commuting_at == null || commuting_at.equals("") || leaving_at == null || leaving_at.equals("") ) {
            return "”出勤”/”退勤”時間を入力してください。";
            }


        return "";
    }




}
