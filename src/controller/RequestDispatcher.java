package controller;
//import java.util.LinkedList;
import java.util.Queue;

import component.FloorRequest;

public class RequestDispatcher {
    private Queue<FloorRequest> requestQueue;

    public RequestDispatcher(Queue<FloorRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    public boolean checkQueueForRequest() {
        return (requestQueue.isEmpty()) ? true : false;
    }

    public FloorRequest determineNextRequestToProcess() {
        return requestQueue.poll();
    }

    public Queue<FloorRequest> getRequestQueue() {
        return this.requestQueue;
    }

    //checkForAvailableCabs()   ??????? Buat naon ieu
}