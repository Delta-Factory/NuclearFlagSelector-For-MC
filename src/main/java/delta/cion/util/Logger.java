package delta.cion.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements AutoCloseable {

    private final File logsFolder = new File("BSAH_DEBUG");
    private File logList;

    public Logger() {
        createLogsFile();
    }

    private void createLogsFile() {
        if (!logsFolder.exists())
            logsFolder.mkdir();

        if (!logsFolder.isDirectory())
            logsFolder.mkdir();


        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd (HH-mm-ss)");
        String date = LocalDateTime.now().format(f);

        logList = new File(String.format("%s/%s.txt", logsFolder, date));
    }

    public void log(String content) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH-mm-ss");
        String time = LocalDateTime.now().format(f);

        try (FileWriter fw = new FileWriter(logList.getAbsoluteFile(), true)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(String.format("[%s] %s", time, content));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (logsFolder.length()==1) return;

        StringBuilder sb = new StringBuilder();
        for (String ch : logsFolder.list()) sb.append(ch);

        log(String.format("\nTotal Logs Files:\n%s", sb));
    }
}
