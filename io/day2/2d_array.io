#!/usr/local/bin/io

List sum2d := method (
  self map(subList, 
    if(subList isEmpty, 0, subList select(x, x!=nil) sum)
  ) sum
)

TwoDeeList := List clone do (
  dim := method (x,y,
    self setSize(y)
    for(i,1,y, 
      xs := list()
      xs setSize(x)
      self atPut(i-1, xs)
    )
  )
  
  get := method(x,y,
    (self at(y-1)) at(x-1)
  )
  set := method(x,y,value,
    (self at(y-1)) atPut(x-1, value)
  )
  
  toFile := method (name,
    file := File clone openForUpdating(name)
    self foreach(x,
      file write((x join(",")), "\n")
    )
    file close
  )
  
  fromFile := method (name,
    file := File clone openForReading(name)
    lines := file readLines
    file close
    new2dList := TwoDeeList clone
    new2dList setSize(lines size)
    lines foreach(i, line,
      xs := (line split(",")) map(it, 
                                if(it == "nil", nil, it asNumber)
                              )
      new2dList atPut(i, xs)
    )
    new2dList
  )
)

Suite := UnitTest clone do(
  testShouldSumASimple2dListWithOneEntry := method (
    assertEquals(1, list(list(1)) sum2d)
  )
  testShouldSumASimple2dListWithOneEmptyEntry := method (
    assertEquals(0, list(list()) sum2d)
  )
  testShouldSumA2dListWithOneSubListWithTwoEntries := method (
    assertEquals(3, list(list(1,2)) sum2d)
  )
  testShouldSumA2dListWithTwoSubListEachWithTwoEntries := method (
    assertEquals(10, list(list(1,2), list(3,4)) sum2d)
  )
  testShouldSumASimple2dListIgnoringNilEntries := method (
    assertEquals(3, list(list(1,nil,2)) sum2d)
  )
)
Suite run

MatrixSuite := UnitTest clone do(
  my2dList := TwoDeeList clone
    
  setUp = method (
    my2dList dim(3,3)
    my2dList set(1,1,2)
    my2dList set(2,2,1)
    my2dList set(3,2,15)
  )
  testShouldCreateA2dList := method (
    assertNil(my2dList get(1,2))
    assertEquals(1, my2dList get(2,2))
    assertEquals(15, my2dList get(3,2))
  )
  
  testShouldWriteAndReadAMatrixAsAFile := method (
    my2dList toFile("matrix.csv")
    
    myOther2dList := TwoDeeList fromFile("matrix.csv")

    assertNil(myOther2dList get(1,2))     
    assertEquals(1, myOther2dList get(2,2))
    assertEquals(15, myOther2dList get(3,2))
    
    assertEquals(my2dList asString, myOther2dList asString)
    writeln(myOther2dList)
  )
)

MatrixSuite run
