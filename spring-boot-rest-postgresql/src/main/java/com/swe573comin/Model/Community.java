package com.swe573comin.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "community")
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "semanticTag")
	private String semanticTag;

	@Column(name = "bannerUrl")
	private String bannerUrl;
	
	public Community() {
	}

	public Community(String name, String description, String semanticTag, String bannerUrl) {
		this.name = name;
		this.description = description;
		this.semanticTag = semanticTag;
		this.bannerUrl = bannerUrl;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSemanticTag() {
		return semanticTag;
	}

	public void setSemanticTag(String semanticTag) {
		this.semanticTag = semanticTag;
	}
	
	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
}