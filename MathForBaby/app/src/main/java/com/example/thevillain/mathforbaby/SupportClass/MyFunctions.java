package com.example.thevillain.mathforbaby.SupportClass;

import android.content.Context;
import android.content.SharedPreferences;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyFunctions {
    JSONParser jsonparser;

    private final String ip = "http://169.254.97.73:9091";
    String loginurl=ip+"/webAPI_php/index.php";
    String registerurl=ip+"/webAPI_php/index.php";
    //Get All
    String alluniturl=ip+"/webAPI_php/getallunit.php";
    String allexamurl=ip+"/webAPI_php/getallexam.php";

    String userdetailsurl=ip+"/webAPI_php/getuserbyusername.php";
    //Get Title Unit
    String titleuniturl=ip+"/webAPI_php/gettitleunit.php";
    //Get Detail Unit
    String getunitdetailurl=ip+"/webAPI_php/getunitdetail.php";

    String getexamdetailurl=ip+"/webAPI_php/getexamdetail.php";
    //Create
    String createuniturl=ip+"/webAPI_php/createunit.php";
    String createexam=ip+"/webAPI_php/createexam.php";
    //Update
    String updateunit=ip+"/webAPI_php/updateunit.php";
    String updateexam=ip+"/webAPI_php/updateexam.php";
    //Deleta
    String deleteunit=ip+"/webAPI_php/deleteunit.php";
    String deleteexam=ip+"/webAPI_php/deleteexam.php";

    String login_tag="login";
    String register_tag="register";

    Context context;

    //ham tao khoi ta doi tuong jsonparser
    public MyFunctions(Context context)
    {
        jsonparser=new JSONParser();
        this.context=context;
    }
    public boolean checkLogin()
    {
        SharedPreferences lay=
                context.getSharedPreferences(null,context.MODE_WORLD_READABLE);
        String emaillogined=lay.getString("emaillogined","chua login");
        if(emaillogined.equals("chua login"))
            return false;
        else
            return true;
    }

    public String getEmail()
    {
        SharedPreferences lay=
                context.getSharedPreferences(null,context.MODE_WORLD_READABLE);
        String emaillogined=lay.getString("emaillogined","chua login");
        return emaillogined;
    }

    public boolean logOut()
    {
        SharedPreferences ghi=
                context.getSharedPreferences(null,context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor=ghi.edit();
        editor.putString("emaillogined", "chua login");
        editor.commit();
        return true;
    }

    public boolean setemaillogin(String username)
    {
        SharedPreferences ghi=
                context.getSharedPreferences(null,context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor=ghi.edit();
        editor.putString("emaillogined", username);
        editor.commit();
        return true;
    }

    public JSONObject loginUser(String username, String password)
    {
        List<NameValuePair> cacdoiso=new ArrayList<NameValuePair>();
        cacdoiso.add(new BasicNameValuePair("tag",login_tag) );
        cacdoiso.add(new BasicNameValuePair("username", username));
        cacdoiso.add(new BasicNameValuePair("password", password));

        JSONObject jobj=jsonparser.getJSONFromUrl(loginurl, cacdoiso);

        setemaillogin(username);//gan len share de nho da login roi

        return jobj;
    }

    public JSONObject registerUser(String avatar, String fullname, String username, String password, String highscore, String account_type)
    {
        List<NameValuePair> cacdoiso=new ArrayList<NameValuePair>();
        cacdoiso.add(new BasicNameValuePair("tag",register_tag));
        cacdoiso.add(new BasicNameValuePair("avatar",avatar));
        cacdoiso.add(new BasicNameValuePair("fullname",fullname));
        cacdoiso.add(new BasicNameValuePair("username", username));
        cacdoiso.add(new BasicNameValuePair("password", password));
        cacdoiso.add(new BasicNameValuePair("highscore", highscore));
        cacdoiso.add(new BasicNameValuePair("account_type", account_type));

        JSONObject jobj=jsonparser.getJSONFromUrl(registerurl, cacdoiso);
        return jobj;
    }

    //get all Unit
    public JSONObject getAllUnit()
    {
        //POST khong can doi so nen ta tao doi so rong
        List<NameValuePair> cacdoiso=new ArrayList<NameValuePair>();
        JSONObject jobj=jsonparser.getJSONFromUrl(alluniturl, cacdoiso);
        return jobj;
    }
    //getTitleUnit
    public JSONObject getTitleUnit()
    {
        //POST khong can doi so nen ta tao doi so rong
        List<NameValuePair> cacdoiso=new ArrayList<NameValuePair>();
        JSONObject jobj=jsonparser.getJSONFromUrl(titleuniturl, cacdoiso);
        return jobj;
    }
    //Detail
    public JSONObject getDetail(String id)
    {
        List<NameValuePair> cacdoiso = new ArrayList<NameValuePair>();
        cacdoiso.add(new BasicNameValuePair("id",id));
        JSONObject jobj = jsonparser.getJSONFromUrl(getunitdetailurl,cacdoiso);
        return jobj;
    }

<<<<<<< HEAD
    //Detail
    public JSONObject getExamDetail(String id)
    {
        List<NameValuePair> cacdoiso = new ArrayList<NameValuePair>();
        cacdoiso.add(new BasicNameValuePair("id",id));
        JSONObject jobj = jsonparser.getJSONFromUrl(getexamdetailurl,cacdoiso);
=======
    public JSONObject getUserByUsername(String username)
    {
        //POST khong can doi so nen ta tao doi so rong
        List<NameValuePair> cacdoiso=new ArrayList<NameValuePair>();
        cacdoiso.add(new BasicNameValuePair("username",username));
        JSONObject jobj=jsonparser.getJSONFromUrl(userdetailsurl, cacdoiso);
>>>>>>> fb2d47169f7a25abcd33395b74958309a4f4b2ae
        return jobj;
    }

    public JSONObject getAllExam()
    {
        //POST khong can doi so nen ta tao doi so rong
        List<NameValuePair> cacdoiso=new ArrayList<NameValuePair>();
        JSONObject jobj=jsonparser.getJSONFromUrl(allexamurl, cacdoiso);
        return jobj;
    }

    //CreateUnit
    public JSONObject createUnit(String unit_name,String unit_img, String lesson1_img,String lesson1,String lesson2_img,String lesson2,String lesson3_img,String lesson3)
    {
        // tao cac doi so
        List<NameValuePair> cacdoiso = new ArrayList<NameValuePair>();
        cacdoiso.add(new BasicNameValuePair("unit_name", unit_name));
        cacdoiso.add(new BasicNameValuePair("unit_img", unit_img));
        cacdoiso.add(new BasicNameValuePair("lesson1_img", lesson1_img));
        cacdoiso.add(new BasicNameValuePair("lesson1", lesson1));
        cacdoiso.add(new BasicNameValuePair("lesson2_img", lesson2_img));
        cacdoiso.add(new BasicNameValuePair("lesson2", lesson2));
        cacdoiso.add(new BasicNameValuePair("lesson3_img", lesson3_img));
        cacdoiso.add(new BasicNameValuePair("lesson3", lesson3));
        JSONObject jobj=jsonparser.getJSONFromUrl(createuniturl, cacdoiso);
        return jobj;
    }
    //CreateExam
    public JSONObject createExam(String image,String questions, String answer1,String answer2,String resultans,String score)
    {
        // tao cac doi so
        List<NameValuePair> cacdoiso = new ArrayList<NameValuePair>();
        cacdoiso.add(new BasicNameValuePair("image", image));
        cacdoiso.add(new BasicNameValuePair("questions", questions));
        cacdoiso.add(new BasicNameValuePair("answer1", answer1));
        cacdoiso.add(new BasicNameValuePair("answer2", answer2));
        cacdoiso.add(new BasicNameValuePair("resultans", resultans));
        cacdoiso.add(new BasicNameValuePair("score", score));
        JSONObject jobj=jsonparser.getJSONFromUrl(createexam, cacdoiso);
        return jobj;
    }
    //UpdateUnit
    public JSONObject updateUnit(String id, String unit_name, String unit_img, String lesson1_img, String lesson1,String lesson2_img,String lesson2,String lesson3_img,String lesson3)
    {
        List<NameValuePair> productOjb = new ArrayList<NameValuePair>();
        productOjb.add(new BasicNameValuePair("id", id));
        productOjb.add(new BasicNameValuePair("unit_name", unit_name));
        productOjb.add(new BasicNameValuePair("unit_img", unit_img));
        productOjb.add(new BasicNameValuePair("lesson1_img", lesson1_img));
        productOjb.add(new BasicNameValuePair("lesson1", lesson1));
        productOjb.add(new BasicNameValuePair("lesson2_img", lesson2_img));
        productOjb.add(new BasicNameValuePair("lesson2", lesson2));
        productOjb.add(new BasicNameValuePair("lesson3_img", lesson3_img));
        productOjb.add(new BasicNameValuePair("lesson3", lesson3));
        JSONObject jobj=jsonparser.getJSONFromUrl(updateunit, productOjb);
        return jobj;
    }
    //UpdateExam
    public JSONObject updateExam(String id, String image, String questions, String answer1, String answer2,String resultans,String score,String lesson3_img,String lesson3)
    {
        List<NameValuePair> productOjb = new ArrayList<NameValuePair>();
        productOjb.add(new BasicNameValuePair("id", id));
        productOjb.add(new BasicNameValuePair("image", image));
        productOjb.add(new BasicNameValuePair("questions", questions));
        productOjb.add(new BasicNameValuePair("answer1", answer1));
        productOjb.add(new BasicNameValuePair("answer2", answer2));
        productOjb.add(new BasicNameValuePair("resultans", resultans));
        productOjb.add(new BasicNameValuePair("score", score));
        JSONObject jobj=jsonparser.getJSONFromUrl(updateexam, productOjb);
        return jobj;
    }

    //Delete
    public JSONObject deleteUnit(String id)
    {
        List<NameValuePair> productID = new ArrayList<NameValuePair>();
        productID.add(new BasicNameValuePair("id", id));
        JSONObject jobj=jsonparser.getJSONFromUrl(deleteunit, productID);
        return jobj;
    }

    public JSONObject deleteExam(String id)
    {
        List<NameValuePair> productID = new ArrayList<NameValuePair>();
        productID.add(new BasicNameValuePair("id", id));
        JSONObject jobj=jsonparser.getJSONFromUrl(deleteexam, productID);
        return jobj;
    }

}
