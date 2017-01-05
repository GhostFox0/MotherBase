package inc.sam.farmalista;

import android.provider.BaseColumns;

/**
 * Created by sam on 05/01/17.
 */

public class MedicineTableHelper implements BaseColumns {

    public static final String TABLE_NAME ="medicine";
    public static final String NAME ="name";
    public static final String QUANTITA = "quantita";
    public static final String ID_UTENTE= "id_utente";
    public static final String DOSAGGIO = "dosaggio";
    public static final String DATA_INIZIO = "data_inizio";
    public static final String DATA_SCADENZA = "data_scadenza";
    public static final String GIORNI_PREAVVISO = "giorni_preavviso";
    public static final String NUMERO_CONFEZIONI = "numero_confezioni";

    public static final String CREATE = "CREATE TABLE "+TABLE_NAME+" ( "
            +_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "
            +NAME+" TEXT , "
            +QUANTITA+" TEXT , "
            +DOSAGGIO+" TEXT , "
            +DATA_INIZIO+" TEXT , "
            +DATA_SCADENZA+" TEXT , "
            +GIORNI_PREAVVISO+" TEXT , "
            +NUMERO_CONFEZIONI+" TEXT , "
            +ID_UTENTE+" INTEGER, FOREIGN KEY("+ID_UTENTE+") REFERENCES "+UserTableHelper.TABLE_NAME+"("+UserTableHelper._ID+") );";


}
