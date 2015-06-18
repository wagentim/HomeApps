package cn.wagentim.homeapps.entities.managers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cn.wagentim.homeapps.entities.CustomerEntity;

public final class UserManager
{
	private static final UserManager manager = new UserManager();
	private Set<CustomerEntity> customers = new HashSet<CustomerEntity>();
	
	private UserManager()
	{
	}
	
	public static UserManager INSTANCE()
	{
		return manager;
	}
	
	public synchronized void addUser(CustomerEntity customer)
	{
		if( null == customer || findCustomer(customer) )
		{
			return;
		}
		
		customers.add(customer);
	}
	
	public synchronized void removeUser(CustomerEntity customer)
	{
		if( null == customer || !findCustomer(customer) )
		{
			return;
		}
		
		customers.remove(customer);
	}

	public synchronized boolean findCustomer(CustomerEntity customer)
	{
		Iterator<CustomerEntity> it = customers.iterator();
		
		while(it.hasNext())
		{
			if( customer == it.next() )
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	
}
