package bang.scad.libaws.invokers.interfaces;

public interface Invoker {
    String invoke(String endpoint);
    String invoke(String endpoint, String payload);
}
