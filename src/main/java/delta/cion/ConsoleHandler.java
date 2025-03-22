package delta.cion;

import delta.cion.util.Logger;

import java.io.*;

public class ConsoleHandler implements AutoCloseable {

    private Process p;

    private final Logger LOGGER = new Logger();

    public ConsoleHandler(Process process) {
        this.p = process;
    }

    public void setInput() throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream os = p.getOutputStream();
        PrintWriter writer = new PrintWriter(os, true);

        String input;
        while ((input = consoleReader.readLine()) != null) {
            LOGGER.log(input);
            writer.println(input);
        }
    }

    public void setOutput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public Process getProcess() {
        return p;
    }

    public void start() {
        new Thread(() -> {
            try {
                setOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                setInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void close() {
        LOGGER.close();
    }
}