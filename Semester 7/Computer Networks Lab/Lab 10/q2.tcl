#This program will create two nodes and send data via UDP from one node to another over a duplex-link

#Creating Simulator Object
set ns [new Simulator]

$ns rtproto DV

#creating Two color classes for ns object to distinguish the traffic coming from various sources
$ns color 0 blue
$ns color 1 red

#Creating nam file
set nf [open out.nam w]
$ns namtrace-all $nf

#Finish procedure to open file using nam to visulaize our network
proc finish {} {
        global ns nf
        $ns flush-trace
        close $nf
        exec nam out.nam &
        exit 0
}

#Defining Nodes
for {set a 0}  {$a < 7} {incr a} {
   set nodes($a) [$ns node]
}

#Defining Duplex link between nodes having bandwidth of 512Kbps, delay of 5ms and Droptail Queue
for {set a 0} {$a < 6} {incr a} {
	$ns duplex-link $nodes(0) $nodes([expr ($a+1)]) 0.512Mb 10ms SFQ
}

$ns duplex-link-op $nodes(0) $nodes(1) orient left-up
$ns duplex-link-op $nodes(0) $nodes(2) orient right-up
$ns duplex-link-op $nodes(0) $nodes(3) orient right
$ns duplex-link-op $nodes(0) $nodes(4) orient right-down
$ns duplex-link-op $nodes(0) $nodes(5) orient left-down
$ns duplex-link-op $nodes(0) $nodes(6) orient left

#Specifying the UDP agent
set udp0 [new Agent/UDP]

#Specifying udp traffic to have red color as defined in the second line of program
$udp0 set fid_ 0

#Attaching the UDP agent with n0
$ns attach-agent $nodes(2) $udp0

#Specifying the Null agent
set null0 [new Agent/Null]

#Attaching the NULL agent with n1
$ns attach-agent $nodes(5) $null0

#Connecting both udp0 and null0 agents for transferring data between n0 and n1
$ns connect $udp0 $null0
 
#Specifying the CBR agent to generate the traffic over udp0 agent
set cbr0 [new Application/Traffic/CBR]

#Each packet having 500 bytes          						#Rate = Packet_Size / Time
$cbr0 set packetSize_ 500							

#EAch packet will be generated after 5ms i.e., 200 packets per second
$cbr0 set rate 0.256Mb 

#Attaching cbr0 with udp0
$cbr0 attach-agent $udp0

#Starting the cbr0 at 0.5 simulaio time
$ns at 0.2 "$cbr0 start"

#Stoping the cbr0 at 4.5 simulation time
$ns at 1.3 "$cbr0 stop"

#Creating a TCP agent and connecting it to n0 (Basically it defines source node of TCP)
set tcp0 [new Agent/TCP]
$ns attach-agent $nodes(1) $tcp0        

$tcp0 set fid_ 1

#Creating a Sink Agent and attaching it to n1
set sink0 [new Agent/TCPSink]
$ns attach-agent $nodes(4) $sink0

#Connecting TCP agent with Sink agent
$ns connect $tcp0 $sink0

#Creating FTP agent for traffic and attching it to tcp0
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

#Starting the FTP Traffic
$ns at 0.1 "$ftp0 start"
$ns at 1.5 "$ftp0 stop"

$ns rtmodel-at 0.5 down $nodes(0) $nodes(5)
$ns rtmodel-at 0.7 down $nodes(0) $nodes(4)
$ns rtmodel-at 0.9 up $nodes(0) $nodes(5)
$ns rtmodel-at 1.2 up $nodes(0) $nodes(4)

$ns at 2.0 "finish"

$ns run
