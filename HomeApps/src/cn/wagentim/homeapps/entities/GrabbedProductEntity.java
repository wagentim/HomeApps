package cn.wagentim.homeapps.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GrabbedProductEnt")
public final class GrabbedProductEntity implements IEntityStatus, IEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String desc;
	private String origPrice;
	private String currPrice;
	private String webLink;
	private String imageLink;
	private String domain;
	private String category;
	
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
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getOrigPrice()
	{
		return origPrice;
	}
	public void setOrigPrice(String origPrice)
	{
		this.origPrice = origPrice;
	}
	public String getCurrPrice()
	{
		return currPrice;
	}
	public void setCurrPrice(String currPrice)
	{
		this.currPrice = currPrice;
	}
	public String getWebLink()
	{
		return webLink;
	}
	public void setWebLink(String webLink)
	{
		this.webLink = webLink;
	}
	public String getImageLink()
	{
		return imageLink;
	}
	public void setImageLink(String imageLink)
	{
		this.imageLink = imageLink;
	}
	public String getDomain()
	{
		return domain;
	}
	public void setDomain(String domain)
	{
		this.domain = domain;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}

}
