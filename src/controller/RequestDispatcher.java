package controller;

import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Set;

import component.FloorRequest;

public class RequestDispatcher {
    private Set<FloorRequest> requestQueue;

    public RequestDispatcher(Set<FloorRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    public boolean checkQueueForRequest() {
        return (requestQueue.isEmpty()) ? true : false;
    }

    public FloorRequest determineNextRequestToProcess() {
        Iterator<FloorRequest> it = requestQueue.iterator();
        FloorRequest head = it.next();
        it.remove();
        return head;
    }

    public Set<FloorRequest> getRequestQueue() {
        return this.requestQueue;
    }

    //checkForAvailableCabs()   ??????? Buat naon ieu
}