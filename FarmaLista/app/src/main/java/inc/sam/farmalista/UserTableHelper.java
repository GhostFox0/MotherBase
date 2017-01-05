package inc.sam.farmalista;

import android.provider.BaseColumns;

/**
 * Created by sam on 03/01/17.
 */

public class UserTableHelper implements BaseColumns {

    public static final String TABLE_NAME = "users";

    public static final String NAME ="name";

    public static final String SURNAME = "surname";

    public static final String EMAIL = "email";

    public static final String PASSWORD= "password";

    public static final String COMUNE_NASCITA = "comune_nascita";

    public static final String GENDER = "gender";

    public static final String FISCAL_CODE = "fiscal_code";

    public static final String BIRTH_DATE = "birth_date";

    public static final String ADDRESS = "address";

    public static final String CREATE = "CREATE TABLE "+TABLE_NAME+" ( "
            +_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NAME+" TEXT, "
            +SURNAME+" TEXT, "
            +EMAIL+" TEXT, "
            +PASSWORD+" TEXT, "
            +COMUNE_NASCITA+" TEXT, "
            +GENDER+" TEXT, "
            +FISCAL_CODE+" TEXT, "
            +BIRTH_DATE+" TEXT, "
            +ADDRESS+" TEXT);";
}
