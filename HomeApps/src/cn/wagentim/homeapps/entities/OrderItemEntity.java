package cn.wagentim.homeapps.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public final class OrderItemEntity implements Serializable, IEntity
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long product;
    private double singlePrice;
    private int amount;
    private double totalPrice;
    private double singleWeight;
    private double totalWeight;
    private String others;

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getProduct()
    {
        return product;
    }
    public void setProduct(Long product)
    {
        this.product = product;
    }
    public int getAmount()
    {
        return amount;
    }
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
	public double getSinglePrice()
	{
		return singlePrice;
	}
	public void setSinglePrice(double singlePrice)
	{
		this.singlePrice = singlePrice;
	}
	public double getTotalPrice()
	{
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice)
	{
		this.totalPrice = totalPrice;
	}
	public double getSingleWeight()
	{
		return singleWeight;
	}
	public void setSingleWeight(double singleWeight)
	{
		this.singleWeight = singleWeight;
	}
	public double getTotalWeight()
	{
		return totalWeight;
	}
	public void setTotalWeight(double totalWeight)
	{
		this.totalWeight = totalWeight;
	}
	public String getOthers()
	{
		return others;
	}
	public void setOthers(String others)
	{
		this.others = others;
	}
	
	@Override
	public String toString()
	{
		return "OrderItemEntity [id=" + id + ", product="
				+ product + ", singlePrice=" + singlePrice + ", amount="
				+ amount + ", totalPrice=" + totalPrice + ", singleWeight="
				+ singleWeight + ", totalWeight=" + totalWeight + ", others="
				+ others + "]";
	}
}
