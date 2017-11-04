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

    private final String ip = "http://192.168.0.107";
    String loginurl=ip+"/webAPI_php/index.php";
    String registerurl=ip+"/webAPI_php/index.php";

    String allproducturl=ip+"/webAPI_php/getallproducts.php";
    String createproducturl=ip+"/webAPI_php/createproduct.php";
    String updateproduct=ip+"/webAPI_php/updateproduct.php";
    String deleteproduct=ip+"/webAPI_php/deleteproduct.php";

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

    public JSONObject getAllProducts()
    {
        //POST khong can doi so nen ta tao doi so rong
        List<NameValuePair> cacdoiso=new ArrayList<NameValuePair>();
        JSONObject jobj=jsonparser.getJSONFromUrl(allproducturl, cacdoiso);
        return jobj;
    }

    public JSONObject createProduct(String name,String price, String image,String description)
    {
        // tao cac doi so
        List<NameValuePair> cacdoiso = new ArrayList<NameValuePair>();
        cacdoiso.add(new BasicNameValuePair("name", name));
        cacdoiso.add(new BasicNameValuePair("price", price));
        cacdoiso.add(new BasicNameValuePair("image", image));
        cacdoiso.add(new BasicNameValuePair("description", description));
        JSONObject jobj=jsonparser.getJSONFromUrl(createproducturl, cacdoiso);
        return jobj;
    }

    public JSONObject updateProduct(String id, String name, String price, String image, String description)
    {
        List<NameValuePair> productOjb = new ArrayList<NameValuePair>();
        productOjb.add(new BasicNameValuePair("id", id));
        productOjb.add(new BasicNameValuePair("name", name));
        productOjb.add(new BasicNameValuePair("price", price));
        productOjb.add(new BasicNameValuePair("image", image));
        productOjb.add(new BasicNameValuePair("description", description));
        JSONObject jobj=jsonparser.getJSONFromUrl(updateproduct, productOjb);
        return jobj;
    }

    public JSONObject deleteProduct(String id)
    {
        List<NameValuePair> productID = new ArrayList<NameValuePair>();
        productID.add(new BasicNameValuePair("id", id));
        JSONObject jobj=jsonparser.getJSONFromUrl(deleteproduct, productID);
        return jobj;
    }

}
