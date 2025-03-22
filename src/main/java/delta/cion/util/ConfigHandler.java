package delta.cion.util;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ConfigHandler {

    private final String xms;
    private final String xmx;

    public ConfigHandler(String xms, String xmx) {
        saveResource("README.txt");
        saveResource("settings.properties");
        saveResource("startup.txt");

        this.xms = xms;
        this.xmx = xmx;
    }

//    public void propReader() {
//
//    }

    public String flagsReader() {
        String standardFlags = "java -Xms000M -Xmx0000M -jar server.jar nogui";

        try {
            Path path = Paths.get("startup.txt");
            String s = Files.readString(path);

            if (Objects.equals(s, standardFlags) || s.isBlank()) {
                System.out.println("Use standard flags.");
                return startupHadler(standardFlags);
            }
            else return startupHadler(String.format("java -Xms000M -Xmx0000M %s nogui", s));
        } catch (IOException e) {
            System.out.println("Startup file not found");
            return startupHadler(standardFlags);
        }
    }

    private String startupHadler(String s) {
        s = s.replace("-Xms000M", xms);
        s = s.replace("-Xmx0000M", xmx);
        System.out.println("Set startup to: "+s);
        return s;
    }

    private static void saveResource(String s) {
        File file = new File(s);
        if (file.exists()) return;

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(s);

        try(OutputStream os = new FileOutputStream(file)) {
            assert is != null;
            IOUtils.copy(is, os);
        } catch (Exception ignored) {}
    }
}
