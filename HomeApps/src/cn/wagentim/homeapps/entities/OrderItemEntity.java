package cn.wagentim.homeapps.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public final class OrderItemEntity implements Serializable, IEntity
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Long order;
    private Long product;
    private int amount;

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getOrder()
    {
        return order;
    }
    public void setOrder(Long order)
    {
        this.order = order;
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
}
