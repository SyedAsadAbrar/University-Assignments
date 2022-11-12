#This program will create two nodes and send data via UDP from one node to another over a duplex-link

#Creating Simulator Object
set ns [new Simulator]

$ns rtproto DV

#creating Two color classes for ns object to distinguish the traffic coming from various sources
#$ns color 0 blue
#$ns color 1 red

#Creating nam file
set nf [open out.nam w]
$ns namtrace-all $nf

#Finish procedure to open file using nam to visulaize our network
proc finish {} {
        global ns nf
        $ns flush-trace
        close $nf
        exec nam out.nam
        exit 0
        }

#Defining Nodes
for {set a 0} {$a < 7} {incr a} {
	set nodes($a) [$ns node]
	#$nodes($a) color green
	#$nodes($a) shape circle
}

#Defining Duplex link between nodes having bandwidth of 512Kbps, delay of 5ms and Droptail Queue
for {set a 0} {$a < 7} {incr a} {
	$ns duplex-link $nodes($a) $nodes([expr ($a+1)%7]) 512Kb 5ms DropTail
	#Limiting the queue to only 10 packets 
	#$ns queue-limit $nodes($a) $nodes([expr ($a+1)%7]) 10
}

$ns duplex-link-op $nodes(0) $nodes(1) queuePos 1
$ns duplex-link-op $nodes(0) $nodes(6) queuePos 1

#Creating UDP agent
set udp0 [new Agent/UDP]

#Attaching the UDP agent with n0
$ns attach-agent $nodes(0) $udp0

#Creating Null agent
set null0 [new Agent/Null]

#Attaching the NULL agent with node 3
$ns attach-agent $nodes(3) $null0

#Connecting both udp0 and null0 agents for transferring data between node 0 and node 3
$ns connect $udp0 $null0

#Specifying the CBR agent to generate the traffic over udp0 agent
set cbr0 [new Application/Traffic/CBR]

#Each packet having 1024 bytes  
$cbr0 set packetSize_ 1024

#EAch packet will be generated after 10ms i.e., 100 packets per second
$cbr0 set interval_ 0.01

#Attachig cbr0 with udp0
$cbr0 attach-agent $udp0

# Bringing the link down between node 2 and node 3 at 0.4
$ns rtmodel-at 0.4 down $nodes(2) $nodes(3)

# Bringing the link up between node 2 and node 3 at 1.0
$ns rtmodel-at 1.0 up $nodes(2) $nodes(3)

# #Starting the cbr0 at 0.02 simulaio time
$ns at 0.02 "$cbr0 start"

# #Stoping the cbr0 at 1.5 simulation time
$ns at 1.5 "$cbr0 stop"

$ns at 2.0 "finish"
$ns run