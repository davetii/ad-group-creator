import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {

    public static void main(String[] args)  {
        String inputFile = "perf.txt";
        String outputFile = "c:/temp/out.csv";
        Path inputPath = null;
        Path outputPath = Paths.get(outputFile);
        BufferedWriter writer = null;
        try {
            Files.deleteIfExists(outputPath);
            Files.createFile(outputPath);
        } catch (IOException e) { e.printStackTrace(); }

        try {
            inputPath = Paths.get(ClassLoader.getSystemResource(inputFile).toURI());
        } catch (URISyntaxException e) { e.printStackTrace(); }

        try {
            writer = Files.newBufferedWriter(outputPath);
        } catch (IOException e) { e.printStackTrace(); }

        try (Stream<String> lines = Files.lines(inputPath)) {

            for(String s: lines.collect(Collectors.toList())) {
                buildLines(writer, s);
            }
            writer.close();
        } catch (IOException ex) { }
        System.out.println("end process");
    }

    private static void buildLines(BufferedWriter writer, String s) throws IOException {
        writer.write(s + ",ostagroup");
        writer.newLine();
        writer.write(s + ",mnmsadmingroup");
        writer.newLine();
        writer.write(s + ",svsgroup");
        writer.newLine();
        writer.write(s + ",sysopsgroup");
        writer.newLine();
    }
}
