import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleWeatherApp {
    public static void main(String[] args) {
        try {
            // 🔑 Step 1: API key and city
            String apiKey = "2b9e234ef3b56e4c94cdbbb2d407822e";
                                 
                                                                        
            String city = "Coimbatore";

            // 🔗 Step 2: API URL
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                                + city + "&appid=" + apiKey + "&units=metric";

            // 🌐 Step 3: HTTP Request
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 📥 Step 4: Read response (plain JSON text)
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonResponse = response.toString();
            System.out.println("Full JSON Response: " + jsonResponse);

            // ⚡ Step 5: Very simple string search (not full JSON parse!)
            // Extract "temp"
            String tempKey = "\"temp\":";
            int tempIndex = jsonResponse.indexOf(tempKey);
            String tempValue = jsonResponse.substring(tempIndex + tempKey.length(),
                                                      jsonResponse.indexOf(",", tempIndex));
            
            // Extract "humidity"
            String humidityKey = "\"humidity\":";
            int humIndex = jsonResponse.indexOf(humidityKey);
            String humidityValue = jsonResponse.substring(humIndex + humidityKey.length(),
                                                          jsonResponse.indexOf("}", humIndex));

            // ✅ Final Output
            System.out.println("\nWeather Report for " + city + ":");
            System.out.println("Temperature: " + tempValue + "°C");
            System.out.println("Humidity: " + humidityValue + "%");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}