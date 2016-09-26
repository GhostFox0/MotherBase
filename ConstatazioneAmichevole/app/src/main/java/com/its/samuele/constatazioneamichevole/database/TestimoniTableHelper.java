package com.its.samuele.constatazioneamichevole.database;

import android.content.pm.ProviderInfo;
import android.provider.BaseColumns;

/**
 * Created by Utente on 23/09/2016.
 */

public class TestimoniTableHelper implements BaseColumns {

    private static final String TABLE_NAME = "testimoni";
    private static final String ID ="id_testimone";
    private static final String NOME_TESTIMONE ="nome_testimone";
    private static final String COGNOME_TESTIMONE = "cognome_testimone";
    private static final String INDIRIZZO_TESTIMONE = "indirizzo_testimone";
    private static final String CONTATTI_TESTIMONE = "contatti_testimone";
    private static final String PASSEGERO_CONTRAENTE = "passegero_contraente";

    private static final String CREATE = "CREATE TABLE IF NOT EXISTS"+ TABLE_NAME +" ("
            +ID+" INTEGER PRIMARY KKEY, "
            +NOME_TESTIMONE+" TEXT, "
            +COGNOME_TESTIMONE+" TEXT, "
            +INDIRIZZO_TESTIMONE+" TEXT, "
            +CONTATTI_TESTIMONE+" TEXT, "
            +PASSEGERO_CONTRAENTE+" TEXT, FOREIGN KEY (id_testimone) references sinistri("+_ID+"));";

}
