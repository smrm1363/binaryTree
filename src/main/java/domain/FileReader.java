package domain;

import util.ReadPropertiesFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * This is a file reader for reading .txt files
 * This is a SingleTone
 */
public class FileReader {
    private static FileReader fileReader;

    private FileReader() {
    }

    public static FileReader getInstance() {
        if (fileReader == null)
            fileReader = new FileReader();
        return fileReader;
    }

    /**
     * @param path is the path of the Text file
     * @return a map of words and total number of each one in the file
     * @throws ApplicationException if the path has not valid format
     */
    public Map<String, Integer> readFileWordCount(String path) throws ApplicationException, IOException {
        String regularExpression = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+txt)+\\\\?";
        Pattern regexp = Pattern.compile(regularExpression);
        if (!regexp.matcher(path).find())
            throw new ApplicationException(ReadPropertiesFile.readKey("fileFormatExceptionMsg"));
        Map<String, Integer> result = new ConcurrentHashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            /**
             * I used Parallel because each line could be processed in a parallel way
             */
            stream.parallel().forEach(s ->
                    Arrays.stream(s.split(" ")).forEach(word ->
                    {
                        Optional<Integer> oldCount = Optional.ofNullable(result.get(word));
                        result.put(word, oldCount.orElse(0) + 1);
                    }));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
