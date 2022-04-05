package ro.siit.multithreading;

import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class FestivalStatisticsThreadTest {

    @Test
    void FestivalTest() {
        FestivalGate gate = new FestivalGate();
        FestivalStatisticsThread statistics = new FestivalStatisticsThread(gate);
        //instantiate another statistics thread and assert exception is thrown
        Exception e1 = assertThrows(IllegalArgumentException.class, ()-> {
            new FestivalStatisticsThread(gate);;
        });
        assertEquals("Statistics thread is already instantiated!", e1.getMessage());
        //start statistics thread
        statistics.start();
        //generate 100 attendee threads
        FestivalAttendeeGenerator.generateAttendees(gate,100);
        //get the queue with attendees and assert is filled
        Queue<FestivalAttendeeThread> q = gate.transmitQueue();
        assertEquals(q.size(),100);
        //stop the satistics thread
        statistics.stop();
    }

}