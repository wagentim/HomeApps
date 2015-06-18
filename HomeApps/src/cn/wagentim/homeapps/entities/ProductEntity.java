package cn.wagentim.homeapps.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProductEntity")
public final class ProductEntity implements IEntityConstants, IEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int categorie;
	private int defaultAmount;
	private double defaultPrice;
	private int nettoWeigth;
	private double rabatt;

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getCategorie()
    {
        return categorie;
    }
    public void setCategorie(int categorie)
    {
        this.categorie = categorie;
    }
    public int getDefaultAmount()
    {
        return defaultAmount;
    }
    public void setDefaultAmount(int defaultAmount)
    {
        this.defaultAmount = defaultAmount;
    }
    public double getDefaultPrice()
    {
        return defaultPrice;
    }
    public void setDefaultPrice(double defaultPrice)
    {
        this.defaultPrice = defaultPrice;
    }
	public int getNettoWeigth()
	{
		return nettoWeigth;
	}
	public void setNettoWeigth(int nettoWeigth)
	{
		this.nettoWeigth = nettoWeigth;
	}
    public double getRabatt()
    {
        return rabatt;
    }
    public void setRabatt(double rabatt)
    {
        this.rabatt = rabatt;
    }
}
