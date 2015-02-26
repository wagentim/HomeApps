package cn.wagentim.homeapps.entities.managers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import cn.wagentim.homeapps.entities.CustomerEntity;
import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.Utils;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class CustomerManager implements ICustomerStatement
{
	private Map<Long, CustomerEntity> customers = null;
	private static final EntityManager em = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME).createEntityManager();

    public CustomerManager()
    {
    	customers = new ConcurrentHashMap<Long, CustomerEntity>();
    	loadCustomersFromDB();
    }

    public synchronized boolean postCustomer(CustomerEntity customer, boolean isNewEntity)
    {
    	if( null == customer )
    	{
    		return false;
    	}

    	if( isNewEntity )
    	{
    		addNewCustomerToDB(customer);
    	}
    	else
    	{
    		updateCustomer(customer);
    	}

    	loadCustomersFromDB();
    	return true;
    }

    private void updateCustomer(CustomerEntity customer)
	{
    	Long id = customer.getId();
    	Set<Long> keys = customers.keySet();
    	if( keys.contains(id) )
    	{
    		customers.put(id, customer);
    		mergeCustomer(customer);
    	}
	}

    private void mergeCustomer(final CustomerEntity customer)
    {
    	em.getTransaction().begin();
    	em.merge(customer);
    	em.getTransaction().commit();
    }

	private void addNewCustomerToDB(CustomerEntity customer)
    {
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
    }

    private void loadCustomersFromDB()
    {
        @SuppressWarnings("unchecked")
		List<CustomerEntity> result = em.createQuery(GET_ALL_CUSTOMERS).getResultList();

        if( null != result && !result.isEmpty() )
        {
        	customers.clear();

        	for(CustomerEntity c : result)
        	{
        		customers.put(c.getId(), c);
        	}
        }
    }

    public String getCustomersAsString()
    {
    	if( null == customers || customers.isEmpty() )
        {
    		return Utils.toJson(new JSONObject());
        }
    	else
    	{
    		return Utils.toJson(customers.values());
    	}
    }

	public synchronized boolean deleteCustomer(Long uid)
	{
		CustomerEntity c = em.find(CustomerEntity.class, uid);

		if( null == c )
		{
			return false;
		}
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();

		loadCustomersFromDB();

		return true;
	}

	public CustomerEntity getCustomerWithID(Long uid)
	{
		if( null == customers || customers.isEmpty() )
		{
			return null;
		}

		Set<Long> keys = customers.keySet();

		for(Long id : keys)
		{
			if(id.equals(uid))
			{
				return customers.get(id);
			}
		}

		return null;
	}

	public synchronized void deleteAllCustomers()
	{
		if( null == customers || customers.isEmpty() )
		{
			return;
		}
		Set<Long> keys = customers.keySet();
		for(Long key : keys)
		{
			deleteCustomer(key);
		}

		loadCustomersFromDB();
	}
}
