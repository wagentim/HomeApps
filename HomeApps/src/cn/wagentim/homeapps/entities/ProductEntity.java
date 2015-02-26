package cn.wagentim.homeapps.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProductEntity")
public final class ProductEntity implements IEntityStatus
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
}
