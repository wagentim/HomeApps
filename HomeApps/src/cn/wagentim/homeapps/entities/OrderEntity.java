package cn.wagentim.homeapps.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="OrderEntity")
public final class OrderEntity implements IEntityConstants, Serializable, IEntity
{

	private static final long serialVersionUID = -6424192667756126476L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long customer;
	private List<Long> items;
	@Transient
	private List<OrderItemEntity> orders;
	private int status;

	public Long getCustomer()
	{
		return customer;
	}
	public void setCustomer(Long customer)
	{
		this.customer = customer;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public List<Long> getItems()
    {
        return items;
    }
    public void setItems(List<Long> items)
    {
        this.items = items;
    }
    public void addOrderItem(Long item)
    {
        if(null == this.items)
        {
            this.items = new ArrayList<Long>();
        }
        this.items.add(item);
    }
	public List<OrderItemEntity> getOrders()
	{
		return orders;
	}
	
	public void addOrder(OrderItemEntity order)
	{
		if( null == orders )
		{
			orders = new ArrayList<OrderItemEntity>();
		}
		
		if( null != order )
		{
			orders.add(order);
		}
	}

}
