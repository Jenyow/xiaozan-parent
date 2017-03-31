package com.xiaozan.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The short prefix toString style. Using the <code>Person</code> example from
 * {@link ToStringBuilder}, the output would look like this:
 *
 * <pre>
 * Person[name=John Doe,age=33,smoker=false]
 * </pre>
 *
 */
public class CommonMessage {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}