package com.training.menu.models;

import org.springframework.core.convert.converter.Converter;

import com.training.menu.models.constants.Constants;
import com.training.menu.models.constants.Constants.SORT_ORDER;



public class SortTypeEnumConvertor implements Converter<String, Constants.SORT_ORDER> {

	@Override
	public SORT_ORDER convert(String source) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return SORT_ORDER.valueOf(source);
	}

}
