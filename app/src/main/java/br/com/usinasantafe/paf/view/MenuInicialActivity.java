package br.com.usinasantafe.paf.view;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.usinasantafe.paf.PAFContext;
import br.com.usinasantafe.paf.R;
import br.com.usinasantafe.paf.TimerAlarme;
import br.com.usinasantafe.paf.util.AtualDadosServ;
import br.com.usinasantafe.paf.util.ConexaoWeb;
import br.com.usinasantafe.paf.util.EnvioDadosServ;
import br.com.usinasantafe.paf.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialListView;
    private PAFContext pafContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pafContext = (PAFContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        progressBar = new ProgressDialog(this);

        if (!checkPermission(Manifest.permission.CAMERA)) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.INTERNET)) {
            String[] PERMISSIONS = {android.Manifest.permission.INTERNET};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.ACCESS_NETWORK_STATE)) {
            String[] PERMISSIONS = {android.Manifest.permission.ACCESS_NETWORK_STATE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        clearBD();

        if(pafContext.getFormularioCTR().verFormularioAberto()) {
            startTimer();
            Intent it = new Intent(MenuInicialActivity.this, ListaInformacaoActivity.class);
            startActivity(it);
            finish();
        }
        else {
            atualizarAplic();
        }

        listarMenuInicial();
    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("FORMULÁRIO");
        itens.add("CONFIGURAÇÃO");
        itens.add("ATUALIZAR DADOS");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = (ListView) findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("FORMULÁRIO")) {

                    if (pafContext.getFormularioCTR().hasElemColab() && pafContext.getConfigCTR().hasElements()) {
                        pafContext.getFormularioCTR().salvarCabecIniciado();
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuInicialActivity.this, DataActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("BASE DE DADOS DESATUALIZADA! POR FAVOR, SELECIONE A OPÇÃO 'ATUALIZAR DADOS' PARA ATUALIZAR A BASE DE DADOS ANTES DE CRIAR UM NOVO FORMULÁRIO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();

                    }

                }
                else if (text.equals("CONFIGURAÇÃO")) {

                    Intent it = new Intent(MenuInicialActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();

                }
                else if (text.equals("ATUALIZAR DADOS")) {

                    ConexaoWeb conexaoWeb = new ConexaoWeb();

                    if(conexaoWeb.verificaConexao(MenuInicialActivity.this)){

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO BASE DE DADOS...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();

                        AtualDadosServ.getInstance().atualTodasTabBD(MenuInicialActivity.this, progressBar);

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();

                    }

                }
                else if (text.equals("SAIR")) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

            }

        });

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (EnvioDadosServ.getInstance().getStatusEnvio() == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando e recebendo de dados...");
            } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem dados para serem enviados e recebidos");
            } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram enviados e recebidos");
            }

            if(!VerifDadosServ.getInstance().isVerTerm()) {
                VerifDadosServ.getInstance().cancelVer();
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                startTimer();
            }
            customHandler.postDelayed(this, 10000);
        }
    };

    public void atualizarAplic(){
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(this)) {
            if (pafContext.getConfigCTR().hasElements()) {
                progressBar.setCancelable(true);
                progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
                progressBar.show();
                customHandler.postDelayed(updateTimerThread, 10000);
                VerifDadosServ.getInstance().verAtualAplic(pafContext.versaoAplic, this, progressBar);
            }
        } else {
            startTimer();
        }
    }

    public void startTimer() {

        if(pafContext.getConfigCTR().hasElements()) {

            boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }

            if (alarmeAtivo) {

                Log.i("PCQ", "NOVO TIMER");

                Intent intent = new Intent(this, TimerAlarme.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.add(Calendar.SECOND, 1);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                if (pendingIntent != null && alarmManager != null) {
                    alarmManager.cancel(pendingIntent);
                }

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, pendingIntent);

            } else {
                Log.i("PCQ", "TIMER já ativo");
            }

        }

    }

    public void clearBD(){
        if(pafContext.getFormularioCTR().verFormularioIniciado()){
            pafContext.getFormularioCTR().delFormIniciado();
        }
    }

}