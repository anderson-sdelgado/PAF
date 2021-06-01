package br.com.usinasantafe.paf.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import br.com.usinasantafe.paf.control.ConfigCTR;
import br.com.usinasantafe.paf.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.paf.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.paf.util.Tempo;

public class LogErroDAO {

    private static LogErroDAO instance = null;

    public static LogErroDAO getInstance() {
        if (instance == null)
            instance = new LogErroDAO();
        return instance;
    }

    public void insert(Throwable ex){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElements()){
            if(configCTR.getConfig().getFlagLogErro().equals(1L)) {
                ConfigBean configBean = configCTR.getConfig();
                LogErroBean logErroBean = new LogErroBean();
                logErroBean.setNroAparelho(configBean.getNroAparelhoConfig());
                logErroBean.setException(throwableToString(ex));
                logErroBean.setDthr(Tempo.getInstance().dataComHora());
                logErroBean.setStatus(1L);
                logErroBean.insert();
            }
        }
    }

    public void insert(String erro){
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElements()){
            if(configCTR.getConfig().getFlagLogErro().equals(1L)) {
                ConfigBean configBean = configCTR.getConfig();
                LogErroBean logErroBean = new LogErroBean();
                logErroBean.setNroAparelho(configBean.getNroAparelhoConfig());
                logErroBean.setException("RETORNO SERVIDOR COM FALHA = " + erro);
                logErroBean.setDthr(Tempo.getInstance().dataComHora());
                logErroBean.setStatus(1L);
                logErroBean.insert();
            }
        }
    }

    private String throwableToString(Throwable t) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, false);

        t.printStackTrace(printWriter);

        printWriter.flush();
        stringWriter.flush();

        return stringWriter.toString();

    }

    public boolean verEnvioLogErro(){
        List<LogErroBean> logErroList = logErroAbertList();
        boolean ret = logErroList.size() > 0;
        logErroList.clear();
        return ret;
    }

    public List<LogErroBean> logErroAbertList(){
        LogErroBean logErroBean = new LogErroBean();
        return logErroBean.get("status", 1L);
    }

    public List<LogErroBean> logErroFechList(){
        LogErroBean logErroBean = new LogErroBean();
        return logErroBean.get("status", 2L);
    }

    public String dadosEnvio(){

        JsonArray jsonArrayLogErro = new JsonArray();

        List<LogErroBean> logErroList = logErroAbertList();
        for (int i = 0; i < logErroList.size(); i++) {

            LogErroBean logErroBean = logErroList.get(i);
            Gson gson = new Gson();
            jsonArrayLogErro.add(gson.toJsonTree(logErroBean, logErroBean.getClass()));

        }

        logErroList.clear();

        JsonObject jsonLogErro = new JsonObject();
        jsonLogErro.add("logerro", jsonArrayLogErro);

        return jsonLogErro.toString();

    }

    public void updLogErro(String retorno){

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjLogErro = new JSONObject(objPrinc);
            JSONArray jsonArrayLogErro = jObjLogErro.getJSONArray("logerro");

            for (int i = 0; i < jsonArrayLogErro.length(); i++) {

                JSONObject objLogErro = jsonArrayLogErro.getJSONObject(i);
                Gson gsonBol = new Gson();
                LogErroBean logErroBean = gsonBol.fromJson(objLogErro.toString(), LogErroBean.class);

                List<LogErroBean> logErroList = logErroBean.get("idLog", logErroBean.getIdLog());
                LogErroBean logErroBD = logErroList.get(0);
                logErroBD.setStatus(2L);
                logErroBD.update();

                logErroList.clear();

            }

            delLogErroFechado();

        }
        catch(Exception e){
            LogErroDAO.getInstance().insert(e);
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public void delLogErroFechado(){
        List<LogErroBean> logErroList = logErroFechList();
        for(LogErroBean logErroBean : logErroList){
            logErroBean.delete();
        }
        logErroList.clear();
    }

}
