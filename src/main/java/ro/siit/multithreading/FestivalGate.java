package ro.siit.multithreading;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * FestivalGate contains a queue that holds the attendees that pass through gate.
 *
 * @author  Andrei Chirila
 *
 */
public class FestivalGate<FestivalAttendeeThread> {
    private Queue<FestivalAttendeeThread> gateQueue = new PriorityQueue<>();

    public void addToGate(FestivalAttendeeThread t) {
        this.gateQueue.add(t);
    }

    public FestivalAttendeeThread peekFromGate() {
        return this.gateQueue.peek();
    }

    public Queue<FestivalAttendeeThread> transmitQueue() {
        Queue<FestivalAttendeeThread> transmittedQueue = new PriorityQueue<>();
        for (FestivalAttendeeThread t : this.gateQueue) {
            transmittedQueue.add(t);
        }
        return transmittedQueue;
    }
}
