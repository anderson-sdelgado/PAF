package br.com.usinasantafe.paf.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.paf.PAFContext;
import br.com.usinasantafe.paf.R;
import br.com.usinasantafe.paf.util.ConexaoWeb;

public class VistoriadorActivity extends ActivityGeneric {

    private PAFContext pafContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistoriador);

        pafContext = (PAFContext) getApplication();

        Button buttonOkMotorista = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancMotorista = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(VistoriadorActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(VistoriadorActivity.this)) {

                            progressBar = new ProgressDialog(VistoriadorActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pafContext.getFormularioCTR().atualDadosColab(VistoriadorActivity.this, VistoriadorActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(VistoriadorActivity.this);
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

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

        buttonOkMotorista.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    if (pafContext.getFormularioCTR().verColab(Long.parseLong(editTextPadrao.getText().toString()))) {

                        pafContext.getFormularioCTR().setMatricVistoriadorForm(Long.parseLong(editTextPadrao.getText().toString()));

                        Intent it = new Intent(VistoriadorActivity.this, ObservacaoActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(VistoriadorActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NUMERAÇÃO DO VISTORIADOR INEXISTENTE! FAVOR VERIFICA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }
                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(VistoriadorActivity.this);
                    alerta.setTitle("ATENÇÃO");

                    String label = "DESEJA REALMENTE AVANÇAR SEM COLOCAR UM VISTORIADOR?";

                    alerta.setMessage(label);

                    alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            pafContext.getFormularioCTR().setMatricVistoriadorForm(0L);
                            Intent it = new Intent(VistoriadorActivity.this, ObservacaoActivity.class);
                            startActivity(it);
                            finish();

                        }

                    });


                    alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }

                    });

                    alerta.show();

                }

            }

        });

        buttonCancMotorista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(VistoriadorActivity.this, ListaAnimalActivity.class);
        startActivity(it);
        finish();
    }

}