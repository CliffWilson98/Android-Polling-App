package com.example.cliff.polling;

import android.os.AsyncTask;

import java.net.*;
import java.io.*;

public class SaveAnswer extends AsyncTask<String, Void, Void> {
//    public static void main(String[] args) throws Exception {
//        saveAnswer(18, "mmerwin2", "0", "What is bread #6", "9");
//    }

//
//	public static void saveAnswer( int ID, String username, String score, String question, String answer){
//	saveAnswer(ID, username, score, question, answer, "Yes");
//	}
//	public static void saveAnswer( int ID, String username, String score, String question, String answer, String attempted){
//		String endpointURL = "";
//		try{
//		 endpointURL = "http://csci4661.com/API.php?endpoint=SaveAnswer&ID="+ ID +"&username="+ URLEncoder.encode(username,"UTF-8") +"&score="+ URLEncoder.encode(score,"UTF-8") +"&question="+ URLEncoder.encode(question,"UTF-8") +"&answer="+ URLEncoder.encode(answer,"UTF-8") +"&attempted="+ URLEncoder.encode(attempted,"UTF-8") +"";
//		}
//		catch(UnsupportedEncodingException e){System.out.println("Encoder Error! for GetQuestionByID API");}
//		try{
//		URL APIEndpoint = new URL(endpointURL);
//        URLConnection yc = APIEndpoint.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                                yc.getInputStream()));
//        String inputLine;
//        while ((inputLine = in.readLine()) != null){
//
//
//		}//end while-loop
//        in.close();
//
//		} //end try
//		catch(IOException e){System.out.println("IOException Error on saveAnswer API");}
//
//
//
//	} //end method


    @Override
    protected Void doInBackground(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            try {
                URL APIEndpoint = new URL(strings[i]);
                URLConnection yc = APIEndpoint.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {


                }//end while-loop
                in.close();

            } //end try
            catch (IOException e) {
                System.out.println("IOException Error on saveAnswer API");
            }
        }
        return null;
    }
}