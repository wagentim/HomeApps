package cn.wagentim.homeapps.utils;

public interface Constants
{
    public static final int ERROR_INT = -1;
    public static final Long ERROR_LONG = -1L;
    /** the Authentication will last one day */
    public static final Long AUTH_TIME_OUT = Long.valueOf(1000 * 60 * 60 * 24);

    // --------------- Template File Constants ---------------

    public static final String TEMPLAGE_SUFFIX = "tmp";
    public static final String TEMPLATE_LOCATION = "/templates/";

    // --------------- Language Constants ---------------

    public static final String LANG_TXT_PREFIX = "lang(";
    public static final String LANG_TXT_SUFFIX = ")";
    public static final String LANG_FILE_CHINESE = "i18n/chinese.lang";
    public static final String LANG_FILE_ENGLISH = "i18n/english.lang";

    // --------------- HTML Constants ---------------

    public static final String CONTENT_TYPE_HTML = "text/html";
    public static final String CONTENT_TYPE_TEXT = "text/text";
    public static final String CONTENT_CHAR_SET_UTF8 = "utf-8";

   //  --------------- Customer Constants ---------------
    public static final String CUSTOMER_ALISA = "alias";
    public static final String CUSTOMER_FIRST_NAME = "firstname";
    public static final String CUSTOMER_LAST_NAME = "lastname";
    public static final String CUSTOMER_TELEPHONE = "telephon";
    public static final String CUSTOMER_EMAIL = "email";
    public static final String CUSTOMER_COUNTRY = "country";
    public static final String CUSTOMER_PROVINCE = "province";
    public static final String CUSTOMER_CITY = "city";
    public static final String CUSTOMER_ZIPCODE = "zipcode";
    public static final String CUSTOMER_ADDRESS = "address";
	public static final String CUSTOMER_LIST = "customer_list";

	// 	--------------- Entity Constants ---------------
	public static final String PERSISTENCE_UNIT_NAME = "google";
	public static final int ENTITY_CUSTOMER = 0;

	//  --------------- Parser Constants ---------------
	public static final String OPERATION = "opt";
    public static final String AUTH = "auth";
    public static final String USER_NAME = "usr";
    public static final String PASSWORD = "pwd";
    public static final String ENTITY = "entity";
    public static final String ID = "id";
    
    //  --------------- Page Constants --------------- 
	public static final String PAGE_LOGIN = "/pages/login.jsp";
	public static final String PAGE_EDIT_USER = "/pages/buymanager/usereditor.jsp";
	
	//  --------------- Operation Constants ---------------
	public static final int OPT_ENTITY_SAVE_OR_UPDATE = 0;
	public static final int OPT_ENTITY_DELETE = 1;
	public static final int OPT_ENTITY_GET_ALL = 2;

}
