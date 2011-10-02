#!/usr/local/bin/io

Espresso := Object clone do (
	taste := "Intense"
)

HotMilk := Object clone do (
	taste := "Creamy"
)

Latte := Espresso clone do ( 
	appendProto(HotMilk) // mixin
	taste = method(
		tastes := self protos map(p, if(p hasSlot("taste"), p taste))
		tastes join(" and ")
	)
)

myLatte := Latte clone

write(Espresso taste .. "\n") // => Intense
write(HotMilk taste .. "\n") // => Creamy
write(myLatte taste .."\n") // => Intense and Creamy
