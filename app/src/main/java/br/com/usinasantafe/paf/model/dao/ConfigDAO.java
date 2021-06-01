package br.com.usinasantafe.paf.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.paf.model.bean.AtualAplicBean;
import br.com.usinasantafe.paf.model.bean.variaveis.ConfigBean;


public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        List<ConfigBean> configList = configList();
        ConfigBean configBean = configList.get(0);
        configList.clear();
        return configBean;
    }

    public boolean getConfig(String senha){
        List<ConfigBean> configList = configList(senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    private List<ConfigBean> configList(){
        ConfigBean configBean = new ConfigBean();
        return configBean.all();
    }

    private List<ConfigBean> configList(String senha){
        ConfigBean configBean = new ConfigBean();
        return configBean.get("senhaConfig", senha);
    }

    public void salvarConfig(Long nroAparelho){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setNroAparelhoConfig(nroAparelho);
        configBean.setFlagLogErro(0L);
        configBean.setFlagLogEnvio(0L);
        configBean.setDthrServConfig("");
        configBean.insert();
        configBean.commit();
    }

    public AtualAplicBean recAtual(String result) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                atualAplicBean = gson.fromJson(objeto.toString(), AtualAplicBean.class);

                ConfigBean configBean = getConfig();
                configBean.setFlagLogEnvio(atualAplicBean.getFlagLogEnvio());
                configBean.setFlagLogErro(atualAplicBean.getFlagLogErro());
                configBean.setDthrServConfig(atualAplicBean.getDthr());
                configBean.update();

            }

        } catch (Exception e) {
            LogErroDAO.getInstance().insert(e);
        }

        return atualAplicBean;

    }

}
