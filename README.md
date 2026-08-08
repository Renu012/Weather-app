 Weather-app

A simple Java console application that fetches and displays real-time weather data (temperature and humidity) for a given city, using the [OpenWeatherMap API](https://openweathermap.org/api).

## Features

- Fetches live weather data from OpenWeatherMap's Current Weather API
- Displays temperature (°C) and humidity (%) for a specified city
- Uses only Java's built-in `java.net` and `java.io` libraries — no external dependencies
- Prints the full raw JSON response for reference/debugging

## How It Works

1. Builds a request URL to OpenWeatherMap's `/data/2.5/weather` endpoint using a city name, API key, and metric units.
2. Sends an HTTP GET request via `HttpURLConnection`.
3. Reads the JSON response as plain text.
4. Extracts the `temp` and `humidity` values using simple string searching (not a full JSON parser).
5. Prints a formatted weather report to the console.

## Requirements

- Java Development Kit (JDK) 8 or later
- A free API key from [OpenWeatherMap](https://openweathermap.org/appid)
- Internet connection

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Renu012/Weather-app.git
cd Weather-app
```

### 2. Set your API key

⚠️ **Important:** Don't hardcode your API key directly in the source file, especially if the repo is public. Anyone can read it and use your quota (or run up charges if you're on a paid plan).

Instead, pass it in as an environment variable or a command-line argument. For example, using an environment variable:

```java
String apiKey = System.getenv("OPENWEATHER_API_KEY");
```

Then run with:

```bash
export OPENWEATHER_API_KEY=your_api_key_here   # macOS/Linux
set OPENWEATHER_API_KEY=your_api_key_here      # Windows (cmd)
```

If you've already committed a real key to this repo, treat it as compromised — regenerate a new key from your [OpenWeatherMap account](https://home.openweathermap.org/api_keys) and update your code/environment accordingly.

### 3. Set the city (optional)

By default, the program looks up weather for a hardcoded city (e.g., `"Coimbatore"`). Change the `city` variable in `SimpleWeatherApp.java`, or better, read it from `args[0]` or an environment variable so it's configurable without editing the code.

### 4. Compile

```bash
javac SimpleWeatherApp.java
```

### 5. Run

```bash
java SimpleWeatherApp
```

### Example Output

```
Full JSON Response: {"coord":{"lon":76.96,"lat":11.02},"weather":[...],...}

Weather Report for Coimbatore:
Temperature: 28.5°C
Humidity: 65%
```

## Project Structure

```
Weather-app/
└── SimpleWeatherApp.java   # Main source file: fetches and parses weather data
```

## Known Limitations

- **No real JSON parsing** — the app uses raw string indexing (`indexOf`/`substring`) to pull out `temp` and `humidity`. This is fragile and can break if the API response format changes slightly. Consider using a proper JSON library like [org.json](https://mvnrepository.com/artifact/org.json/json) or [Gson](https://github.com/google/gson) for more reliable parsing.
- **City is hardcoded** — no command-line or interactive input yet.
- **No error handling for API failures** — e.g., invalid city names or bad API keys will produce unclear output or exceptions.

## Possible Improvements

- Parse JSON properly using a library instead of manual string searches
- Accept the city name as user input or a command-line argument
- Add more weather details (wind speed, description, sunrise/sunset, etc.)
- Add input validation and clearer error messages for invalid cities or network failures
- Load the API key securely from an environment variable or config file (see above)

## License

No license specified. Feel free to fork and adapt for your own use, or add a license of your choice.
