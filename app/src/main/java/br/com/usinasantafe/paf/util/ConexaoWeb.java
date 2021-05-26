package br.com.usinasantafe.paf.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

public class ConexaoWeb {

	public ConexaoWeb() {

	}
	
	public  boolean verificaConexao(Context context) {
	    ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
	    if (cm.getActiveNetworkInfo() != null  
	            && cm.getActiveNetworkInfo().isAvailable()  
	            && cm.getActiveNetworkInfo().isConnected()) {
	        Log.i("ECM", "CONECTA");
			return true;
	    } else {
	        Log.i("ECM", "NAO CONECTA");
			return  false;
		}
	} 
	
}
