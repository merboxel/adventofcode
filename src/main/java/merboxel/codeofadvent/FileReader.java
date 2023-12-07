package merboxel.codeofadvent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReader {

    public static Scanner readFileAsScanner(int year, int day) throws IOException {

        //getAoCInputFile(Integer.toString(year),Integer.toString(day));

        //TODO Auto loading file from AoC
        URL resource = FileReader.class.getClassLoader().getResource(year+"/"+day+".txt");
        assert resource != null;
        return new Scanner(resource.openStream());
    }

    public static Scanner readFileAsScanner(int year, String day) throws IOException {

        //getAoCInputFile(Integer.toString(year),day);

        //TODO Auto loading file from AoC
        URL resource = FileReader.class.getClassLoader().getResource(year+"/"+day+".txt");
        assert resource != null;
        return new Scanner(resource.openStream());
    }

    private static void getAoCInputFile(String year, String day) throws IOException {

        if(null == FileReader.class.getClassLoader().getResource(year + "/" + day + ".txt"))
            return;

        if(null == FileReader.class.getClassLoader().getResource("session_token.txt"))
            throw new RuntimeException("Session token file not present");

        URL url = URL.of(URI.create("https://adventofcode.com/"+year+"/day/"+day+"/input"), null);

        Scanner sc = new Scanner(Objects.requireNonNull(FileReader.class.getClassLoader().getResource("session_token.txt")).openStream());
        String sessionToken = sc.nextLine();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Java 21 - Automation");
        con.setRequestProperty("Cookie","session="+sessionToken);

        int status = con.getResponseCode();
        Reader streamReader;

        if (status == 200) {
            streamReader = new InputStreamReader(con.getInputStream());

            ArrayList<String> response = readStream(streamReader);

            FileWriter myWriter = new FileWriter("src/main/resources/" + year + "/" + day + ".txt");
            myWriter.write(String.join("\r\n", response));
            myWriter.close();
        } else {
            streamReader = new InputStreamReader(con.getErrorStream());
            ArrayList<String> response = readStream(streamReader);
            System.out.println("Url cannot be reached");
            System.out.println(String.join("\r\n", response));
            throw new RuntimeException(String.join("\r\n", response));
        }
    }

    private static ArrayList<String> readStream(Reader streamreader) throws IOException {

        BufferedReader in = new BufferedReader(streamreader);
        String inputLine;
        ArrayList<String> content = new ArrayList<>();

        while (null != (inputLine = in.readLine())) {
            content.add(inputLine);
        }
        in.close();
        return content;
    }
}
