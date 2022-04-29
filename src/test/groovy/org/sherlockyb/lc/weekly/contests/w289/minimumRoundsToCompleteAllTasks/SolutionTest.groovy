package org.sherlockyb.lc.weekly.contests.w289.minimumRoundsToCompleteAllTasks

import spock.lang.Specification

/**
 * @author sherlockyb
 */
class SolutionTest extends Specification {
	Solution sut;

	def setup() {
		sut = new Solution();
	}

	def "case 1"() {
		given:
		int[] tasks = [2,2,3,3,2,4,4,4,4,4]

		when:
		def result = sut.minimumRounds(tasks)

		then:
		result == 4
	}

	def "case 2"() {
		given:
		int[] tasks = [2,3,3]

		when:
		def result = sut.minimumRounds(tasks)

		then:
		result == -1
	}
}
