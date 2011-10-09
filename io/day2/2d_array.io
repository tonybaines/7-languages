#!/usr/local/bin/io

List sum2d := method (
  self map(subList, subList sum) sum
)

TwoDeeList := List clone do (
  ys := list()

  dim := method (x,y,
    ys setSize(y)
    for(i,1,y, 
      xs := list()
      xs setSize(x)
      ys atPut(i-1, xs)
    )
  )
  
  get := method(x,y,
    (ys at(y-1)) at(x-1)
  )
  set := method(x,y,value,
    (ys at(y-1)) atPut(x-1, value)
  )
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
  
  testShouldCreateA2dList := method (
    my2dList := TwoDeeList clone
    my2dList dim(3,3)
    my2dList set(2,2,1)
    my2dList set(3,2,15)
    assertEquals(1, my2dList get(2,2))
    assertEquals(15, my2dList get(3,2))
  )
)

Suite run
