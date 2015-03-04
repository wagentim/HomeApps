package cn.wagentim.homeapps.entities.managers;


public enum DataManager
{
    INSTANE;
    private static final DBDataManager cmg = new DBDataManager();
    private static final CachedDataManager cdm = new CachedDataManager();

    public synchronized DBDataManager DB_DATA()
    {
        return cmg;
    }

    public synchronized CachedDataManager CACHE_DATA()
    {
        return cdm;
    }
}

