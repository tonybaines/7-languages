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
		// Collect the tastes of all prototypes in the heirarchy that 
		// _have_ a 'taste' slot
		tastes := self protos map(p, if(p hasSlot("taste"), p perform("taste")))
		tastes join(" and ")
	)
)

myLatte := Latte clone

write(Espresso taste .. "\n") // => Intense
write(HotMilk taste .. "\n") // => Creamy
write(myLatte taste .."\n") // => Intense and Creamy

myLatte appendProto(Espresso) // the same prototype can appear twice
write(myLatte taste .."\n") // => Intense and Creamy and Intense
