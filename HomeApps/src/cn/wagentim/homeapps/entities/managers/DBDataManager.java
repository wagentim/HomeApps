package cn.wagentim.homeapps.entities.managers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import cn.wagentim.homeapps.entities.IDEntity;
import cn.wagentim.homeapps.entities.IEntity;
import cn.wagentim.homeapps.entities.IEntityConstants;
import cn.wagentim.homeapps.entities.OrderEntity;
import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.StatementHelper;

public class DBDataManager implements IStatement, IEntityConstants
{
	private static final EntityManager em = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME).createEntityManager();
	private static final Logger logger = Logger.getLogger(DBDataManager.class.getSimpleName());

    public synchronized Long addOrModifyData(IEntity entity, Long id)
    {
    	if( null == id || 0 == id )
    	{
    		addNewEntity(entity);
    		return entity.getId();
    	}
    	else
    	{
    		mergeEntity(entity);
    		return id;
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

	public synchronized List<?> getAllEntity(Class<?> entityType)
    {
        logger.log(Level.INFO, "DBDataManager#getAllEntity get all entites: " + entityType.getSimpleName());
		return em.createQuery(StatementHelper.jpaGetAllEntity(entityType)).getResultList();
    }

	public synchronized void deleteEntity(Class<?> entityType, Long uid)
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
	
	@SuppressWarnings("unchecked")
	public synchronized IDEntity getIDEntity(int type)
	{
		List<IDEntity> result = null;
		Query q = em.createQuery(GET_ID_ENTITY_BY_TYPE);
		q.setParameter("type", type);
		result = q.getResultList();
        
        if( null == result || result.isEmpty() )
        {
        	IDEntity entity = new IDEntity();
        	entity.setLastNumber("-1");
        	entity.setType(ID_ENTITY_ORDER);
        	entity.setMode(ID_Entity_MODE_ORDER);
        	
        	return entity;
        }

        return result.get(0);
	}

	public synchronized boolean checkUser(String username, String password)
	{
		List<IDEntity> result = null;
		Query q = em.createQuery(GET_USER_BY_USERNAME_AND_PASSWORD);
		q.setParameter("username", username);
		q.setParameter("password", password);
		result = q.getResultList();
        
        if( result.isEmpty() )
        {
        	return false;
        }

		return true;
	}

}
