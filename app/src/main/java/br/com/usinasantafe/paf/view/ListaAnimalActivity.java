package br.com.usinasantafe.paf.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import br.com.usinasantafe.paf.model.bean.estaticas.AnimalBean;
import br.com.usinasantafe.paf.util.ConexaoWeb;

public class ListaAnimalActivity extends ActivityGeneric {

    private ListView animalListView;
    private List<AnimalBean> animalList;
    private PAFContext pafContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_animal);

        pafContext = (PAFContext) getApplication();

        Button buttonRetAnimal = (Button) findViewById(R.id.buttonRetAnimal);
        Button buttonAtualAnimal = (Button) findViewById(R.id.buttonAtualAnimal);
        Button buttonDigitarNome = (Button) findViewById(R.id.buttonDigitarNome);

        buttonAtualAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaAnimalActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaAnimalActivity.this)) {

                            progressBar = new ProgressDialog(ListaAnimalActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pafContext.getFormularioCTR().atualDadosAnimal(ListaAnimalActivity.this, ListaAnimalActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaAnimalActivity.this);
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

        animalList = pafContext.getFormularioCTR().animalList();

        ArrayList<String> itens = new ArrayList<String>();

        for(AnimalBean animalBean : animalList){
            itens.add(animalBean.getNomeAnimal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        animalListView = (ListView) findViewById(R.id.listaAnimal);
        animalListView.setAdapter(adapterList);

        animalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                AnimalBean animalBean = (AnimalBean) animalList.get(position);
                animalList.clear();

                pafContext.getFormularioCTR().setIdAnimalForm(animalBean.getIdAnimal());
                pafContext.getFormularioCTR().setNomeAnimalForm("");

                if(pafContext.getFormularioCTR().verFormularioIniciado()) {
                    Intent it = new Intent(ListaAnimalActivity.this, VistoriadorActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(ListaAnimalActivity.this, ListaInformacaoActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonRetAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pafContext.getFormularioCTR().verFormularioIniciado()){
                    Intent it = new Intent(ListaAnimalActivity.this, FotoActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(ListaAnimalActivity.this, ListaInformacaoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonDigitarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaAnimalActivity.this, ListaInformacaoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}