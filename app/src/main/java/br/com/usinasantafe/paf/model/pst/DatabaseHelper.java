package br.com.usinasantafe.paf.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.paf.model.bean.estaticas.AnimalBean;
import br.com.usinasantafe.paf.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.paf.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.paf.model.bean.variaveis.FormularioBean;
import br.com.usinasantafe.paf.model.bean.variaveis.FotoBean;
import br.com.usinasantafe.paf.model.bean.variaveis.LogErroBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "paf_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;

	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);
		instance = this;
	}

	@Override
	public void close() {
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			TableUtils.createTable(cs, AnimalBean.class);
			TableUtils.createTable(cs, ColabBean.class);

			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, FormularioBean.class);
			TableUtils.createTable(cs, FotoBean.class);
			TableUtils.createTable(cs, LogErroBean.class);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
//				TableUtils.createTable(cs, ConfigBean.class);
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
