/*
 * The MIT License
 *
 *  Copyright (c) 2020, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package org.rossonet.ext.rules.api;

import java.util.Objects;

/**
 * A class representing a named fact. Facts have unique names within a
 * {@link Facts} instance.
 * 
 * @param <T> type of the fact
 * @author Mahmoud Ben Hassine
 */
public class Fact<T> {

	private static int maxCharsInValueToString = 80;

	public static int getMaxCharsInValueToString() {
		return maxCharsInValueToString;
	}

	public static void setMaxCharsInValueToString(final int maxCharsInValueToString) {
		Fact.maxCharsInValueToString = maxCharsInValueToString;
	}

	private final String name;
	private final T value;

	/**
	 * Create a new fact.
	 * 
	 * @param name  of the fact
	 * @param value of the fact
	 */
	public Fact(final String name, final T value) {
		Objects.requireNonNull(name, "name must not be null");
		Objects.requireNonNull(value, "value must not be null");
		this.name = name;
		this.value = value;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Fact<?> fact = (Fact<?>) o;
		return name.equals(fact.name);
	}

	/**
	 * Get the fact name.
	 * 
	 * @return fact name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the fact value.
	 * 
	 * @return fact value
	 */
	public T getValue() {
		return value;
	}

	/*
	 * The Facts API represents a namespace for facts where each fact has a unique
	 * name. Hence, equals/hashcode are deliberately calculated only on the fact
	 * name.
	 */

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		String valueString = "";
		if (value != null) {
			if (value.toString().length() <= maxCharsInValueToString) {
				valueString = value.toString();
			} else {
				valueString = value.toString().substring(0, maxCharsInValueToString) + "[...]";
			}
		}
		return "Fact{" + "name='" + name + '\'' + ", value=" + valueString + '}';
	}
}
