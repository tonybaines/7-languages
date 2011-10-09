#!/usr/local/bin/io

// Create a copy of the original slot
Number originalDivide := Number getSlot("/")

// Replace the '/' method
Number setSlot("/", 
  method(denom,
    if(denom == 0, 
      0, 
      self originalDivide(denom)
    )
  )
)


// Spent too long with this; doesn't work with 'suite' since
// it's not a type and the reflection in TestRunner run breaks
Suite := UnitTest clone do(
  testShouldSuccessfullyDivideByZero := method(
    assertEquals(0, (1/0))
  )

  testShouldSuccessfullyDivideByNonZero := method(
    assertEquals(2, (4/2))
  )
  
)

// Run all tests
Suite run
