package br.com.usinasantafe.paf.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.paf.model.bean.estaticas.AnimalBean;
import br.com.usinasantafe.paf.model.bean.variaveis.FormularioBean;
import br.com.usinasantafe.paf.model.pst.EspecificaPesquisa;

public class AnimalDAO {

    public AnimalDAO() {
    }

    public List<AnimalBean> animalList(){
        AnimalBean animalBean = new AnimalBean();
        return animalBean.orderBy("nomeAnimal", true);
    }

    public AnimalBean getAnimalId(Long idAnimal) {
        List<AnimalBean> animalList = animalIdList(idAnimal);
        AnimalBean animalBean = animalList.get(0);
        animalList.clear();
        return animalBean;
    }

    private List<AnimalBean> animalIdList(Long idAnimal) {
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdAnimal(idAnimal));
        AnimalBean animalBean = new AnimalBean();
        return animalBean.get(pesqArrayList);
    }

    private EspecificaPesquisa getPesqIdAnimal(Long idAnimal){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idAnimal");
        pesquisa.setValor(idAnimal);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
