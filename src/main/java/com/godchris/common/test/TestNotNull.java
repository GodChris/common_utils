package com.godchris.common.test;

import com.godchris.common.annotation.NotBlank;
import com.godchris.common.annotation.NotEmpty;
import com.godchris.common.annotation.NotNull;
import com.godchris.common.utils.ParamsCheck;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.*;

/**
 * 测试notNull注解
 *
 * @author Chris
 * @create 2020-04-22 14:39
 **/
public class TestNotNull {
	public static void main(String[] args) throws IllegalAccessException {
		TestObject t = new TestObject(null, "", null);
		List<String> a = Lists.newArrayList();
		a.add("");
		Map<String, Integer> b = Maps.newHashMap();
		t.setCo(a);
		t.setMo(b);
		List<String> err = ParamsCheck.checkForList(t);
		System.out.println(err);
	}

	@Data
	static class TestObject {
		@NotNull
		private String name;

		@NotBlank
		private String gender;

		@NotNull
		private Integer id;

		@NotEmpty
		private List<String> co;

		@NotEmpty(require = false)
		private Map<String, Integer> mo;

		public TestObject(String name, String gender, Integer id) {
			this.name = name;
			this.gender = gender;
			this.id = id;
		}
	}
}
