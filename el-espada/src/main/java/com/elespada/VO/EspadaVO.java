/*
 * Copyright [2020] [ElEspada - Avengers-UIS Force - Software Engineering Capstone - Springfield, IL]
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
package com.elespada.VO;

/**
 * <b>EspadaVO.java</b>
 * <p>
 * A plain old POJO, Espada Value Object for capturing form data. This is used
 * for capturing items added to order by the user
 *
 */
public class EspadaVO {
	private String menuIds;

	/**
	 * @return the menuIds
	 */
	public String getMenuIds() {
		return menuIds;
	}

	/**
	 * @param menuIds the menuIds to set
	 */
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((menuIds == null) ? 0 : menuIds.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EspadaVO other = (EspadaVO) obj;
		if (menuIds == null) {
			if (other.menuIds != null) {
				return false;
			}
		} else if (!menuIds.equals(other.menuIds)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EspadaVO [menuIds=");
		builder.append(menuIds);
		builder.append("]");
		return builder.toString();
	}

}
