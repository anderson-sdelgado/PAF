package br.com.usinasantafe.paf.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.paf.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.paf.model.dao.ConfigDAO;
import br.com.usinasantafe.paf.model.dao.LogErroDAO;
import br.com.usinasantafe.paf.util.AtualDadosServ;

public class ConfigCTR {


    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public boolean getConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig(senha);
    }

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void salvarConfig(Long nroAparelho){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(nroAparelho);
    }

//    public boolean verEnvioLogErro(){
//        LogErroDAO logErroDAO = new LogErroDAO();
//        return logErroDAO.verEnvioLogErro();
//    }
//
//    public String dadosEnvioLogErro(){
//        LogErroDAO logErroDAO = new LogErroDAO();
//        return logErroDAO.dadosEnvio();
//    }
//
//    public void updLogErro(String retorno){
//        LogErroDAO logErroDAO = new LogErroDAO();
//        logErroDAO.updLogErro(retorno);
//    }


}
