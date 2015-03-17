package cn.wagentim.homeapps.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="OrderEntity")
public final class OrderEntity implements IEntityStatus, Serializable, IEntity
{

	private static final long serialVersionUID = -6424192667756126476L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long customer;
	@OneToMany
	private List<OrderItemEntity> items;
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
    public List<OrderItemEntity> getItems()
    {
        return items;
    }
    public void setItems(List<OrderItemEntity> items)
    {
        this.items = items;
    }

}
