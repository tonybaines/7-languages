#!/usr/local/bin/io

Number fibi := method(
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

writeln(6 fibi)
writeln(6 fibr)
