package com.felipesantacruz.secondhandmarcket.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class MarketUser
{
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "Name must contain any character.")
	private String name;
	
	@NotBlank(message = "Surname must contain any character.")
	private String surname;
	private String avatar;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpDate;
	
	@Email(message = "Email should be valid.")
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password;
	
	public MarketUser() { }
	
	

	public MarketUser(String name, String surname, String avatar, String email, String password)
	{
		this.name = name;
		this.surname = surname;
		this.avatar = avatar;
		this.email = email;
		this.password = password;
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

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getAvatar()
	{
		return avatar;
	}

	public void setAvatar(String avatar)
	{
		this.avatar = avatar;
	}

	public Date getSignUpDate()
	{
		return signUpDate;
	}

	public void setSignUpDate(Date signUpDate)
	{
		this.signUpDate = signUpDate;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((signUpDate == null) ? 0 : signUpDate.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		MarketUser other = (MarketUser) obj;
		if (avatar == null)
		{
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (email == null)
		{
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (signUpDate == null)
		{
			if (other.signUpDate != null)
				return false;
		} else if (!signUpDate.equals(other.signUpDate))
			return false;
		if (surname == null)
		{
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", avatar=" + avatar + ", signUpDate="
				+ signUpDate + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
