package br.com.usinasantafe.paf.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.paf.PAFContext;
import br.com.usinasantafe.paf.R;

public class NomeAnimalActivity extends ActivityGeneric {

    private PAFContext pafContext;
    private EditText editTextNomeAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_animal);

        pafContext = (PAFContext) getApplication();

        Button buttonRetNomeAnimal = (Button) findViewById(R.id.buttonRetNomeAnimal);
        Button buttonAvancaNomeAnimal = (Button) findViewById(R.id.buttonAvancaNomeAnimal);
        editTextNomeAnimal = (EditText) findViewById(R.id.editTextNomeAnimal);

        buttonRetNomeAnimal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(NomeAnimalActivity.this, ListaAnimalActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaNomeAnimal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextNomeAnimal.getText().toString().equals("")) {

                    pafContext.getFormularioCTR().setIdAnimalForm(0L);
                    pafContext.getFormularioCTR().setNomeAnimalForm(editTextNomeAnimal.getText().toString());
                    Intent it = new Intent(NomeAnimalActivity.this, VistoriadorActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( NomeAnimalActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE O NOME DO ANIMAL.");
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