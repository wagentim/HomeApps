package cn.wagentim.homeapps.entities.managers;

import cn.wagentim.homeapps.entities.CustomerEntity;
import cn.wagentim.homeapps.utils.Constants;

public class EntityHelper
{
    public static final Class<?> getEntityClazz(final int entityType)
    {
        switch(entityType)
        {
            case Constants.ENTITY_CUSTOMER:
                return CustomerEntity.class;
            default:
                return null;
        }
    }
}
