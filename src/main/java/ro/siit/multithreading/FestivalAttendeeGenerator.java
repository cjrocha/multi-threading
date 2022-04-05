package ro.siit.multithreading;
/**
 * Generates a specific number of FestivalAttendeesThreads and starts them for 1 FestivalGate object.
 * It is used for simulation.
 *
 * @author  Andrei Chirila
 *
 */
class FestivalAttendeeGenerator {
    public static void generateAttendees(FestivalGate gate, Integer attendeesNumber) {
        Thread attendeeThread;
        for (int i=1; i<=attendeesNumber; ++i) {
            attendeeThread = new Thread(new FestivalAttendeeThread(TicketType.generateTicket(),gate));
            attendeeThread.start();
            try {
                attendeeThread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}