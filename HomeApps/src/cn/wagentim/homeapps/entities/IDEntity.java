package cn.wagentim.homeapps.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IdTable")
public class IDEntity implements IEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long mode;
	private String lastNumber;
	private int type;
	
	@Override
	public Long getId()
	{
		return null;
	}

	public Long getMode()
	{
		return mode;
	}

	public void setMode(Long mode)
	{
		this.mode = mode;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getLastNumber()
	{
		return lastNumber;
	}

	public void setLastNumber(String lastNumber)
	{
		this.lastNumber = lastNumber;
	}
}
