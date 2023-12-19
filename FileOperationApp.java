import java.io.File;

public class FileOperationApp {

    public static void main(String[] args) {
        // Podaj ścieżkę do katalogu, który chcesz przetworzyć
        String directoryPath = "ścieżka/do/twojego/katalogu";

        File directory = new File(directoryPath);

        // Sprawdź czy ścieżka wskazuje na istniejący katalog
        if (directory.exists() && directory.isDirectory()) {
            // a) Wylicz rozmiar katalogu
            long directorySize = calculateDirectorySize(directory);
            System.out.println("Rozmiar katalogu: " + directorySize + " bajtów");

            // b) Wyświetl pliki i katalogi spełniające warunek (np. zaczynające się od określonej litery)
            String condition = "A"; // Przykładowy warunek
            System.out.println("\nPliki i katalogi spełniające warunek '" + condition + "':");
            displayFilesAndDirectories(directory, condition);

            // c) Poda liczbę plików, katalogów oraz informacje o dostępności
            int[] fileStats = countFileStats(directory);
            System.out.println("\nLiczba plików: " + fileStats[0]);
            System.out.println("Liczba katalogów: " + fileStats[1]);
            System.out.println("Liczba plików do odczytu: " + fileStats[2]);
            System.out.println("Liczba plików do zapisu: " + fileStats[3]);
            System.out.println("Liczba plików do wykonania: " + fileStats[4]);
        } else {
            System.out.println("Podana ścieżka nie wskazuje na istniejący katalog.");
        }
    }

    // a) Rekurencyjnie wylicza rozmiar katalogu
    private static long calculateDirectorySize(File directory) {
        long size = 0;
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    size += calculateDirectorySize(file);
                } else {
                    size += file.length();
                }
            }
        }

        return size;
    }

    // b) Rekurencyjnie wyświetla pliki i katalogi spełniające warunek
    private static void displayFilesAndDirectories(File directory, String condition) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    displayFilesAndDirectories(file, condition);
                } else {
                    if (file.getName().startsWith(condition)) {
                        System.out.println(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    // c) Rekurencyjnie podaje liczbę plików, katalogów oraz informacji o dostępności
    private static int[] countFileStats(File directory) {
        int[] stats = new int[5];
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    stats[1]++; // Zwiększ liczbę katalogów
                    countFileStats(file); // Rekurencyjnie przetwarzaj podkatalogi
                } else {
                    stats[0]++; // Zwiększ liczbę plików

                    if (file.canRead()) {
                        stats[2]++; // Zwiększ liczbę plików do odczytu
                    }
                    if (file.canWrite()) {
                        stats[3]++; // Zwiększ liczbę plików do zapisu
                    }
                    if (file.canExecute()) {
                        stats[4]++; // Zwiększ liczbę plików do wykonania
                    }
                }
            }
        }

        return stats;
    }
}
