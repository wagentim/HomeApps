package cn.wagentim.homeapps.entities.managers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import cn.wagentim.homeapps.entities.OrderEntity;

public class OrderManager implements IStatement
{
	private final String PERSISTENCE_UNIT_NAME = "transactions-optional";
	/** Data Format <Customer, List<Order>> */
	private final Map<Long, List<OrderEntity>> orders;

    public OrderManager()
    {
        orders = new ConcurrentHashMap<Long, List<OrderEntity>>(10);
    }

    private void loadOrderForDB(long customer)
    {
    	if( !orders.containsKey(customer) )
    	{
    		orders.put(customer, loadOrdersOfCustomerFromDB(customer));
    	}
    }

    public void addOrder(final OrderEntity order, final long customer)
    {
    	if( null == order )
    	{
    		return;
    	}

    	loadOrderForDB(customer);

    	List<OrderEntity> ords = orders.get(customer);

    	if(!ords.contains(order))
    	{
    		ords.add(order);
    	}
    }

    private EntityManager getEntitiyManager()
    {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    }

	public List<OrderEntity> getOrders(final long customer)
	{
	    if( !orders.containsKey(customer) )
	    {
	        loadOrderForDB(customer);
	    }

	    return orders.get(customer);
	}

    @SuppressWarnings("unchecked")
	private List<OrderEntity> loadOrdersOfCustomerFromDB(long customer)
    {
        List<OrderEntity> result = null;

        EntityManager em = getEntitiyManager();
        try
        {
        	Query q = em.createQuery(GET_ORDERS_BY_CUSTOMER);
        	q.setParameter("customer_id", customer);
            result = q.getResultList();
        }
        finally
        {
            em.close();
        }

        return result;
    }
}
