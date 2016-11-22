package com.its.pretto.samuele.marketplace.database;

import android.provider.BaseColumns;

/**
 * Created by Samuele.Pretto on 15/11/2016.
 */

public class AdTableHelper implements BaseColumns {

    public static final String TABLE_NAME = "ads";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String PRICE = "price";
    public static final String DATE = "date";
    public static final String COMUNE = "comune";
    public static final String PROVINCIA = "provincia";
    public static final String REGIONE = "regione";
    public static final String MAIL = "mail";
    public static final String CATEGORIE_ID = "categorie_id";

    public static final String CREATE = "CREATE TABLE "+TABLE_NAME
            +" ( "+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TITLE+" TEXT, "
            +DESCRIPTION+" TEXT, "
            +IMAGE+" TEXT, "
            +PRICE+" TEXT, "
            +DATE+" TEXT, "
            +COMUNE+" TEXT, "
            +PROVINCIA+" TEXT, "
            +REGIONE+" TEXT, "
            +MAIL+" TEXT, "
            +CATEGORIE_ID+" INT);";
}
