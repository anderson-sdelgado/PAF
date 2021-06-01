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

public class LocalizacaoActivity extends ActivityGeneric {

    private PAFContext pafContext;
    private EditText editTextLocalizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);

        pafContext = (PAFContext) getApplication();

        Button buttonRetLocalizacao = (Button) findViewById(R.id.buttonRetLocalizacao);
        Button buttonAvancaLocalizacao = (Button) findViewById(R.id.buttonAvancaLocalizacao);
        editTextLocalizacao = (EditText) findViewById(R.id.editTextLocalizacao);

        buttonRetLocalizacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LocalizacaoActivity.this, FotoActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaLocalizacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextLocalizacao.getText().toString().trim().equals("")) {

                    pafContext.getFormularioCTR().setLatLongForm(0D,0D);
                    pafContext.getFormularioCTR().setDescrLocalForm(editTextLocalizacao.getText().toString());
                    Intent it = new Intent(LocalizacaoActivity.this, ListaAnimalActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(LocalizacaoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE A LOCALIZAÇÃO.");
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

    public void onBackPressed() {
    }

}