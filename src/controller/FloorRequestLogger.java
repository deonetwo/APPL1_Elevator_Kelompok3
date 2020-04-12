package controller;
//import java.util.LinkedList;
import java.util.Queue;

import component.FloorRequest;

public class FloorRequestLogger {
    private Queue<FloorRequest> requestQueue;

    public FloorRequestLogger(Queue<FloorRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    public void AddFloorRequestToQueue(FloorRequest floorRequest) {
        this.requestQueue.add(floorRequest);
    }

    public Queue<FloorRequest> getRequestQueue() {
        return this.requestQueue;
    }
}
