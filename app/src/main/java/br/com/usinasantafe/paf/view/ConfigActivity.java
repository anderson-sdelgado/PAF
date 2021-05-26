package br.com.usinasantafe.paf.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.paf.PAFContext;
import br.com.usinasantafe.paf.R;
import br.com.usinasantafe.paf.util.ConexaoWeb;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextAparelhoConfig;
    private PAFContext pafContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonSalvarConfig =  (Button) findViewById(R.id.buttonSalvarConfig );
        Button buttonCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        Button buttonAtualizarConfig = (Button) findViewById(R.id.buttonAtualizarBD);
        editTextAparelhoConfig = (EditText)  findViewById(R.id.editTextAparelhoConfig);

        pafContext = (PAFContext) getApplication();

        if (pafContext.getConfigCTR().hasElements()) {
            editTextAparelhoConfig.setText(String.valueOf(pafContext.getConfigCTR().getConfig().getNroAparelhoConfig()));
        }

        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextAparelhoConfig.getText().toString().equals("")){

                    pafContext.getConfigCTR().salvarConfig(Long.parseLong(editTextAparelhoConfig.getText().toString()));

                    Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonAtualizarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfigActivity.this)){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pafContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
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
        });

    }
}