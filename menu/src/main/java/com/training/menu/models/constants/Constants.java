package com.training.menu.models.constants;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.training.menu.models.MenuException;

public class Constants {
	
	public static enum SORT_ORDER{
		ASC,DESC;
		
		private String value;
		
		@JsonValue
	    public String getValue() {
	        return value;
	    }

		
		@JsonCreator
		 public static SORT_ORDER of(String value) {
		        if (null == value) {
		            return ASC;
		        }

		        for (SORT_ORDER item : SORT_ORDER.values()) {
		            if (item.toString().equals(value)) {
		                return item;
		            }
		        }

		        throw new MenuException("Sort order is either ASC or DESC", HttpStatus.BAD_REQUEST);
		    }
		
	}

}
