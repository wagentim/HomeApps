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
    public static final String CUSTOMER_PASSWORD = "pwd";
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

	//  --------------- Product Constants ---------------
	public static final String PRODUCT_NAME = "name";
	public static final String PRODUCT_CATEGORIE = "categorie";
	public static final String PRODUCT_DEFAULT_PRICE = "defaultprice";
	public static final String PRODUCT_DEFAULT_AMOUNT = "defaultamount";
	public static final String PRODUCT_NETTO_WEIGHT = "nettoweight";

	//  --------------- Order Constants ---------------
	public static final String ORDER_CONTENT = "content";
	public static final String ORDER_CUSTOMER = "customer";
	public static final String ORDER_OWNER = "owner";
	public static final String ORDER_ITEMS = "items";
	public static final String ORDER_PRODUCT = "product";
	public static final String ORDER_AMOUNT = "amount";
	public static final String ORDER_ID = "id";
	public static final String ORDER_ITEM_ID = "item_id";
	public static final String ORDER_SINGLE_PRICE = "sprice";
	public static final String ORDER_TOTAL_PRICE = "tprice";
	public static final String ORDER_SINGLE_WEIGHT = "sweight";
	public static final String ORDER_TOTAL_WEIGHT = "tweight";
	public static final String ORDER_OTHER = "other";
	public static final String ORDER_STATUS = "status";

	// 	--------------- Entity Constants ---------------
	public static final String PERSISTENCE_UNIT_NAME = "google";
	public static final int ENTITY_CUSTOMER = 0;
	public static final int ENTITY_PRODUCT = 1;
	public static final int ENTITY_ORDER = 2;

	//  --------------- Parser Constants ---------------
	public static final String OPERATION = "opt";
    public static final String AUTH = "auth";
    public static final String USER_NAME = "usr";
    public static final String PASSWORD = "pwd";
    public static final String ENTITY = "entity";
    public static final String ID = "uid";

    //  --------------- Page Constants ---------------
	public static final String PAGE_LOGIN = "/pages/login.jsp";
	public static final String PAGE_EDIT_USER = "/pages/buymanager/usereditor.jsp";
	public static final String PAGE_EDIT_PRODUCT = "/pages/buymanager/producteditor.jsp";
	public static final String PAGE_ORDER = "/pages/buymanager/order.jsp";
	public static final String PAGE_HOME = "/pages/home.jsp";
	public static final String PAGE_REGISTER = "/pages/regist.jsp";

	//  --------------- Operation Constants ---------------
	public static final int OPT_ENTITY_SAVE_OR_UPDATE = 0;
	public static final int OPT_DELETE = 1;
	public static final int OPT_ENTITY_GET_ALL = 2;
	public static final int OPT_START_GRABBING = 3;
	public static final int OPT_STOP_GRABBING = 4;
	public static final int OPT_ENTITY_DELETE_ALL = 5;
	public static final int OPT_ENTITY_SAVE_OR_UPDATE_ORDER = 6;
	
	
	//  --------------- Operation Name ---------------
	public static final String OPT_NAME_ENTITY_SAVE_OR_UPDATE = "ENTITY_SAVE_OR_UPDATE";
	public static final String OPT_NAME_ENTITY_DELETE = "OPT_ENTITY_DELETE";
	public static final String OPT_NAME_ENTITY_GET_ALL = "OPT_ENTITY_GET_ALL";
	public static final String OPT_NAME_UNKNOWN = "OPT_UNKNOWN";
	public static final String OPT_NAME_START_GRABBING = "OPT_START_GRABBING";
	public static final String OPT_NAME_STOP_GRABBING = "OPT_STOP_GRABBING";
}
