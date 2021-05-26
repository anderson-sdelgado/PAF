package br.com.usinasantafe.paf.model.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.paf.model.bean.variaveis.FotoBean;
import br.com.usinasantafe.paf.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.paf.util.Tempo;


public class FotoDAO {

    public FotoDAO() {
    }

    public FotoBean salvarFoto(Long idForm, Bitmap bitmap){
        FotoBean fotoBean = new FotoBean();
        fotoBean.setIdForm(idForm);
        fotoBean.setFoto(getBitmapString(bitmap));
        fotoBean.setDthrFoto(Tempo.getInstance().dataComHora());
        fotoBean.insert();
        return fotoBean;
    }

    public List<FotoBean> fotoCabecList(Long idForm){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdForm(idForm));
        FotoBean fotoBean = new FotoBean();
        return fotoBean.get(pesqArrayList);
    }

    private String getBitmapString(Bitmap foto){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        foto.compress(Bitmap.CompressFormat.JPEG, 95, stream);

        byte[] byteArray = stream.toByteArray();

        return(Base64.encodeToString(byteArray, Base64.DEFAULT));

    }

    public Bitmap getStringBitmap(String foto){

        try{
            byte [] encodeByte= Base64.decode(foto ,Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        }catch(Exception e){
            Log.i("PST", "Erro = " + e.toString());
            return null;
        }

    }

    public void delFotoIdForm(Long idForm){
        FotoBean fotoBean = new FotoBean();
        List<FotoBean> fotoList = fotoBean.get("idForm", idForm);
        for (FotoBean fotoBeanBD : fotoList) {
            fotoBeanBD.delete();
        }
    }

    public JsonArray dadosEnvioFoto(Long idForm){
        FotoBean fotoBean = new FotoBean();
        List<FotoBean> fotoList = fotoBean.get("idForm", idForm);
        JsonArray fotoJsonArray = new JsonArray();
        for (FotoBean fotoBeanBD : fotoList) {
            Gson cabecGson = new Gson();
            fotoJsonArray.add(cabecGson.toJsonTree(fotoBeanBD, fotoBeanBD.getClass()));
        }
        fotoList.clear();
        return fotoJsonArray;
    }

    private EspecificaPesquisa getPesqIdForm(Long idForm){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idForm");
        especificaPesquisa.setValor(idForm);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
