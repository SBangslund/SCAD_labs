package bang.scad.libaws;

import java.util.DoubleSummaryStatistics;
import java.util.SortedSet;
import java.util.TreeSet;

import bang.scad.libaws.invokers.interfaces.Invoker;

import java.util.List;
import java.util.ArrayList;

public class TestHandler implements Runnable {

    private final Invoker invoker;
    private final String endpoint, payload;

    private int count = 100;
    private SortedSet<Integer> runtimes;
    private List<String> results;

    private DoubleSummaryStatistics statistics;

    public TestHandler(Invoker invoker, String endpoint, String payload) {
        this.endpoint = endpoint;
        this.invoker = invoker;
        this.payload = payload;
        runtimes = new TreeSet<>((e1, e2) -> {
            return e1 - e2;
        });
        results = new ArrayList<>();
    }
    
    public TestHandler(Invoker invoker, String endpoint, String payload, int count) {
        this(invoker, endpoint, payload);
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            long initTime = System.currentTimeMillis();
            results.add(payload != null 
                ? invoker.invoke(endpoint, payload) 
                : invoker.invoke(endpoint));
            long endTime = System.currentTimeMillis();
            runtimes.add((int) (endTime - initTime));
        }
        this.statistics = computeStatistics();
    }

    private DoubleSummaryStatistics computeStatistics() {
        return runtimes.stream()
            .mapToDouble(x -> x)
            .summaryStatistics();
    }

    public List<String> getResults() {
        return results;
    }

    public DoubleSummaryStatistics getStatistics() {
        return statistics;
    }
}
