package com.self.androidsample;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class FileClass {

   public Boolean writeJsonFile(Context context, JSONObject object) {
       String config = "";
       // use the config.
       String filename = "Users.json";
       JSONArray arr = new JSONArray();
       File file = new File(context.getFilesDir(), "Users.json");
       if(file.exists()) {
           try {
               arr = new JSONArray(loadJSONFromAsset(context));
           } catch (JSONException e) {
               e.printStackTrace();
           }
       }
       arr.put(object);
       String fileContents = arr.toString();
               //object.toString();
       FileOutputStream outputStream;
       try {
           outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
           outputStream.write(fileContents.getBytes());
           outputStream.close();
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           return  false;
       }
   }

    public String loadJSONFromAsset(Context context) {
        File file = new File(context.getFilesDir(),"Users.json");
        FileReader fileReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
// This responce will have Json Format String
        String responce = stringBuilder.toString();
        return responce;
    }

    public Boolean isUserAvailable(Context context, String mailID, String pwd) {
        File file = new File(context.getFilesDir(), "Users.json");
        if(file.exists()) {
            JSONArray arr = null;
            try {
                arr = new JSONArray(loadJSONFromAsset(context));
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = new JSONObject();
                    obj = arr.getJSONObject(i);
                    Log.d("obj",String.valueOf(obj));
                    if(obj.getString("email").equals(mailID) && obj.getString("password").equals(pwd)) {
                        return true;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       return false;
    }
}
