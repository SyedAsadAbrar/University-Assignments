puts "Enter x."
set x [gets stdin]
for {set y 0} {$x <= 4} {incr y} {
	puts "x is less than or equal to 4."
	puts "Enter x again."
	set x [gets stdin]
}
puts "Your entered value of x is $x."

set y $x

# check if odd or even
if {$x % 2 == 1} {
	set x 3
	puts "Your entered number $y is odd."
} else {
	set x 4
	puts "Your entered number $y is even."
}

for {set a 0} {$x <= $y} {incr a} {
	puts "$x"
	set x [expr $x + 2]
}

