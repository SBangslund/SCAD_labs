package bang.scad.p01.models;

import java.util.Map;

public class RuntimeInfo {
    private final long availProc, freeMem, maxMem, totalMem;
    private final Map<String, String> systemProperties;
    private final String version;

    public RuntimeInfo(Map<String, String> systemProperties, int processors, long freeMem, long maxMem, long totalMem,
            String version) {
        this.systemProperties = systemProperties;
        this.availProc = processors;
        this.freeMem = freeMem;
        this.maxMem = maxMem;
        this.totalMem = totalMem;
        this.version = version;
    }

    public long getAvailProc() {
        return this.availProc;
    }

    public long getFreeMem() {
        return this.freeMem;
    }

    public long getMaxMem() {
        return this.maxMem;
    }

    public long getTotalMem() {
        return this.totalMem;
    }

    public Map<String, String> getSystemProperties() {
        return this.systemProperties;
    }

    public String getVersion() {
        return this.version;
    }
}
