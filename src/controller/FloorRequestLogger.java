package controller;

//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Set;

import component.FloorRequest;

public class FloorRequestLogger {
    private Set<FloorRequest> requestQueue;

    public FloorRequestLogger(Set<FloorRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    public void AddFloorRequestToQueue(FloorRequest floorRequest) {
        this.requestQueue.add(floorRequest);
    }

    public Set<FloorRequest> getRequestQueue() {
        return this.requestQueue;
    }
}
