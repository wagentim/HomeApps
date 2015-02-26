package cn.wagentim.homeapps.entities;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OrderEntity")
public final class OrderEntity implements IEntityStatus, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6424192667756126476L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long customer;
	/** Data Format <Product_ID, Amount> */
	private Map<Long, Double> productions;
	private Double soldPreis;
	private int status;
	public Long getCustomer()
	{
		return customer;
	}
	public void setCustomer(Long customer)
	{
		this.customer = customer;
	}
	public Map<Long, Double> getProductions()
	{
		return productions;
	}
	public void setProductions(Map<Long, Double> productions)
	{
		this.productions = productions;
	}
	public Double getSoldPreis()
	{
		return soldPreis;
	}
	public void setSoldPreis(Double soldPreis)
	{
		this.soldPreis = soldPreis;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((productions == null) ? 0 : productions.hashCode());
		result = prime * result
				+ ((soldPreis == null) ? 0 : soldPreis.hashCode());
		result = prime * result + status;
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		if (customer == null)
		{
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productions == null)
		{
			if (other.productions != null)
				return false;
		} else if (!productions.equals(other.productions))
			return false;
		if (soldPreis == null)
		{
			if (other.soldPreis != null)
				return false;
		} else if (!soldPreis.equals(other.soldPreis))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "OrderEntity [id=" + id + ", customer=" + customer
				+ ", productions=" + productions + ", soldPreis=" + soldPreis
				+ ", status=" + status + "]";
	}
}
