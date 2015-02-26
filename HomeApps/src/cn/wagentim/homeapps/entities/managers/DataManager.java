package cn.wagentim.homeapps.entities.managers;


public enum DataManager
{
    INSTANE;
    private static final CustomerManager cmg = new CustomerManager();
    private static final CachedDataManager cdm = new CachedDataManager();

    public CustomerManager CUSTOMER()
    {
        return cmg;
    }

    public CachedDataManager CACHE_DATA()
    {
        return cdm;
    }
}

