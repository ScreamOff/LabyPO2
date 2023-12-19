

import java.io.*;
import java.util.HashMap;
import java.util.Map;

    class Książka implements Serializable {
        private String tytuł;
        private String autor;

        public Książka(String tytuł, String autor) {
            this.tytuł = tytuł;
            this.autor = autor;
        }

        public String getTytuł() {
            return tytuł;
        }

        public String getAutor() {
            return autor;
        }

        @Override
        public String toString() {
            return "Książka{" +
                    "tytuł='" + tytuł + '\'' +
                    ", autor='" + autor + '\'' +
                    '}';
        }
    }

    public class BibliotekaApp {

        public static void main(String[] args) {
            // Tworzymy mapę na przechowywanie książek
            Map<Long, byte[]> biblioteka = new HashMap<>();

            // Dodajemy przykładowe książki do mapy
            dodajKsiążkę(123456789L, new Książka("Wiedźmin", "Andrzej Sapkowski"),biblioteka);
            dodajKsiążkę(987654321L, new Książka("1984", "George Orwell"),biblioteka);

            // Wyświetlamy zawartość biblioteki
            System.out.println("Zawartość biblioteki:");
            wyświetlBibliotekę(biblioteka);

            // Zapisujemy i odczytujemy zawartość z pliku (symulacja przechowywania w pliku)
            zapiszDoPliku(biblioteka, "biblioteka.txt");
            Map<Long, byte[]> wczytanaBiblioteka = wczytajZPliku("biblioteka.txt");

            // Wyświetlamy ponownie zawartość biblioteki po odczytaniu z pliku
            System.out.println("\nZawartość biblioteki po odczytaniu z pliku:");
            wyświetlBibliotekę(wczytanaBiblioteka);
        }

        private static void dodajKsiążkę(Long isbn, Książka książka,Map<Long, byte[]> biblioteka) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(książka);
                objectOutputStream.flush();

                byte[] serializedBook = outputStream.toByteArray();
                // Dodajemy do mapy
                biblioteka.put(isbn, serializedBook);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void wyświetlBibliotekę(Map<Long, byte[]> biblioteka) {
            biblioteka.forEach((isbn, serializedBook) -> {
                try (ByteArrayInputStream inputStream = new ByteArrayInputStream(serializedBook);
                     ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                    Książka książka = (Książka) objectInputStream.readObject();
                    System.out.println("ISBN: " + isbn + ", Książka: " + książka);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }

        private static void zapiszDoPliku(Map<Long, byte[]> biblioteka, String nazwaPliku) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
                outputStream.writeObject(biblioteka);
                System.out.println("Zapisano bibliotekę do pliku: " + nazwaPliku);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static Map<Long, byte[]> wczytajZPliku(String nazwaPliku) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
                return (Map<Long, byte[]>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return new HashMap<>();
            }
        }
    }

