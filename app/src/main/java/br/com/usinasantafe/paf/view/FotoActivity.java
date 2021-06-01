package br.com.usinasantafe.paf.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.paf.PAFContext;
import br.com.usinasantafe.paf.R;
import br.com.usinasantafe.paf.model.bean.variaveis.FotoBean;
import br.com.usinasantafe.paf.util.Tempo;

public class FotoActivity extends ActivityGeneric {

    private RecyclerView mRecyclerView;
    private List<FotoBean> fotoList;
    private PAFContext pafContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        pafContext = (PAFContext) getApplication();

        Button buttonCapturaFoto = (Button) findViewById(R.id.buttonCapturaFoto);
        Button buttonAvancaFoto = (Button) findViewById(R.id.buttonAvancaFoto);
        Button buttonRetFoto = (Button) findViewById(R.id.buttonRetFoto);

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(FotoActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        fotoList = pafContext.getFormularioCTR().fotoFormIniciado();

        AdapterListFoto adapterListFoto = new AdapterListFoto(FotoActivity.this, fotoList);
        mRecyclerView.setAdapter(adapterListFoto);

        buttonCapturaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        buttonAvancaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pafContext.getFormularioCTR().verFormularioIniciado()) {

                    if ((getLongitude() != 0D) && (Tempo.getInstance().dataSHoraComTZ().equals(pafContext.getFormularioCTR().getFormularioIniciado().getDataInsForm()))) {
                        pafContext.getFormularioCTR().setLatLongForm(getLatitude(), getLongitude());
                        Intent it = new Intent(FotoActivity.this, ListaAnimalActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        pafContext.getFormularioCTR().setLatLongForm(getLatitude(), getLongitude());
                        Intent it = new Intent(FotoActivity.this, LocalizacaoActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    Intent it = new Intent(FotoActivity.this, ListaInformacaoActivity.class);
                    startActivity(it);
                    finish();
                }


            }
        });

        buttonRetFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pafContext.getFormularioCTR().verFormularioIniciado()) {
                    Intent it = new Intent(FotoActivity.this, DataActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(FotoActivity.this, ListaInformacaoActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void tirarFoto(){
        if(fotoList.size() < 2){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
        else{
            AlertDialog.Builder alerta = new AlertDialog.Builder(FotoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("CADA FORMULÁRIO PODEM TER APENAS 2 FOTOS. POR FAVOR, EXCLUA UMA FOTO PARA PODE TIRA UMA NOVA FOTO.");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alerta.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            fotoList.add(pafContext.getFormularioCTR().salvarFoto(bitmap));

            Intent it = new Intent(FotoActivity.this, FotoActivity.class);
            startActivity(it);
            finish();

        }

    }

    public void onBackPressed() {
    }

}