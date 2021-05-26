package br.com.usinasantafe.paf.model.bean;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualAplicBean {

    private Long nroAparelhoAtual;
    private String versaoAtual;
    private String versaoNova;
    private Long flagAtualApp;
    private Long flagLogEnvio;
    private Long flagLogErro;
    private String dthr;

    public AtualAplicBean() {
    }

    public Long getNroAparelhoAtual() {
        return nroAparelhoAtual;
    }

    public void setNroAparelhoAtual(Long nroAparelhoAtual) {
        this.nroAparelhoAtual = nroAparelhoAtual;
    }

    public String getVersaoAtual() {
        return versaoAtual;
    }

    public void setVersaoAtual(String versaoAtual) {
        this.versaoAtual = versaoAtual;
    }

    public String getVersaoNova() {
        return versaoNova;
    }

    public void setVersaoNova(String versaoNova) {
        this.versaoNova = versaoNova;
    }

    public Long getFlagAtualApp() {
        return flagAtualApp;
    }

    public void setFlagAtualApp(Long flagAtualApp) {
        this.flagAtualApp = flagAtualApp;
    }

    public Long getFlagLogEnvio() {
        return flagLogEnvio;
    }

    public void setFlagLogEnvio(Long flagLogEnvio) {
        this.flagLogEnvio = flagLogEnvio;
    }

    public Long getFlagLogErro() {
        return flagLogErro;
    }

    public void setFlagLogErro(Long flagLogErro) {
        this.flagLogErro = flagLogErro;
    }

    public String getDthr() {
        return dthr;
    }

    public void setDthr(String dthr) {
        this.dthr = dthr;
    }
}
