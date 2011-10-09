#!/usr/local/bin/io

Number fibi := method(
  if(self == 1, return(1))
  tmp := 0
  a := 0
  b := 1
  for(n, 1, self-1, 
    tmp = a+b
    a = b
    b = tmp
  )
)

Number fibr := method(
  if(self<2, 
     self, 
     (self-1) fibr + (self-2) fibr
  )
)

Suite := UnitTest clone do(
  testShouldFindFibonnaciNumbersIteratively := method(
    assertEquals(1, (1 fibi))
    assertEquals(1, (2 fibi))
    assertEquals(2, (3 fibi))
    assertEquals(3, (4 fibi))
    assertEquals(5, (5 fibi))
    assertEquals(8, (6 fibi))
    assertEquals(13, (7 fibi))
  )
  
  testShouldFindFibonnaciNumbersRecursively := method(
    assertEquals(1, (1 fibr))
    assertEquals(1, (2 fibr))
    assertEquals(2, (3 fibr))
    assertEquals(3, (4 fibr))
    assertEquals(5, (5 fibr))
    assertEquals(8, (6 fibr))
    assertEquals(13, (7 fibr))
  )

)

// Run all tests
Suite run
