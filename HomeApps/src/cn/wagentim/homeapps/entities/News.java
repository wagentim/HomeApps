package cn.wagentim.homeapps.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="news")
public class News implements IEntity, Serializable
{
	private static final long serialVersionUID = 7978185119914384765L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private String content;
	private List<Long> resources;

	private Long id;

	@Override
	public Long getId()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
