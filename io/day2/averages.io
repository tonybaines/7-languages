#!/usr/local/bin/io

List myAverage := method (
  if(self isEmpty,
     return 0,
     (self sum) / (self size) 
  )
)

Suite := UnitTest clone do(
  testShouldAverageASimpleListWithOneEntry := method (
    assertEquals(1, list(1) myAverage)
  )
  
  testShouldAverageAListWithNoEntry := method (
    assertEquals(0, list() myAverage)
  )
  
  testShouldRaiseAnExceptionIfANonNumericEntryIsFound := method (
    assertRaisesException(list(1,2,"Bob") myAverage)
  )
)

Suite run
