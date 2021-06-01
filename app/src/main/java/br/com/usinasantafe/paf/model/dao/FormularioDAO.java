package br.com.usinasantafe.paf.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.paf.model.bean.variaveis.FormularioBean;
import br.com.usinasantafe.paf.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.paf.util.Tempo;

public class FormularioDAO {

    public FormularioDAO() {
    }

    public void salvarCabecIniciado(Long nroAparelho){
        FormularioBean formularioBean = new FormularioBean();
        formularioBean.setDthrForm(Tempo.getInstance().dataComHora());
        formularioBean.setDataInsForm("null");
        formularioBean.setNroAparelhoForm(nroAparelho);
        formularioBean.setLatitudeForm(0D);
        formularioBean.setLongitudeForm(0D);
        formularioBean.setDescrLocalForm("null");
        formularioBean.setIdAnimal(0L);
        formularioBean.setNomeAnimal("null");
        formularioBean.setMatricVistoriador(0L);
        formularioBean.setObservacao("null");
        formularioBean.setStatusForm(0L);
        formularioBean.insert();
    }

    public void setDataForm(String dataForm){
        FormularioBean formularioBean;
        if(verFormularioIniciado()){
            formularioBean = getFormularioIniciado();
        }
        else{
            formularioBean = getFormularioAberto();
        }
        formularioBean.setDataInsForm(dataForm);
        formularioBean.update();
    }

    public void setIdAnimalForm(Long idAnimalForm){
        FormularioBean formularioBean;
        if(verFormularioIniciado()){
            formularioBean = getFormularioIniciado();
        }
        else{
            formularioBean = getFormularioAberto();
        }
        formularioBean.setIdAnimal(idAnimalForm);
        formularioBean.update();
    }

    public void setNomeAnimalForm(String nomeAnimalForm){
        FormularioBean formularioBean;
        if(verFormularioIniciado()){
            formularioBean = getFormularioIniciado();
        }
        else{
            formularioBean = getFormularioAberto();
        }
        formularioBean.setNomeAnimal(nomeAnimalForm);
        formularioBean.update();
    }

    public void setMatricVistoriadorForm(Long matricVistoriadorForm){
        FormularioBean formularioBean;
        if(verFormularioIniciado()){
            formularioBean = getFormularioIniciado();
        }
        else{
            formularioBean = getFormularioAberto();
        }
        formularioBean.setMatricVistoriador(matricVistoriadorForm);
        formularioBean.update();
    }

    public void setDescrLocalForm(String descrLocalForm){
        FormularioBean formularioBean;
        if(verFormularioIniciado()){
            formularioBean = getFormularioIniciado();
        }
        else{
            formularioBean = getFormularioAberto();
        }
        formularioBean.setDescrLocalForm(descrLocalForm);
        formularioBean.update();
    }

    public void setLatLongForm(Double latitude, Double longitude){
        FormularioBean formularioBean;
        if(verFormularioIniciado()){
            formularioBean = getFormularioIniciado();
        }
        else{
            formularioBean = getFormularioAberto();
        }
        formularioBean.setLatitudeForm(latitude);
        formularioBean.setLongitudeForm(longitude);
        formularioBean.update();
    }

    public void setObservacaoForm(String observacao){
        FormularioBean formularioBean;
        if(verFormularioIniciado()){
            formularioBean = getFormularioIniciado();
        }
        else{
            formularioBean = getFormularioAberto();
        }
        formularioBean.setObservacao(observacao);
        formularioBean.update();
    }

    public void fecharForm(){
        FormularioBean formularioBean = getFormularioAberto();
        formularioBean.setStatusForm(2L);
        formularioBean.update();
    }

    public void abrirForm(){
        FormularioBean formularioBean = getFormularioIniciado();
        formularioBean.setStatusForm(1L);
        formularioBean.update();
    }

    public boolean verFormularioIniciado(){
        List<FormularioBean> formularioList = formularioIniciadoList();
        boolean ret = formularioList.size() > 0;
        formularioList.clear();
        return ret;
    }

    public boolean verFormularioAberto(){
        List<FormularioBean> formularioList = formularioAbertoList();
        boolean ret = formularioList.size() > 0;
        formularioList.clear();
        return ret;
    }

    public boolean verFormularioFechado(){
        List<FormularioBean> formularioList = formularioFechadoList();
        boolean ret = formularioList.size() > 0;
        formularioList.clear();
        return ret;
    }

    public FormularioBean getFormularioIniciado(){
        List<FormularioBean> formularioList = formularioIniciadoList();
        FormularioBean formularioBean = formularioList.get(0);
        formularioList.clear();
        return formularioBean;
    }

    public FormularioBean getFormularioAberto(){
        List<FormularioBean> formularioList = formularioAbertoList();
        FormularioBean formularioBean = formularioList.get(0);
        formularioList.clear();
        return formularioBean;
    }

    private List<FormularioBean> formularioIniciadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusIniciado());
        FormularioBean formularioBean = new FormularioBean();
        return formularioBean.get(pesqArrayList);
    }

    private List<FormularioBean> formularioAbertoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusAberto());
        FormularioBean formularioBean = new FormularioBean();
        return formularioBean.get(pesqArrayList);
    }

    public List<FormularioBean> formularioFechadoList() {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqStatusFechado());
        FormularioBean formularioBean = new FormularioBean();
        return formularioBean.get(pesqArrayList);
    }

    private EspecificaPesquisa getPesqStatusIniciado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusForm");
        pesquisa.setValor(0L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusForm");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqStatusFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusForm");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
