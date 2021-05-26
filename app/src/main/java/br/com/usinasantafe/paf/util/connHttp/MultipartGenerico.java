package br.com.usinasantafe.paf.util.connHttp;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

import br.com.usinasantafe.paf.control.FormularioCTR;
import br.com.usinasantafe.paf.util.EnvioDadosServ;

public class MultipartGenerico extends AsyncTask<String, Void, String>   {

    private static MultipartGenerico instance = null;

    public MultipartGenerico() {
    }

    public static MultipartGenerico getInstance() {
        if (instance == null)
            instance = new MultipartGenerico();
        return instance;
    }

	@Override
	protected String doInBackground(String... params) {

        String answer = "";

		try{

			String url = params[0];
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();

			String formulario = params[1];
            valores.add(new BasicNameValuePair("formulario", formulario));

			String foto = params[2];
            valores.add(new BasicNameValuePair("foto", foto));

            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            HttpResponse resposta = httpClient.execute(httpPost);
            answer = EntityUtils.toString(resposta.getEntity());

		} catch (Exception e) {
			EnvioDadosServ.getInstance().setEnviando(false);
		}
		
		return answer;
	}

	protected void onPostExecute(String result) {

		try {

			Log.i("PCQ", "VALOR RECEBIDO --> " + result);
			if(result.trim().equals("GRAVOU")){
				FormularioCTR formularioCTR = new FormularioCTR();
				formularioCTR.delFormFechado();
			}
			else{
				EnvioDadosServ.getInstance().setEnviando(false);
			}

		} catch (Exception e) {
			EnvioDadosServ.getInstance().setEnviando(false);
			Log.i("PMM", "Erro = " + e);
		}

	}
	
}
