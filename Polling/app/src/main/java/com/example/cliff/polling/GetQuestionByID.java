package com.example.cliff.polling;

import android.os.AsyncTask;
import android.renderscript.Sampler;

import java.net.*;
import java.io.*;

import javax.xml.validation.Validator;

public class GetQuestionByID extends AsyncTask<String, Void, Void> {
//    public static void main(String[] args) throws Exception {
//        String elementResponse = getQuestionByID(18, "isAlgorithmic");
//		System.out.println(elementResponse);
//    }
	
//	public static String getQuestionByID( int ID, String element){
//		String endpointURL = "";
//		try{
//		 endpointURL = "http://csci4661.com/API.php?endpoint=GetQuestionByID&ID="+ ID +"&element="+ URLEncoder.encode(element,"UTF-8") +"";
//		}
//		catch(UnsupportedEncodingException e){System.out.println("Encoder Error! for GetQuestionByID API");}
////		try{
////		URL APIEndpoint = new URL(endpointURL);
////        URLConnection yc = APIEndpoint.openConnection();
////        BufferedReader in = new BufferedReader(new InputStreamReader(
////                                yc.getInputStream()));
////        String inputLine;
////        while ((inputLine = in.readLine()) != null){
////
////		//check if adding was successful
////			String response = inputLine;
////
////
////				return response;
////		}//end while-loop
////        in.close();
////
////		} //end try
////		catch(IOException e){System.out.println("IOException Error on GetQuestionByID API");}
//		return "";
//
//
//	} //end method


	@Override
	protected Void doInBackground(String... strings) {
		for (int i=0; i < strings.length; i++){
			try{
				URL APIEndpoint = new URL(strings[i]);
				URLConnection yc = APIEndpoint.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						yc.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null){

					//check if adding was successful
					String response = inputLine;


					return null;
				}//end while-loop
				in.close();



			} //end try
			catch(IOException e){System.out.println("IOException Error on GetQuestionByID API");}
		}
		return null;
	}

//	@Override
//	protected void onPostExecute(Question questions) {
//		super.onPostExecute(questions);
//		myQuestion();
//	}

//	private Question myQuestion(){
//
//	}
}