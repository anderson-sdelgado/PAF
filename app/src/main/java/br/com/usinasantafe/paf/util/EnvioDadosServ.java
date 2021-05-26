package br.com.usinasantafe.paf.util;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.paf.control.ConfigCTR;
import br.com.usinasantafe.paf.control.FormularioCTR;
import br.com.usinasantafe.paf.model.bean.variaveis.DadosEnvioBean;
import br.com.usinasantafe.paf.util.connHttp.MultipartGenerico;
import br.com.usinasantafe.paf.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.paf.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void envioDados() {

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] dados = new String[3];

        FormularioCTR formularioCTR = new FormularioCTR();

        DadosEnvioBean dadosEnvioBean = formularioCTR.dadosFormularioEnvio();

        Log.i("PST", "FORMULARIO = " + dadosEnvioBean.getFormulario());
        Log.i("PST", "FOTO = " + dadosEnvioBean.getFoto());

        dados[0] = urlsConexaoHttp.getsInserirFormulario();
        dados[1] = dadosEnvioBean.getFormulario();
        dados[2] = dadosEnvioBean.getFoto();

        MultipartGenerico multipartGenerico = new MultipartGenerico();
        multipartGenerico.execute(dados);

    }

    public void envioLogErro() {

//        ConfigCTR configCTR = new ConfigCTR();
        String dados = "";//configCTR.dadosEnvioLogErro();

        Log.i("PMM", "LOG ERRO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertLogErro()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifLogErro() {
//        ConfigCTR configCTR = new ConfigCTR();
//        return configCTR.verEnvioLogErro();
        return true;
    }

    public Boolean verEnvioFormulario() {
        FormularioCTR formularioCTR = new FormularioCTR();
        return formularioCTR.verFormularioFechado();
    }


    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc(){

        if(verifLogErro()){
            envioLogErro();
        }
        else{
            if(verEnvioFormulario()){
                envioDados();
            }
        }

    }

    public boolean verifDadosEnvio() {
        if (!verEnvioFormulario()
            && !verifLogErro()){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

}