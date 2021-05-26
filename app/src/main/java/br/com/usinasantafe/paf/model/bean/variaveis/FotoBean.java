package br.com.usinasantafe.paf.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.paf.model.pst.Entidade;

@DatabaseTable(tableName="tbfotovar")
public class FotoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idFoto;
    @DatabaseField
    private Long idForm;
    @DatabaseField
    private String foto;
    @DatabaseField
    private String dthrFoto;

    public FotoBean() {
    }

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public Long getIdForm() {
        return idForm;
    }

    public void setIdForm(Long idForm) {
        this.idForm = idForm;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDthrFoto() {
        return dthrFoto;
    }

    public void setDthrFoto(String dthrFoto) {
        this.dthrFoto = dthrFoto;
    }
}
