package br.com.usinasantafe.paf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.paf.model.pst.DatabaseHelper;
import br.com.usinasantafe.paf.util.EnvioDadosServ;
import br.com.usinasantafe.paf.util.Tempo;

public class TimerAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PST", "DATA HORA = " + Tempo.getInstance().dataComHora());
		if(EnvioDadosServ.getInstance().verifDadosEnvio()){
			Log.i("PST", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}
	}

}