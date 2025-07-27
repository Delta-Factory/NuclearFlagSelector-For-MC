package delta.cion;

import delta.cion.util.ConfigHandler;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class NuclearFlagSelector {
    private static String xms = null;
    private static String xmx = null;

    public static void main(String[] args) {
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        List<String> aList = bean.getInputArguments();

        for (String s : aList) {
            if (s.startsWith("-Xms")) xms = s;
            if (s.startsWith("-Xmx")) xmx = s;
        }

        System.out.printf("Minimal memory %s%n", xms);
        System.out.printf("Max memory %s%n", xmx);

        ConfigHandler cfg = new ConfigHandler(xms, xmx);
        try {
            CoreStarter.start(cfg.flagsReader());
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
