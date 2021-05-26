package br.com.usinasantafe.paf.util;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Tempo {

    private static Tempo instance = null;
    private boolean envioDado;
	
	public Tempo() {

	}
	
    public static Tempo getInstance() {
        if (instance == null)
        instance = new Tempo();
        return instance;
    }

    public String dataComHora(){

        String dataCerta = "";

        TimeZone tz = TimeZone.getDefault();
        Date dataHora = new Date();
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        Long dt =  dataHora.getTime() - tz.getOffset(d.getTime());
        cal.setTimeInMillis(dt);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        String horasStr = "";
        if(horas < 10){
            horasStr = "0" + horas;
        }
        else{
            horasStr = String.valueOf(horas);
        }

        String minutosStr = "";
        if(minutos < 10){
            minutosStr = "0" + minutos;
        }
        else{
            minutosStr = String.valueOf(minutos);
        }

        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano+" "+horasStr+":"+minutosStr;

        return dataCerta;

    }

    public String dataSHora(){

        String dataCerta = "";

        TimeZone tz = TimeZone.getDefault();
        Date dataHora = new Date();
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        Long dt =  dataHora.getTime() - tz.getOffset(d.getTime());
        cal.setTimeInMillis(dt);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano;

        return dataCerta;

    }

    public String dataComHoraCTZ(String data){

        try{

            StringBuffer dtServ = new StringBuffer(data);

            Log.i("PMM", "DATA HORA: " + dtServ);

            dtServ.delete(10, 11);
            dtServ.insert(10, " ");

            String dtStr = String.valueOf(dtServ);

            String diaStr = dtStr.substring(0, 2);
            String mesStr = dtStr.substring(3, 5);
            String anoStr = dtStr.substring(6, 10);
            String horaStr = dtStr.substring(11, 13);
            String minutoStr = dtStr.substring(14, 16);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            Date date = cal.getTime();

            TimeZone tz = TimeZone.getDefault();
            Date d = new Date();
            Long dt =  date.getTime() + tz.getOffset(d.getTime());
            cal.setTimeInMillis(dt);

            int mes = cal.get(Calendar.MONTH);
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int ano = cal.get(Calendar.YEAR);
            int horas = cal.get(Calendar.HOUR_OF_DAY);
            int minutos = cal.get(Calendar.MINUTE);
            mes = mes + 1;

            if(mes < 10){
                mesStr = "0" + mes;
            }
            else{
                mesStr = String.valueOf(mes);
            }

            if(dia < 10){
                diaStr = "0" + dia;
            }
            else{
                diaStr = String.valueOf(dia);
            }

            String horasStr = "";
            if(horas < 10){
                horasStr = "0" + horas;
            }
            else{
                horasStr = String.valueOf(horas);
            }

            String minutosStr = "";
            if(minutos < 10){
                minutosStr = "0" + minutos;
            }
            else{
                minutosStr = String.valueOf(minutos);
            }

            data = ""+diaStr+"/"+mesStr+"/"+ano+" "+horasStr+":"+minutosStr;

        }
        catch (Exception e) {
            Log.i("PCQ", "Erro Manip = " + e);
        }

        return data;

    }

    public String dataSHoraComTZ(){

        String dataCerta = "";

        Date dataHora = new Date();
        Calendar cal = Calendar.getInstance();
        Long dt =  dataHora.getTime();
        cal.setTimeInMillis(dt);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano;

        return dataCerta;

    }

    public boolean verDthrServ(String dthrServ){

        StringBuffer dtServ = new StringBuffer(dthrServ);

        Log.i("PMM", "DATA HORA SERVIDOR: " + dtServ);

        dtServ.delete(10, 11);
        dtServ.insert(10, " ");

        String dtStr = String.valueOf(dtServ);

        String diaStr = dtStr.substring(0, 2);
        String mesStr = dtStr.substring(3, 5);
        String anoStr = dtStr.substring(6, 10);
        String horaStr = dtStr.substring(11, 13);
        String minutoStr = dtStr.substring(14, 16);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
        cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
        cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
        cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

        Date dataHoraServ = cal.getTime();
        Long longDtServ =  dataHoraServ.getTime();

        Date dataHoraCel = new Date();
        Long longDtCel =  dataHoraCel.getTime();

        Long dthrDif =  longDtServ - longDtCel;
        Long diaDif = dthrDif/24/60/60/1000;

        Log.i("PMM", "DIAS DIFERENCA = " + diaDif);

        if((diaDif >= 0) && (diaDif <= 15)){
            return true;
        }
        else{
            return false;
        }

    }

    public Long difDthr(int dia, int mes, int ano, int hora, int minuto){

        String diaStr;
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        String mesStr;
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String anoStr = String.valueOf(ano);

        String horaStr = "";
        if(hora < 10){
            horaStr = "0" + hora;
        }
        else{
            horaStr = String.valueOf(hora);
        }

        String minutoStr = "";
        if(minuto < 10){
            minutoStr = "0" + minuto;
        }
        else{
            minutoStr = String.valueOf(minuto);
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
        cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
        cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
        cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

        Date dataHoraDig = cal.getTime();
        Long longDtDig =  dataHoraDig.getTime();

        Date dataHoraCel = new Date();
        Long longDtCel =  dataHoraCel.getTime();

        Long dif = longDtDig - longDtCel;

        return dif;

    }

    public boolean isEnvioDado() {
        return envioDado;
    }

    public void setEnvioDado(boolean envioDado) {
        this.envioDado = envioDado;
    }
}
