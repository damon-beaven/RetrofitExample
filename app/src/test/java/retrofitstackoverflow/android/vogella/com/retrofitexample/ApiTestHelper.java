package retrofitstackoverflow.android.vogella.com.retrofitexample;

import org.apache.maven.artifact.ant.shaded.io.InputStreamFacade;
import org.apache.tools.ant.types.resources.URLResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ApiTestHelper {

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(Object object, String filename) throws Exception {
        final InputStream stream = object.getClass().getClassLoader().getResourceAsStream(filename);
        String returnValue = convertStreamToString(stream);
        stream.close();
        return returnValue;
    }
}
