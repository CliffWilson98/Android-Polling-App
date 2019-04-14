package com.example.cliff.polling;

import android.os.AsyncTask;

import java.net.*;
import java.io.*;

public class NewQuestion extends AsyncTask<String, Void, Void> {
//    public static void main(String[] args) throws Exception {
//        int questionID = createQuestion( "What is bread #@?", 5, 8, "Food", "myke", "Breadquestion", true);
//		System.out.println(questionID);
//    }

//	public static int  createQuestion( String question, int varMin, int varMax, String answerFormat, String username, String title, boolean isAlgorithmic){
//		String endpointURL = "";
//		try{
//		 endpointURL = "http://csci4661.com/API.php?endpoint=CreateQuestion&question="+ URLEncoder.encode(question,"UTF-8") +"&answerFormat="+ URLEncoder.encode(answerFormat,"UTF-8") +"&username="+ URLEncoder.encode(username,"UTF-8") +"&isAlgorithmic="+ isAlgorithmic +"&varMin="+ varMin +"&varMax="+ varMax +"&title="+ URLEncoder.encode(title,"UTF-8")+ "";
//		}
//		catch(UnsupportedEncodingException e){System.out.println("Encoder Error! for createQuestion API");}
//		try{
//		URL APIEndpoint = new URL(endpointURL);
//        URLConnection yc = APIEndpoint.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                                yc.getInputStream()));
//        String inputLine;
//        while ((inputLine = in.readLine()) != null){
//
//		//check if adding was successful
//			String IDstring = inputLine;
//			int ID = 0;
//			if(IDstring.contains("~Success~")){
//				IDstring =  IDstring.substring(9);
//				ID = Integer.parseInt(IDstring);
//				}
//
//				return ID;
//		}//end while-loop
//        in.close();
//
//		} //end try
//		catch(IOException e){System.out.println("IOException Error on CreateQuestion API");}
//		return 0;
//
//
//	} //end method


    @Override
    protected Void doInBackground(String... strings) {
        try {
            for (int i =0; i < strings.length; i++){
                URL APIEndpoint = new URL(strings[i]);
                URLConnection yc = APIEndpoint.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {

                    //check if adding was successful
                    String IDstring = inputLine;
                    int ID = 0;
                    if (IDstring.contains("~Success~")) {
                        IDstring = IDstring.substring(9);
                        ID = Integer.parseInt(IDstring);
                    }

                    return null;
                }//end while-loop
                in.close();
            }

        } //end try
        catch (IOException e) {
            System.out.println("IOException Error on CreateQuestion API");
        }
        return null;
    }
}