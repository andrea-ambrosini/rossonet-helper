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
package org.rossonet.ext.rules.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.rossonet.ext.rules.annotation.Action;
import org.rossonet.ext.rules.annotation.Condition;
import org.rossonet.ext.rules.annotation.Rule;
import org.rossonet.ext.rules.api.RulesEngine;
import org.rossonet.ext.rules.core.DefaultRulesEngine;

public class AnnotationInheritanceTest extends AbstractTest {

	@Rule
	static class MyBaseRule {
		protected boolean executed;

		public boolean isExecuted() {
			return executed;
		}

		@Action
		public void then() {
			executed = true;
		}

		@Condition
		public boolean when() {
			return true;
		}
	}

	class MyChildRule extends MyBaseRule {

	}

	@Test
	public void annotationsShouldBeInherited() {
		// Given
		final MyChildRule myChildRule = new MyChildRule();
		rules.register(myChildRule);

		// When
		final RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);

		// Then
		assertThat(myChildRule.isExecuted()).isTrue();
	}
}
