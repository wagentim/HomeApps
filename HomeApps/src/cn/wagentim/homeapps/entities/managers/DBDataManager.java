package cn.wagentim.homeapps.entities.managers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.StatementHelper;

public class DBDataManager
{
	private static final EntityManager em = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME).createEntityManager();
	private static final Logger logger = Logger.getLogger(DBDataManager.class.getSimpleName());

    public void addOrModifyData(Object entity, Long id)
    {
    	if( null == id || 0 == id )
    	{
    		addNewEntity(entity);
    	}
    	else
    	{
    		mergeEntity(entity);
    	}


    }

    private void mergeEntity(final Object entity)
    {
    	em.getTransaction().begin();
    	em.merge(entity);
    	em.getTransaction().commit();
    	logger.log(Level.INFO, "DBDataManager#mergeEntity merge the entity: " + entity.getClass().getSimpleName());
    }

	private void addNewEntity(final Object entity)
    {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		logger.log(Level.INFO, "DBDataManager#addNewEntity add a new entity: " + entity.getClass().getSimpleName());
    }

	public List<?> getAllEntity(Class<?> entityType)
    {
        logger.log(Level.INFO, "DBDataManager#getAllEntity get all entites: " + entityType.getSimpleName());
		return em.createQuery(StatementHelper.jpaGetAllEntity(entityType)).getResultList();
    }

	public void deleteEntity(Class<?> entityType, Long uid)
	{
		Object c = em.find(entityType, uid);

		if( null == c )
		{
		    logger.log(Level.INFO, "DBDataManager#deleteEntity cannot find Entity: " + entityType.getSimpleName() + " with the id: " + uid);
			return;
		}
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}

}
