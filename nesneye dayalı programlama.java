import java.util.Scanner;

public class SinemaKayitSistemi {
    static final int FILM_KAPASITE = 10;
    static final int MUSTERI_KAPASITE = 20;

    static String[] filmler = new String[FILM_KAPASITE];
    static int[] sureler = new int[FILM_KAPASITE];
    static String[] turler = new String[FILM_KAPASITE];
    static int filmAdet = 0;

    static String[] isimler = new String[MUSTERI_KAPASITE];
    static String[] emailler = new String[MUSTERI_KAPASITE];
    static int musteriAdet = 0;

    static int[][] secimler = new int[MUSTERI_KAPASITE][FILM_KAPASITE];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int tercih;

        do {
            System.out.println("\n--- Sinema Kayıt Sistemi ---");
            System.out.println("1. Film Ekle");
            System.out.println("2. Filmleri Listele");
            System.out.println("3. Müşteri Ekle");
            System.out.println("4. Müşterileri Listele");
            System.out.println("5. Bilet Oluştur");
            System.out.println("6. Biletleri Listele");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            tercih = input.nextInt();
            input.nextLine();

            switch (tercih) {
                case 1 -> filmEkle(input);
                case 2 -> filmListele();
                case 3 -> musteriEkle(input);
                case 4 -> musteriListele();
                case 5 -> biletEkle(input);
                case 6 -> biletListele();
                case 0 -> System.out.println("Çıkılıyor...");
                default -> System.out.println("Geçersiz seçim!");
            }
        } while (tercih != 0);
    }

    static void filmEkle(Scanner input) {
        if (filmAdet >= FILM_KAPASITE) {
            System.out.println("Film kapasitesine ulaşıldı.");
            return;
        }
        System.out.print("Film adı: ");
        filmler[filmAdet] = input.nextLine();
        System.out.print("Süre (dk): ");
        sureler[filmAdet] = input.nextInt();
        input.nextLine();
        System.out.print("Tür: ");
        turler[filmAdet] = input.nextLine();
        filmAdet++;
        System.out.println("Film eklendi.");
    }

    static void filmListele() {
        if (filmAdet == 0) {
            System.out.println("Film bulunmuyor.");
            return;
        }
        System.out.println("--- Filmler ---");
        for (int i = 0; i < filmAdet; i++) {
            System.out.printf("%d - %s | Süre: %d dk | Tür: %s%n", i, filmler[i], sureler[i], turler[i]);
        }
    }

    static void musteriEkle(Scanner input) {
        if (musteriAdet >= MUSTERI_KAPASITE) {
            System.out.println("Müşteri kapasitesine ulaşıldı.");
            return;
        }
        System.out.print("İsim: ");
        isimler[musteriAdet] = input.nextLine();
        System.out.print("Email: ");
        emailler[musteriAdet] = input.nextLine();
        musteriAdet++;
        System.out.println("Müşteri eklendi.");
    }

    static void musteriListele() {
        if (musteriAdet == 0) {
            System.out.println("Müşteri yok.");
            return;
        }
        System.out.println("--- Müşteriler ---");
        for (int i = 0; i < musteriAdet; i++) {
            System.out.printf("%d - %s | Email: %s%n", i, isimler[i], emailler[i]);
        }
    }

    static void biletEkle(Scanner input) {
        if (musteriAdet == 0 || filmAdet == 0) {
            System.out.println("Önce müşteri ve film girilmelidir.");
            return;
        }

        musteriListele();
        System.out.print("Müşteri numarası: ");
        int mIndex = input.nextInt();

        filmListele();
        System.out.print("Film numarası: ");
        int fIndex = input.nextInt();

        if (mIndex >= 0 && mIndex < musteriAdet && fIndex >= 0 && fIndex < filmAdet) {
            secimler[mIndex][fIndex] = 1;
            System.out.println("Bilet oluşturuldu.");
        } else {
            System.out.println("Geçersiz numara.");
        }
    }

    static void biletListele() {
        System.out.println("--- Biletler ---");
        for (int i = 0; i < musteriAdet; i++) {
            System.out.printf("Müşteri: %s (%s) -> ", isimler[i], emailler[i]);
            boolean varMi = false;
            for (int j = 0; j < filmAdet; j++) {
                if (secimler[i][j] == 1) {
                    System.out.print(filmler[j] + " | ");
                    varMi = true;
                }
            }
            if (!varMi) {
                System.out.print("Henüz bilet alınmamış.");
            }
            System.out.println();
        }
    }
}
