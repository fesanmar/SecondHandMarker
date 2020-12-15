package com.felipesantacruz.secondhandmarcket.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Purchase
{
	@Id
	@GeneratedValue
	private Long id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne
	private User purchaser;

	public Purchase() { }

	public Purchase(User purchaser)
	{
		this.purchaser = purchaser;
	}
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public User getPurchaser()
	{
		return purchaser;
	}

	public void setPurchaser(User purchaser)
	{
		this.purchaser = purchaser;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((purchaser == null) ? 0 : purchaser.hashCode());
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
		Purchase other = (Purchase) obj;
		if (date == null)
		{
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (purchaser == null)
		{
			if (other.purchaser != null)
				return false;
		} else if (!purchaser.equals(other.purchaser))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Purchase [id=" + id + ", date=" + date + ", purchaser=" + purchaser + "]";
	}
	
	
}
