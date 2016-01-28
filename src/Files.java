import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Files {
	private String path;
	private BufferedWriter bufferWriter;
	private BufferedReader bufferReader;

	public Files(String location) throws IOException {
		path = location;
		bufferReader = new BufferedReader(new FileReader(path));
	}

	public String[] read() throws IOException {
		try {

			int numberOfLines = getNumberOfLines();

			String[] textData = new String[numberOfLines];

			for (int i = 0; i < numberOfLines; i++) {
				textData[i] = bufferReader.readLine();

			}
			
			bufferReader.close();
			return textData;
		} catch (FileNotFoundException e) {
			throw new IOException();
		}

	}

	private int getNumberOfLines() throws IOException {
		FileReader f = new FileReader(path);
		BufferedReader b = new BufferedReader(f);
		int nblines = 0;
		while ((b.readLine()) != null) {
			nblines++;
		}
		b.close();

		return nblines;
	}

	public void openStreamWriter(boolean isAppend) throws IOException {
		bufferWriter = new BufferedWriter(new FileWriter(path, isAppend));
	}
	
	public void write(String string) throws IOException {
		bufferWriter.write(string);
		bufferWriter.newLine();
	}

	public void closeWriter() throws IOException {
		bufferWriter.close();
	}

}
