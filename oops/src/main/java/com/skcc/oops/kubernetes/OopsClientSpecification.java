package com.skcc.oops.kubernetes;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.cloud.context.named.NamedContextFactory;

class OopsClientSpecification implements NamedContextFactory.Specification {

	private String name;

	private Class<?>[] configuration;

	OopsClientSpecification() {
	}

	OopsClientSpecification(String name, Class<?>[] configuration) {
		this.name = name;
		this.configuration = configuration;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?>[] getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(Class<?>[] configuration) {
		this.configuration = configuration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OopsClientSpecification that = (OopsClientSpecification) o;
		return Objects.equals(this.name, that.name)
				&& Arrays.equals(this.configuration, that.configuration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.configuration);
	}

	@Override
	public String toString() {
		return new StringBuilder("FeignClientSpecification{").append("name='")
				.append(this.name).append("', ").append("configuration=")
				.append(Arrays.toString(this.configuration)).append("}").toString();
	}

}
