#!/usr/local/bin/io

Espresso := Object clone do (
	taste := "Intense"
)

HotMilk := Object clone do (
	taste := "Creamy"
)

Latte := Espresso clone do ( 
	appendProto(HotMilk)
	taste = method(
		tastes := self protos map(p, if(p hasSlot("taste"), p taste))
		tastes join(" and ")
	)
)

myLatte := Latte clone

write(myLatte taste .."\n")
