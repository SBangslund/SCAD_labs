package bang.scad.libaws.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bang.scad.libaws.readers.interfaces.StringReader;

public class JsonFileReader implements StringReader {

    private Logger logger = Logger.getLogger(JsonFileReader.class.getName());

    @Override
    public String read(String path) {
		StringBuilder builder = new StringBuilder();
		File file = new File(path);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			char[] buffer = new char[10];
			while(reader.read(buffer) != -1) {
				builder.append(new String(buffer));
				buffer = new char[10];
			}
			reader.close();
		} catch(IOException e) {
			logger.log(Level.WARNING, "Wrong path: " + file.getAbsolutePath());
        }
        return builder.toString();
    }
}
