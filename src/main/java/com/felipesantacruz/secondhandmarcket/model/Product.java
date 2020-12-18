package com.felipesantacruz.secondhandmarcket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Product
{
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "Name must contain any character.")
	private String name;
	
	@PositiveOrZero(message = "Price shouldn't be negative.")
	private Float price;
	
	private String image;
	
	@ManyToOne
	private Purchase purchase;
	
	@ManyToOne
	@NotNull
	private MarketUser owner;

	public Product() { }

	public Product(String name, Float price, String image, MarketUser owner)
	{
		this.name = name;
		this.price = price;
		this.image = image;
		this.owner = owner;
	}

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

	public Float getPrice()
	{
		return price;
	}

	public void setPrice(Float price)
	{
		this.price = price;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public Purchase getPurchase()
	{
		return purchase;
	}

	public void setPurchase(Purchase purchase)
	{
		this.purchase = purchase;
	}

	public MarketUser getOwner()
	{
		return owner;
	}

	public void setOwner(MarketUser owner)
	{
		this.owner = owner;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((purchase == null) ? 0 : purchase.hashCode());
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
		Product other = (Product) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null)
		{
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null)
		{
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (price == null)
		{
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (purchase == null)
		{
			if (other.purchase != null)
				return false;
		} else if (!purchase.equals(other.purchase))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", purchase="
				+ purchase + ", owner=" + owner + "]";
	}
		
}
