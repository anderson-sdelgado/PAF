package br.com.usinasantafe.paf.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.paf.model.pst.Entidade;

@DatabaseTable(tableName="tbformulariovar")
public class FormularioBean extends Entidade {


    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idForm;
    @DatabaseField
    private String dthrForm;
    @DatabaseField
    private String dataInsForm;
    @DatabaseField
    private Long nroAparelhoForm;
    @DatabaseField
    private Double latitudeForm;
    @DatabaseField
    private Double longitudeForm;
    @DatabaseField
    private String descrLocalForm;
    @DatabaseField
    private Long idAnimal;
    @DatabaseField
    private String nomeAnimal;
    @DatabaseField
    private Long matricVistoriador;
    @DatabaseField
    private String observacao;
    @DatabaseField
    private Long statusForm;

    public FormularioBean() {
    }

    public Long getIdForm() {
        return idForm;
    }

    public void setIdForm(Long idForm) {
        this.idForm = idForm;
    }

    public String getDthrForm() {
        return dthrForm;
    }

    public void setDthrForm(String dthrForm) {
        this.dthrForm = dthrForm;
    }

    public String getDataInsForm() {
        return dataInsForm;
    }

    public void setDataInsForm(String dataInsForm) {
        this.dataInsForm = dataInsForm;
    }

    public Long getNroAparelhoForm() {
        return nroAparelhoForm;
    }

    public void setNroAparelhoForm(Long nroAparelhoForm) {
        this.nroAparelhoForm = nroAparelhoForm;
    }

    public Double getLatitudeForm() {
        return latitudeForm;
    }

    public void setLatitudeForm(Double latitudeForm) {
        this.latitudeForm = latitudeForm;
    }

    public Double getLongitudeForm() {
        return longitudeForm;
    }

    public void setLongitudeForm(Double longitudeForm) {
        this.longitudeForm = longitudeForm;
    }

    public String getDescrLocalForm() {
        return descrLocalForm;
    }

    public void setDescrLocalForm(String descrLocalForm) {
        this.descrLocalForm = descrLocalForm;
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public Long getMatricVistoriador() {
        return matricVistoriador;
    }

    public void setMatricVistoriador(Long matricVistoriador) {
        this.matricVistoriador = matricVistoriador;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getStatusForm() {
        return statusForm;
    }

    public void setStatusForm(Long statusForm) {
        this.statusForm = statusForm;
    }
}
