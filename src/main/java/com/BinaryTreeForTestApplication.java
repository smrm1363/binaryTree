package com;

import com.domain.ApplicationException;
import com.domain.BinaryTreeService;
import com.util.ReadPropertiesFile;

import java.io.IOException;

public class BinaryTreeForTestApplication {

    /**
     * This is the start point of the application
     * @param args the first element of this array should be file path of the .txt file
     * @throws ApplicationException
     * @throws IOException
     */
    public static void main(String[] args) throws ApplicationException, IOException {
        if(args.length <1)
            throw new ApplicationException(ReadPropertiesFile.readKey("parameterIsMandatory"));
        BinaryTreeService binaryTreeService = BinaryTreeService.getInstance();
        binaryTreeService.createAndPrintBinaryTreeFromFile(args[0]);
    }
}
