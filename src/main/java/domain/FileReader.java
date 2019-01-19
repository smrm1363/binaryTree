package domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class FileReader {
    private static FileReader fileReader;

    private FileReader() {
    }
    public static FileReader getInstance()
    {
        if(fileReader==null)
            fileReader=new FileReader();
        return fileReader;
    }
    public Map<String,Integer> readFileWordCount(String path)
    {
        Map<String,Integer> result = new ConcurrentHashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            stream.parallel().forEach(s ->
            {
                Arrays.stream(s.split(" ")).forEach(word->
                {
                    Optional<Integer> oldCount = Optional.ofNullable(result.get(word));
                    result.put(word,oldCount.orElse(0)+1);
                });

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    return result;
    }
}
