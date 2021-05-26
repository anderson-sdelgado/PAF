package br.com.usinasantafe.paf.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.paf.model.bean.estaticas.AnimalBean;
import br.com.usinasantafe.paf.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.paf.model.bean.variaveis.DadosEnvioBean;
import br.com.usinasantafe.paf.model.bean.variaveis.FormularioBean;
import br.com.usinasantafe.paf.model.bean.variaveis.FotoBean;
import br.com.usinasantafe.paf.model.dao.AnimalDAO;
import br.com.usinasantafe.paf.model.dao.ColabDAO;
import br.com.usinasantafe.paf.model.dao.FormularioDAO;
import br.com.usinasantafe.paf.model.dao.FotoDAO;
import br.com.usinasantafe.paf.util.AtualDadosServ;

public class FormularioCTR {

    public FormularioCTR() {
    }

    //////////////////////////////FORMULÁRIO ///////////////////////////////////////////////////////

    public void salvarCabecIniciado(){
        FormularioDAO formularioDAO = new FormularioDAO();
        ConfigCTR configCTR = new ConfigCTR();
        formularioDAO.salvarCabecIniciado(configCTR.getConfig().getNroAparelhoConfig());
    }

    public boolean verFormularioIniciado(){
        FormularioDAO formularioDAO = new FormularioDAO();
        return formularioDAO.verFormularioIniciado();
    }

    public boolean verFormularioAberto(){
        FormularioDAO formularioDAO = new FormularioDAO();
        return formularioDAO.verFormularioAberto();
    }

    public boolean verFormularioFechado(){
        FormularioDAO formularioDAO = new FormularioDAO();
        return formularioDAO.verFormularioFechado();
    }

    public void delFormIniciado(){
        FormularioDAO formularioDAO = new FormularioDAO();
        delForm(formularioDAO.getFormularioIniciado());
    }

    public void delFormAberto(){
        FormularioDAO formularioDAO = new FormularioDAO();
        delForm(formularioDAO.getFormularioAberto());
    }

    public void delFormFechado() {
        FormularioDAO formularioDAO = new FormularioDAO();
        delForm(formularioDAO.formularioFechadoList());
    }

    private void delForm(List<FormularioBean> formularioList) {
        for (FormularioBean formularioBean : formularioList) {
            delForm(formularioBean);
        }
    }

    private void delForm(FormularioBean formularioBean){
        FotoDAO fotoDAO = new FotoDAO();
        fotoDAO.delFotoIdForm(formularioBean.getIdForm());

        formularioBean.delete();
    }

    public void abrirForm(){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.abrirForm();
    }

    public void fecharForm(){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.fecharForm();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////set Formulário////////////////////////////////////////////////

    public void setDataForm(String dataForm){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.setDataForm(dataForm);
    }

    public void setIdAnimalForm(Long idAnimalForm){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.setIdAnimalForm(idAnimalForm);
    }

    public void setNomeAnimalForm(String nomeAnimalForm){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.setNomeAnimalForm(nomeAnimalForm);
    }

    public void setMatricVistoriadorForm(Long matricVistoriadorForm){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.setMatricVistoriadorForm(matricVistoriadorForm);
    }

    public void setDescrLocalForm(String descrLocalForm){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.setDescrLocalForm(descrLocalForm);
    }

    public void setLatLongForm(Double latitude, Double longitude){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.setLatLongForm(latitude, longitude);
    }

    public void setObservacaoForm(String observacao){
        FormularioDAO formularioDAO = new FormularioDAO();
        formularioDAO.setObservacaoForm(observacao);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////get Cabecalho////////////////////////////////////////////////
//
//    public CabecBean getCabecFechado(){
//        CabecDAO cabecDAO = new CabecDAO();
//        return cabecDAO.getCabecFechado();
//    }
//
    public FormularioBean getFormularioAberto(){
        FormularioDAO formularioDAO = new FormularioDAO();
        return formularioDAO.getFormularioAberto();
    }

    public FormularioBean getFormularioIniciado(){
        FormularioDAO formularioDAO = new FormularioDAO();
        return formularioDAO.getFormularioIniciado();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////COLAB ////////////////////////////////////////////////

    public boolean hasElemColab(){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.hasElements();
    }

    public boolean verColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.verColab(matricColab);
    }

    public ColabBean getMatricColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getMatricColab(matricColab);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////ANIMAL ////////////////////////////////////////////////

    public List<AnimalBean> animalList(){
        AnimalDAO animalDAO = new AnimalDAO();
        return animalDAO.animalList();
    }

    public AnimalBean getAnimalId(Long idAnimal) {
        AnimalDAO animalDAO = new AnimalDAO();
        return animalDAO.getAnimalId(idAnimal);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////Dados para Envio///////////////////////////////////////////////////

    public DadosEnvioBean dadosFormularioEnvio() {

        DadosEnvioBean dadosEnvioBean = new DadosEnvioBean();

        FormularioDAO formularioDAO = new FormularioDAO();
        List<FormularioBean> formularioList = formularioDAO.formularioFechadoList();
        JsonArray formularioJsonArray = new JsonArray();
        JsonArray fotoJsonArray = new JsonArray();

        for (FormularioBean formularioBean : formularioList) {

            Gson gsonCabec = new Gson();
            formularioJsonArray.add(gsonCabec.toJsonTree(formularioBean, formularioBean.getClass()));

            FotoDAO fotoDAO = new FotoDAO();
            fotoJsonArray = fotoDAO.dadosEnvioFoto(formularioBean.getIdForm());

        }

        JsonObject cabecJsonObj = new JsonObject();
        cabecJsonObj.add("cabec", formularioJsonArray);
        dadosEnvioBean.setFormulario(cabecJsonObj.toString());

        JsonObject fotoJsonObj = new JsonObject();
        fotoJsonObj.add("foto", fotoJsonArray);
        dadosEnvioBean.setFoto(fotoJsonObj.toString());

        return dadosEnvioBean;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS POR SERVIDOR /////////////////////

    public void atualDadosColab(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList colabArrayList = new ArrayList();
        colabArrayList.add("ColabBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, colabArrayList);
    }

    public void atualDadosAnimal(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList animalArrayList = new ArrayList();
        animalArrayList.add("AnimalBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, animalArrayList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////// MANIPULAÇÃO FOTO ///////////////////////////////////////////////

    public FotoBean salvarFoto(Bitmap bitmap){
        FotoDAO fotoDAO = new FotoDAO();
        FormularioDAO formularioDAO = new FormularioDAO();
        return fotoDAO.salvarFoto(formularioDAO.getFormularioIniciado().getIdForm(), bitmap);
    }

    public List<FotoBean> fotoFormIniciado(){
        FotoDAO fotoAbordDAO = new FotoDAO();
        FormularioDAO formularioDAO = new FormularioDAO();
        return fotoAbordDAO.fotoCabecList(formularioDAO.getFormularioIniciado().getIdForm());
    }

    public List<FotoBean> fotoFormAberto(){
        FotoDAO fotoAbordDAO = new FotoDAO();
        FormularioDAO formularioDAO = new FormularioDAO();
        return fotoAbordDAO.fotoCabecList(formularioDAO.getFormularioAberto().getIdForm());
    }

    public Bitmap getStringBitmap(String foto) {
        FotoDAO fotoDAO = new FotoDAO();
        return fotoDAO.getStringBitmap(foto);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
