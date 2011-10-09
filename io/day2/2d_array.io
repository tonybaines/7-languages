#!/usr/local/bin/io

List sum2d := method (
  self map(subList, subList sum) sum
)

Suite := UnitTest clone do(
  testShouldSumASimple2dListWithOneEntry := method (
    assertEquals(1, list(list(1)) sum2d)
  )
  testShouldSumA2dListWithOneSubListWithTwoEntries := method (
    assertEquals(3, list(list(1,2)) sum2d)
  )
  testShouldSumA2dListWithTwoSubListEachWithTwoEntries := method (
    assertEquals(10, list(list(1,2), list(3,4)) sum2d)
  )
)

Suite run
