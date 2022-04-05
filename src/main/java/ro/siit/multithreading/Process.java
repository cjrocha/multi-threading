package ro.siit.multithreading;

import java.util.logging.Logger;

/**
 * Festival gate code that treats each atendee
 * as a thread and generates a report of entries
 * and type of tickets every 5 seconds
 * @author Andrei Chirila
 */
public class Process {
    private static Logger logger = Logger.getLogger("multithread.log");
    public static void main(String[] args) {
        FestivalGate gate = new FestivalGate();
        FestivalStatisticsThread statistics = new FestivalStatisticsThread(gate);
        statistics.start();
        logger.info("All engines started!");
        FestivalAttendeeGenerator.generateAttendees(gate,20);
        statistics.stop();
    }
}
