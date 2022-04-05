package ro.siit.multithreading;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Logger;

/**
 * Festival Statistics Thread generates statistics
 * of FestivalGate every 5 seconds.
 */
public class FestivalStatisticsThread extends Thread {
    private static Logger logger = Logger.getLogger("multithread.log");
    private static FestivalStatisticsThread instance = null;
    private FestivalGate gate;
    private Queue<FestivalAttendeeThread> queue = new PriorityQueue<>();

    public FestivalStatisticsThread(FestivalGate gate)
    {
        if (instance != null) {
            logger.severe("Statistics thread is already instantiated!");
            throw new IllegalArgumentException("Statistics thread is already instantiated!");
        }
        else {
            instance = new FestivalStatisticsThread();
            this.gate = gate;
        }
    }

    private FestivalStatisticsThread() {
    }

    @Override
    public void run() {
       while(true) {
           try {
               logger.info("Sleeping for 5 seconds until next reading of gate entries");
               Thread.sleep(5*1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           generateStatistics();
       }
    }

    private void generateStatistics() {
        Integer countAttendees = 0;
        Integer count_Full = 0;
        Integer count_Full_Vip = 0;
        Integer count_Free_Pass = 0;
        Integer count_One_Day = 0;
        Integer count_One_Day_Vip = 0;
        queue.clear();
        queue = this.gate.transmitQueue();
        if (gate.peekFromGate() != null) {
            for (FestivalAttendeeThread attendee:queue) {
                if(attendee.getTicketType().equals(TicketType.FULL)) {
                    count_Full++;
                }
                if(attendee.getTicketType().equals(TicketType.FULL_VIP)) {
                    count_Full_Vip++;
                }
                if(attendee.getTicketType().equals(TicketType.FREE_PASS)) {
                    count_Free_Pass++;
                }
                if(attendee.getTicketType().equals(TicketType.ONE_DAY)) {
                    count_One_Day++;
                }
                if(attendee.getTicketType().equals(TicketType.ONE_DAY_VIP)) {
                    count_One_Day_Vip++;
                }
            }
            logger.info("Static was generated successfully");
            System.out.println(queue.size()+" people entered"+"\n"
                    +count_Full+" people have full tickets"+"\n"
                    +count_Full_Vip+" people have full VIP tickets"+"\n"
                    +count_Free_Pass+" people have free passes"+"\n"
                    +count_One_Day+" have one-day passes"+"\n"
                    +count_One_Day_Vip+" have one-day VIP passes"+"\n"
            );
        }
    }
}
