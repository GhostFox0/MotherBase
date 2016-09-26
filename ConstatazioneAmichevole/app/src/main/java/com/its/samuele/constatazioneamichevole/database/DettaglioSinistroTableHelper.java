package com.its.samuele.constatazioneamichevole.database;

import android.provider.BaseColumns;

/**
 * Created by Utente on 23/09/2016.
 */

public class DettaglioSinistroTableHelper implements BaseColumns {


    private static final String TABLE_NAME = "dettagliosinistro";
    private static final String ID = "id";
    private static final String NOME_UTENTE = "nome_utente";
    private static final String COGNOME_UTENTE = "cognome_utente";
    private static final String CODICE_FISCALE = "codice_fiscale";
    private static final String CAP = "cap";
    private static final String CONTATTI = "contatti";
    private static final String INDIRIZZO = "indirizzo";
    private static final String DATA_INCIDENTE = "data_incidente";
    private static final String TIME = "time";
    private static final String LUOGO_INCIDENTE = "luogo_incidente";
    private static final String FERITI = "feriti";
    private static final String ALTRI_VEICOLI = "altri_veicoli";
    private static final String ALTRI_OGGETTI = "altri_oggetti";

    private static final String CREATE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME
            + " ("+ID +" TEXT, "
            +NOME_UTENTE+" TEXT, "
            + COGNOME_UTENTE+" TEXT, "
            + CODICE_FISCALE+" TEXT, "
            + CAP+" TEXT, "
            +CONTATTI+ " TEXT, "
            +INDIRIZZO+" TEXT, "
            + DATA_INCIDENTE+" DATE, "
            +TIME+" TEXT, "
            +LUOGO_INCIDENTE+" TEXT, "
            +FERITI+" INTEGER, "
            +ALTRI_VEICOLI+" INTEGER, "
            +ALTRI_OGGETTI+" INTEGER, FOREIGN KEY (id) references sinistri("+_ID+"));";
}
