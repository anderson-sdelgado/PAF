package br.com.usinasantafe.paf.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.paf.PAFContext;
import br.com.usinasantafe.paf.R;
import br.com.usinasantafe.paf.model.bean.variaveis.FormularioBean;
import br.com.usinasantafe.paf.model.bean.variaveis.FotoBean;

public class ListaInformacaoActivity extends ActivityGeneric {

    private PAFContext pafContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_informacao);

        pafContext = (PAFContext) getApplication();

        Button buttonSalvarForm = (Button) findViewById(R.id.buttonSalvarForm);
        Button buttonExcluirForm = (Button) findViewById(R.id.buttonExcluirForm);

        FormularioBean formularioBean = pafContext.getFormularioCTR().getFormularioAberto();

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("DATA " + formularioBean.getDataInsForm());
        List<FotoBean> fotoList = pafContext.getFormularioCTR().fotoFormAberto();
        itens.add("QTDE DE FOTOS => " + fotoList.size());
        fotoList.clear();

        if(formularioBean.getLatitudeForm() != 0D){
            itens.add("PEGO A LOCALIZAÇÃO POR GPS");
        }
        else{
            itens.add("LOCALIZAÇÃO DESCRITA:\n" + formularioBean.getDescrLocalForm());
        }
        if(formularioBean.getIdAnimal() > 0L){
            itens.add("ANIMAL:\n" + pafContext.getFormularioCTR().getAnimalId(formularioBean.getIdAnimal()).getNomeAnimal());
        }
        else{
            itens.add("ANIMAL DESCRITO:\n" + formularioBean.getNomeAnimal());
        }
        if(formularioBean.getMatricVistoriador() > 0L){
            itens.add("VISTORIADOR:\n" + formularioBean.getMatricVistoriador()
                                        + " - " + pafContext.getFormularioCTR().getMatricColab(formularioBean.getMatricVistoriador()).getNomeColab());
        }
        else{
            itens.add("VISTORIADOR:");
        }
        itens.add("OBSERVAÇÃO:\n" + formularioBean.getObservacao());

        AdapterList adapterList = new AdapterList(this, itens);
        ListView listViewInforForm = (ListView) findViewById(R.id.listViewInforForm);
        listViewInforForm.setAdapter(adapterList);

        listViewInforForm.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                Intent it;
                switch (position) {
                    case 1:
                        it = new Intent(ListaInformacaoActivity.this, FotoActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 2:
                        if(formularioBean.getLatitudeForm() == 0D){
                            it = new Intent(ListaInformacaoActivity.this, LocalizacaoActivity.class);
                            startActivity(it);
                            finish();
                        }
                        break;
                    case 3:
                        if(formularioBean.getIdAnimal() > 0L){
                            it = new Intent(ListaInformacaoActivity.this, ListaAnimalActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            it = new Intent(ListaInformacaoActivity.this, NomeAnimalActivity.class);
                            startActivity(it);
                            finish();
                        }
                        break;
                    case 4:
                        it = new Intent(ListaInformacaoActivity.this, VistoriadorActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case 5:
                        it = new Intent(ListaInformacaoActivity.this, ObservacaoActivity.class);
                        startActivity(it);
                        finish();
                        break;
                }

            }

        });

        buttonSalvarForm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pafContext.getFormularioCTR().fecharForm();
                Intent it = new Intent(ListaInformacaoActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonExcluirForm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaInformacaoActivity.this);
                alerta.setTitle("ATENÇÃO");

                String label = "DESEJA REALMENTE EXCLUIR O FORMULÁRIO INTEIRO?";

                alerta.setMessage(label);

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pafContext.getFormularioCTR().delFormAberto();
                        Intent it = new Intent(ListaInformacaoActivity.this, MenuInicialActivity.class);
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

        });

    }

    public void onBackPressed()  {
    }

}