package com.in28minuter.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	/**
	 * Static filtering
	 */
	@GetMapping("/filtering")
	public SomeBean filtering() {
		return new SomeBean("value 1", "value 2", "value 3");
	}

	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value 1", "value 2", "value 3"),
				new SomeBean("value 1", "value 2", "value 3"), new SomeBean("value 1", "value 2", "value 3"));
	}

	/**
	 * Dynamic Filtering
	 */

	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue filteringDynamic() {

		SomeBean someBean = new SomeBean("value 1", "value 2", "value 3", "value 4");

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		mappingJacksonValue.setFilters(filters);

		return mappingJacksonValue;
	}

	@GetMapping("/filtering-dynamic-list")
	public MappingJacksonValue filteringDynamiclist() {

		List<SomeBean> list = Arrays.asList(new SomeBean("value 1", "value 2", "value 3", "value 4"),
				new SomeBean("value 1", "value 2", "value 3","value 4"),
				new SomeBean("value 1", "value 2", "value 3", "value 4"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field4");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;

	}
}
