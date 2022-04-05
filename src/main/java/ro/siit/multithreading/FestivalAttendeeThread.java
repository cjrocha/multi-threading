package ro.siit.multithreading;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * FestivalAtendeeThread implements Runnable instantiated with TicketType, gate.
 * Adds current instance to FestivalGate in method run()
 */
public class FestivalAttendeeThread extends Person implements Runnable, Comparable<FestivalAttendeeThread> {
    private static Logger logger = Logger.getLogger("multithread.log");
    private TicketType ticketType;
    private FestivalGate gate;

    public FestivalAttendeeThread(TicketType ticketType, FestivalGate gate) {
        this.gate = gate;
        //ticket validation
        if(ticketType.equals(TicketType.FREE_PASS)||
                ticketType.equals(TicketType.FULL)||
                ticketType.equals(TicketType.FULL_VIP)||
                ticketType.equals(TicketType.ONE_DAY)||
                ticketType.equals(TicketType.ONE_DAY_VIP)) {
            this.ticketType = ticketType;
            logger.info("Ticket was validated");
        }
        else {
            logger.severe("Ticket is not valid");
            throw new IllegalArgumentException("Ticket is not valid.");
        }
    }

    @Override
    public void run() {
        gate.addToGate(this);
        logger.info("New attendee has entered the gate");
        System.out.println("Entering gate");
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    @Override
    public String toString() {
        logger.info("We recorded a ticket type: "+ticketType);
        return "FestivalAttendeeThread{" +
                "ticketType=" + ticketType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FestivalAttendeeThread that = (FestivalAttendeeThread) o;
        return ticketType == that.ticketType &&
                Objects.equals(gate, that.gate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketType, gate);
    }

    //the queue will be sorted depending on the thread ID
    @Override
    public int compareTo(FestivalAttendeeThread o) {
        return this.ticketType.compareTo(o.ticketType);
    }
}

