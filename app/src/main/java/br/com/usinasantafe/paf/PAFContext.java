package br.com.usinasantafe.paf;

import android.app.Application;

import br.com.usinasantafe.paf.control.ConfigCTR;
import br.com.usinasantafe.paf.control.FormularioCTR;

public class PAFContext extends Application {

    public static String versaoAplic = "1.00";
    private FormularioCTR formularioCTR;
    private ConfigCTR configCTR;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public FormularioCTR getFormularioCTR(){
        if (formularioCTR == null)
            formularioCTR = new FormularioCTR();
        return formularioCTR;
    }

    public ConfigCTR getConfigCTR() {
        if(configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

}
