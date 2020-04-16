/*
 * Copyright [2020] [ElEspada - Software Engineering Capstone - Springfield, IL]
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.elespada.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menu {
	@Id
	private Long menuId;
	private String menuName;
	private String menuType;
	private String menuDetails;
	private Float menuPrice;
	private String menuImgSrc;

	public Menu() {
		super();
	}

	public Menu(Long menuId, String menuName, String menuType, String menuDetails, Float menuPrice, String menuImgSrc) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuDetails = menuDetails;
		this.menuPrice = menuPrice;
		this.menuImgSrc = menuImgSrc;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuDetails() {
		return menuDetails;
	}

	public void setMenuDetails(String menuDetails) {
		this.menuDetails = menuDetails;
	}

	public Float getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(Float menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuImgSrc() {
		return menuImgSrc;
	}

	public void setMenuImgSrc(String menuImgSrc) {
		this.menuImgSrc = menuImgSrc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuDetails == null) ? 0 : menuDetails.hashCode());
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((menuImgSrc == null) ? 0 : menuImgSrc.hashCode());
		result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + ((menuPrice == null) ? 0 : menuPrice.hashCode());
		result = prime * result + ((menuType == null) ? 0 : menuType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (menuDetails == null) {
			if (other.menuDetails != null)
				return false;
		} else if (!menuDetails.equals(other.menuDetails))
			return false;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (menuImgSrc == null) {
			if (other.menuImgSrc != null)
				return false;
		} else if (!menuImgSrc.equals(other.menuImgSrc))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (menuPrice == null) {
			if (other.menuPrice != null)
				return false;
		} else if (!menuPrice.equals(other.menuPrice))
			return false;
		if (menuType == null) {
			if (other.menuType != null)
				return false;
		} else if (!menuType.equals(other.menuType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu [menuId=");
		builder.append(menuId);
		builder.append(", menuName=");
		builder.append(menuName);
		builder.append(", menuType=");
		builder.append(menuType);
		builder.append(", menuDetails=");
		builder.append(menuDetails);
		builder.append(", menuPrice=");
		builder.append(menuPrice);
		builder.append(", menuImgSrc=");
		builder.append(menuImgSrc);
		builder.append("]");
		return builder.toString();
	}

}
