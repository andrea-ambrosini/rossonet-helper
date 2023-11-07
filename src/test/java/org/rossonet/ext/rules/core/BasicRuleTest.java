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
import org.rossonet.ext.rules.api.Facts;
import org.rossonet.ext.rules.api.Rules;
import org.rossonet.ext.rules.core.BasicRule;

public class BasicRuleTest extends AbstractTest {

	static class FirstRule extends BasicRule {
		@Override
		public boolean evaluate(Facts facts) {
			return true;
		}

		@Override
		public String getName() {
			return "rule1";
		}

		@Override
		public int getPriority() {
			return 1;
		}
	}

	static class SecondRule extends BasicRule {
		@Override
		public boolean evaluate(Facts facts) {
			return true;
		}

		@Override
		public String getName() {
			return "rule2";
		}

		@Override
		public int getPriority() {
			return 3;
		}
	}

	static class ThirdRule extends BasicRule {
		@Override
		public boolean evaluate(Facts facts) {
			return true;
		}

		@Override
		public String getName() {
			return "rule3";
		}

		@Override
		public int getPriority() {
			return 2;
		}
	}

	@Test
	public void basicRuleEvaluateShouldReturnFalse() {
		final BasicRule basicRule = new BasicRule();
		assertThat(basicRule.evaluate(facts)).isFalse();
	}

	@Test
	public void testCompareTo() {
		final FirstRule rule1 = new FirstRule();
		final FirstRule rule2 = new FirstRule();

		assertThat(rule1.compareTo(rule2)).isEqualTo(0);
		assertThat(rule2.compareTo(rule1)).isEqualTo(0);
	}

	@Test
	public void testSortSequence() {
		final FirstRule rule1 = new FirstRule();
		final SecondRule rule2 = new SecondRule();
		final ThirdRule rule3 = new ThirdRule();

		rules = new Rules(rule1, rule2, rule3);

		rulesEngine.check(rules, facts);
		assertThat(rules).containsSequence(rule1, rule3, rule2);
	}

}
