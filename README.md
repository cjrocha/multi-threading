# Festival Statistics

Every Festival has a gate, where people have to show their tickets.

Write an application where every person is represented by a special Thread that is instantiated with random ticket data. 
Possible ticket types: full, full-vip, free-pass, one-day, one-day-vip


FestivalGate gate = new FestivalGate();

TicketType ticketType = // this should be enum value randomly generated.

FestivalAttendeeThread festivalAttendee = new FestivalAttendeeThread(ticketType, gate);

Every attendee validates the ticket, and the ticket-data is kept is a queue.

Meanwhile, every 5 seconds a FestivalStatisticsThread wakes up and reads the new data from the gate and generates statistics:

e.g

100 people entered

57 people have full tickets

30 have free passes

3 have full VIP passes

10 have one-day passes

0 have one-day VIP passes

FestivalStatisticsThread statsThread = new FestivalStatisticsThread(gate);

statsThread.start();

Note:

There can be only one statsThread
Stats thread reads from the gate-data only if it's not empty
There can be unlimited attendee threads
Attendee threads can add data to the gate regardless of the gate data being empty or full.
The app must randomly generate multiple attendee threads - at least 100
Use Thread.sleep in both thread types.