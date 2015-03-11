package cn.wagentim.homeapps.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="StringEntity")
public class StringEntity implements Serializable, IEntity
{

    private static final long serialVersionUID = -493076099082116922L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    private String value;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
