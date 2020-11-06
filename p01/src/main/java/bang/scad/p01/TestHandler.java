package bang.scad.p01;

import bang.scad.p01.handlers.interfaces.Invoker;

import java.util.DoubleSummaryStatistics;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class TestHandler implements Runnable {

    private final Invoker invoker;
    private final String endpoint;

    private int count = 100;
    private SortedSet<Integer> runtimes;
    private List<String> results;

    private DoubleSummaryStatistics statistics;

    public TestHandler(Invoker invoker, String endpoint) {
        this.endpoint = endpoint;
        this.invoker = invoker;

        runtimes = new TreeSet<>((e1, e2) -> {
            return e1 - e2;
        });
        results = new ArrayList<>();
    }
    
    public TestHandler(Invoker invoker, String url, int count) {
        this(invoker, url);
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            long initTime = System.currentTimeMillis();
            results.add(invoker.invoke(endpoint));
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
