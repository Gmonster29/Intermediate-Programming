import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

class Movie {

    // Fields 
    String movieName;
    String movieNameForUrl;
    String movieScreenshotFilename;

    Movie(String name) {
        movieName = name;
        movieNameForUrl = getMovieNameForURL();
        movieScreenshotFilename = getMovieFilename();
    }

    /* Converts movieName to a URL-safe format */
    String getMovieNameForURL() {
        try {
            // Replace spaces with "%20" or "+" for URL encoding
            return URLEncoder.encode(movieName, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return movieName; // Fallback to original name in case of error
        }
    } 

    /* Formats the movie filename with an appropriate extension and replaces spaces */
    String getMovieFilename() {
        // Replace spaces with underscores
        String filename = movieName.replace(" ", "_");
        
        // Check if it already has an extension, if not, append ".jpg"
        if (!filename.endsWith(".jpg") && !filename.endsWith(".png")) {
            filename += ".jpg"; // Default to .jpg if no extension found
        }
        
        return filename;
    } 

    @Override
    public String toString() {
        return "Movie Name: " + movieName +
               "\nURL Safe Name: " + movieNameForUrl +
               "\nScreenshot Filename: " + movieScreenshotFilename + "\n";
    }

    public static void main(String[] args) {
        String[] movieNames = {
            "The Shawshank Redemption",
            "The Godfather",
            "The Dark Knight",
            "Pulp Fiction",
            "The Lord of the Rings: The Return of the King"
        };

        for (String name : movieNames) {
            Movie movie = new Movie(name);
            System.out.println(movie);
        }
    }
}
