import java.util.Random;

// 1. ENCAPSULATION: 

class Song {
    private String title;
    private String artist;

    // Constructor to initialize a Song object
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    // Getters for encapsulation
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    // Overriding toString to provide a readable format of the Song

    public String toString() {
        return title + " by " + artist;
    }
}

// * 2. AGGREGATION (HAS-A Relationship): The Playlist class contains an array
// of Song objects, demonstrating a "has-a" relationship where a playlist has
// songs.

class Playlist {
    // 3. ARRAY OF OBJECTS: Managing a collection of Song instances
    private Song[] songs;
    private int numberOfSongs;

    // Constructor initializes the playlist with a specific capacity
    public Playlist(int capacity) {
        songs = new Song[capacity];
        numberOfSongs = 0;
    }

    // Functionality: Add a song to the playlist
    public void addSong(Song song) {
        if (numberOfSongs < songs.length) {
            songs[numberOfSongs] = song;
            numberOfSongs++;
            System.out.println("Added to playlist: " + song.toString());
        } else {
            System.out.println("Cannot add '" + song.getTitle() + "'. Playlist is full!");
        }
    }

    // Functionality: Display the current queue
    public void displayQueue() {
        System.out.println("\n--- Current Playlist Queue ---");
        if (numberOfSongs == 0) {
            System.out.println("The playlist is empty.");
            return;
        }

        int trackNumber = 1;

        // * 4. ENHANCED FOR-LOOP TRAVERSAL:
        for (Song song : songs) {
            // Null check is necessary because the array capacity might be
            // larger than the actual number of added songs
            if (song != null) {
                System.out.println(trackNumber + ". " + song.toString());
                trackNumber++;
            }
        }
        System.out.println("------------------------------");
    }

    // * 5. RANDOMIZATION LOGIC: The shuffle method implements the Fisher-Yates
    // algorithm to randomize the order of songs in the playlist.

    public void shuffle() {
        System.out.println("\n[Action] Shuffling the playlist...");
        Random random = new Random();

        // Loop backwards through the populated portion of the array
        for (int i = numberOfSongs - 1; i > 0; i--) {
            // Generate a random index between 0 and i (inclusive)
            int j = random.nextInt(i + 1);

            // Swap the current element with the randomly selected element
            Song temp = songs[i];
            songs[i] = songs[j];
            songs[j] = temp;
        }
    }
}

// * Main Class to test the Music Playlist Management System
public class MusicPlayer {
    public static void main(String[] args) {
        System.out.println("=== Welcome to the Music Playlist Manager ===\n");

        // Create a new playlist with a capacity of 5 songs
        Playlist myPlaylist = new Playlist(5);

        // Create independent Song objects
        Song song1 = new Song(" Late-Night Melancholy", "Arjit Singh");
        Song song2 = new Song("High-Energy", "Badshah");
        Song song3 = new Song("Timeless Retro Classics", "Kishore Kumar");
        Song song4 = new Song(" Soulful Sufi & Qawwali", "Nusrat Fateh Ali Khan");
        Song song5 = new Song(" Golden 90s Romance", "Kumar Sanu");

        // Add songs to the playlist
        myPlaylist.addSong(song1);
        myPlaylist.addSong(song2);
        myPlaylist.addSong(song3);
        myPlaylist.addSong(song4);
        myPlaylist.addSong(song5);

        // Attempting to add a 6th song to a capacity-5 playlist will trigger the full
        // condition
        Song song6 = new Song("Smells Like Teen Spirit", "Nirvana");
        myPlaylist.addSong(song6);

        // Display the queue chronologically
        myPlaylist.displayQueue();

        // Shuffle the playlist
        myPlaylist.shuffle();

        // Display the newly randomized queue
        myPlaylist.displayQueue();
    }
}