package cn.wagentim.homeapps.entities.managers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.StatementHelper;

public class DBDataManager
{
	private static final EntityManager em = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME).createEntityManager();

    public synchronized void addOrModifyData(Object entity, Long id)
    {
    	if( 0 == id )
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
    }

	private void addNewEntity(final Object entity)
    {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
    }

    @SuppressWarnings({ "unchecked" })
	public List<Object> getAllEntity(Class<?> entityType)
    {
		return em.createQuery(StatementHelper.jpaGetAllEntity(entityType)).getResultList();
    }

	public synchronized void deleteEntity(Class<?> entityType, Long uid)
	{
		Object c = em.find(entityType, uid);

		if( null == c )
		{
			return;
		}
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}
}
