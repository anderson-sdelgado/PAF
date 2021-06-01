package br.com.usinasantafe.paf.util.connHttp;

import br.com.usinasantafe.paf.PAFContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/paf/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/paf/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.paf.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.paf.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PAFContext.versaoAplic.replace(".", "_");

    public static String AnimalBean = urlPrincipal + "animal.php" + put;
    public static String ColabBean = urlPrincipal + "colab.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirFormulario() {
        return urlPrincEnvio + "inserirformulario.php" + put;
    }

    public String getsInsertLogErro() {
        return urlPrincEnvio + "inserirlogerro.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php" + put;
        }
        return retorno;
    }

}
