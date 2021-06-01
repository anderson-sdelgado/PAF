package br.com.usinasantafe.paf.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.paf.PAFContext;
import br.com.usinasantafe.paf.R;

public class ObservacaoActivity extends ActivityGeneric {

    private PAFContext pafContext;
    private EditText editTextObservacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observacao);

        pafContext = (PAFContext) getApplication();

        Button buttonRetObservacao = (Button) findViewById(R.id.buttonRetObservacao);
        Button buttonAvancaObservacao = (Button) findViewById(R.id.buttonAvancaObservacao);
        editTextObservacao = (EditText) findViewById(R.id.editTextObservacao);

        buttonRetObservacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ObservacaoActivity.this, VistoriadorActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaObservacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextObservacao.getText().toString().equals("")) {

                    pafContext.getFormularioCTR().setObservacaoForm(editTextObservacao.getText().toString());
                    pafContext.getFormularioCTR().abrirForm();
                    Intent it = new Intent(ObservacaoActivity.this, ListaInformacaoActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ObservacaoActivity.this);
                    alerta.setTitle("ATENÇÃO");

                    String label = "DESEJA REALMENTE AVANÇAR SEM COLOCAR UMA OBSERVAÇÃO?";

                    alerta.setMessage(label);

                    alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            pafContext.getFormularioCTR().setObservacaoForm("null");
                            pafContext.getFormularioCTR().abrirForm();
                            Intent it = new Intent(ObservacaoActivity.this, ListaInformacaoActivity.class);
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

    }

    public void onBackPressed() {
    }

}