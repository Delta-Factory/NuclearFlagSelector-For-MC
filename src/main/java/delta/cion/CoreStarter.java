package delta.cion;

import java.io.*;

public class CoreStarter {

    private static Process p;

    public static void start(String startup) throws IOException {
        String corePath = corePath(startup.split(" "));

        assert corePath != null;
        File core = new File(corePath);

        if (core.isDirectory()) {
            System.out.println("Core is a directory! Stopping the program.\nPlease - Create a YOUR_CORE_NAME.jar file");
            throw new IOException();
        }

        if (!core.exists()) {
            System.out.println("Core not exists.\nPlease - Create a YOUR_CORE_NAME.jar file");
            throw new IOException();
        }

        try {
            Thread jarThread = getThread(corePath, startup);
            jarThread.start();
        } catch (Exception ignored) {}
    }

    private static Thread getThread(String corePath, String startup) {
        Runnable jarExecution = () -> {
            try {
                ProcessBuilder pb = new ProcessBuilder(startup.split(" "));
                pb.redirectErrorStream(true);

                File coreFile = new File(corePath);
                File directory = coreFile.getParentFile();
                if (directory != null && directory.exists()) {
                    pb.directory(directory);
                    System.out.println("Core working dir: " + directory.getAbsolutePath());
                }

                p = pb.start();

                ConsoleHandler ch = new ConsoleHandler(p);
                ch.start();
                p = ch.getProcess();

                p.waitFor();
                int exitCode = p.exitValue();
                System.out.printf("Core stopped with code %s%n", exitCode);
                System.exit(0);
            } catch (Exception ignored) {}
        };

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (p != null && p.isAlive()) {
                p.destroyForcibly();
                System.out.println("Core process forcibly terminated.");
            }
        }));

        return new Thread(jarExecution);
    }

    private static String corePath(String[] ss) {
        for (int i = 0; i < ss.length; i++) {
            if ("-jar".equals(ss[i]) && i + 1 < ss.length) {
                String path = ss[i + 1];
                System.out.println("Core path set to: " + path);
                return path;
            }
        }
        return null;
    }
}
