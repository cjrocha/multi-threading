package ro.siit.multithreading;

import java.util.logging.Logger;

/**
 * Generates a specific number of FestivalAttendeesThreads
 * and starts them for 1 FestivalGate object.
 * It is used for simulation.
 */
class FestivalAttendeeGenerator {
    private static Logger logger = Logger.getLogger("multithread.log");
    public static void generateAttendees(FestivalGate gate, Integer attendeesNumber) {
        Thread attendeeThread;
        for (int i=1; i<=attendeesNumber; ++i) {
            attendeeThread = new Thread(new FestivalAttendeeThread(TicketType.generateTicket(),gate));
            attendeeThread.start();
            logger.info("New thread(atendee) started");
            try {
                attendeeThread.sleep((long) (0.5*1000));
            } catch (InterruptedException e) {
                logger.severe("Interruption occurred!");
                e.printStackTrace();
            }
        }
    }
}