package com.mycompany.main;

import model.Member;
import model.Progress;
import model.User;
import service.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        AuthService auth = new AuthService();
        MemberService memberService = new MemberService();
        ProgressService progressService = new ProgressService();

        while (true) {
            printFrame("SISTEM MANAJEMEN PROGRESS GYM");
            System.out.println("1. Login");
            System.out.println("2. Registrasi Akun");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            String pilih = in.nextLine().trim();

            if ("1".equals(pilih)) {
                submenuLogin(auth, memberService, progressService);
            } else if ("2".equals(pilih)) {
                handleRegister(auth);
            } else if ("3".equals(pilih)) {
                System.out.println("Program selesai. Terima kasih.");
                return;
            } else {
                System.out.println("Menu tidak valid.");
            }
        }
    }

    private static void submenuLogin(AuthService auth, MemberService memberService, ProgressService progressService) {
        while (true) {
            printFrame("MENU LOGIN");
            System.out.println("1. Login User");
            System.out.println("2. Login Admin");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            String p = in.nextLine().trim();

            try {
                if ("1".equals(p)) {
                    System.out.print("Username: ");
                    String u = in.nextLine();
                    System.out.print("Password: ");
                    String pw = in.nextLine();
                    Optional<User> logged = auth.login(u, pw, "USER");
                    if (logged.isPresent()) {
                        System.out.println("Selamat datang kembali, "
                                + logged.get().getUsername()
                                + "! Semoga latihanmu konsisten ya.");
                        menuUser(logged.get(), progressService);
                        return;
                    } else {
                        System.out.println("Login gagal.");
                    }
                } else if ("2".equals(p)) {
                    System.out.print("Username: ");
                    String u = in.nextLine();
                    System.out.print("Password: ");
                    String pw = in.nextLine();
                    Optional<User> logged = auth.login(u, pw, "ADMIN");
                    if (logged.isPresent()) {
                        System.out.println("Halo Admin " + logged.get().getUsername()
                                + ", siap kelola member hari ini?");
                        menuAdmin(memberService);
                        return;
                    } else {
                        System.out.println("Login gagal.");
                    }
                } else if ("0".equals(p)) {
                    return;
                } else {
                    System.out.println("Menu tidak valid.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleRegister(AuthService auth) {
        try {
            System.out.print("Buat username: ");
            String u = in.nextLine();
            System.out.print("Buat password: ");
            String p = in.nextLine();
            if (auth.register(u, p)) {
                System.out.println("Registrasi berhasil. Silakan login.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void menuAdmin(MemberService memberService) {
        while (true) {
            printFrame("MENU ADMIN - KELOLA MEMBER");
            System.out.println("1. Tambah Member");
            System.out.println("2. Lihat Member");
            System.out.println("3. Update Member");
            System.out.println("4. Hapus Member");
            System.out.println("5. Cari Member (by ID)");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            String p = in.nextLine().trim();

            try {
                switch (p) {
                    case "1":
                        do {
                            System.out.print("Nama: ");
                            String name = in.nextLine().trim();
                            System.out.print("Masa aktif (bulan): ");
                            int aktif = Integer.parseInt(in.nextLine().trim());
                            Member m = memberService.create(name, aktif);
                            System.out.println("Member '" + m.getName() + "' berhasil ditambahkan dengan ID " + m.getId());
                        } while (repeat("menambah member"));
                        break;

                    case "2":
                        do {
                            List<Member> all = memberService.readAll();
                            if (all.isEmpty()) System.out.println("Tidak ada member.");
                            else for (Member mem : all) printMember(mem);
                        } while (repeat("melihat member"));
                        break;

                    case "3":
                        do {
                            System.out.print("ID yang diupdate: ");
                            int uid = Integer.parseInt(in.nextLine().trim());
                            printMember(memberService.findById(uid));
                            System.out.print("Nama baru (kosong skip): ");
                            String n = in.nextLine().trim();
                            System.out.print("Masa aktif baru (0 skip): ");
                            int a = Integer.parseInt(in.nextLine().trim());
                            memberService.update(uid, n, a);
                            System.out.println("Member dengan ID " + uid + " berhasil diupdate.");
                        } while (repeat("mengupdate member"));
                        break;

                    case "4":
                        do {
                            System.out.print("ID yang dihapus: ");
                            int did = Integer.parseInt(in.nextLine().trim());
                            memberService.delete(did);
                            System.out.println("Member dengan ID " + did + " telah berhasil dihapus.");
                        } while (repeat("menghapus member"));
                        break;

                    case "5":
                        do {
                            System.out.print("Masukkan ID member: ");
                            int id = Integer.parseInt(in.nextLine().trim());
                            printMember(memberService.findById(id));
                        } while (repeat("mencari member"));
                        break;

                    case "0":
                        return;

                    default:
                        System.out.println("Menu tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input angka tidak valid.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void menuUser(User current, ProgressService progressService) {
        while (true) {
            printFrame("MENU USER - " + current.getUsername());
            System.out.println("1. CREATE Progress");
            System.out.println("2. READ Progress Saya");
            System.out.println("3. UPDATE Progress");
            System.out.println("4. DELETE Progress");
            System.out.println("5. SEARCH Progress (by ID)");
            System.out.println("6. Logout");
            System.out.print("Pilih: ");
            String p = in.nextLine().trim();

            try {
                switch (p) {
                    case "1":
                        do {
                            handleCreateProgress(current, progressService);
                        } while (repeat("menambah progress"));
                        break;

                    case "2":
                        do {
                            handleReadProgress(current, progressService);
                        } while (repeat("melihat progress"));
                        break;

                    case "3":
                        do {
                            handleUpdateProgress(current, progressService);
                        } while (repeat("mengupdate progress"));
                        break;

                    case "4":
                        do {
                            handleDeleteProgress(current, progressService);
                        } while (repeat("menghapus progress"));
                        break;

                    case "5":
                        do {
                            handleSearchProgress(current, progressService);
                        } while (repeat("mencari progress"));
                        break;

                    case "6":
                        return;

                    default:
                        System.out.println("Menu tidak valid.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleCreateProgress(User current, ProgressService service) {
        String tanggal;
        while (true) {
            System.out.print("Tanggal latihan (dd-mm-yyyy): ");
            tanggal = in.nextLine().trim();
            if (Validator.matchDate(tanggal)) break;
            System.out.println("Format tanggal salah. Contoh: 09-09-2025");
        }

        String tipe = pilihTipeLatihan();

        System.out.print("Gerakan + repetisi/set/beban: ");
        String gerakan = in.nextLine().trim();
        Validator.require(Validator.notBlank(gerakan), "Gerakan tidak boleh kosong.");

        System.out.print("Otot yang dilatih: ");
        String otot = in.nextLine().trim();
        Validator.require(Validator.notBlank(otot), "Otot yang dilatih tidak boleh kosong.");

        int durasi;
        while (true) {
            System.out.print("Durasi latihan (>=10 menit): ");
            String s = in.nextLine().trim();
            try {
                durasi = Integer.parseInt(s);
                Validator.require(durasi >= 10, "Durasi minimal 10 menit.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Durasi harus angka.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String id = service.create(current.getUsername(), tanggal, tipe, gerakan, otot, durasi);
        System.out.println("Progress ditambahkan dengan ID: " + id);
    }

    private static void handleReadProgress(User current, ProgressService service) {
        List<Progress> mine = service.readByUser(current.getUsername());
        if (mine.isEmpty()) System.out.println("Belum ada progress.");
        else for (Progress p : mine) printProgress(p);
    }

    private static void handleUpdateProgress(User current, ProgressService service) {
        System.out.print("Masukkan ID progress yang ingin diupdate: ");
        String id = in.nextLine().trim();
        Progress existing = service.searchById(current.getUsername(), id);
        printProgress(existing);

        String t = null;
        while (true) {
            System.out.print("Tanggal baru (dd-mm-yyyy, kosong skip): ");
            String tmp = in.nextLine().trim();
            if (!Validator.notBlank(tmp)) break;
            if (Validator.matchDate(tmp)) { t = tmp; break; }
            System.out.println("Format tanggal salah.");
        }

        String tipe = null;
        System.out.print("Ubah tipe latihan? (y/n): ");
        if (in.nextLine().trim().equalsIgnoreCase("y")) tipe = pilihTipeLatihan();

        System.out.print("Gerakan baru (kosong skip): ");
        String g = in.nextLine().trim(); if (!Validator.notBlank(g)) g = null;

        System.out.print("Otot baru (kosong skip): ");
        String o = in.nextLine().trim(); if (!Validator.notBlank(o)) o = null;

        Integer d = null;
        System.out.print("Durasi baru (>=10, kosong skip): ");
        String ds = in.nextLine().trim();
        if (Validator.notBlank(ds)) {
            try {
                int parsed = Integer.parseInt(ds);
                if (parsed > 0) d = parsed;
            } catch (NumberFormatException e) {
                System.out.println("Durasi tidak valid, diabaikan.");
            }
        }

        service.update(current.getUsername(), id, t, tipe, g, o, d);
        System.out.println("Progress diupdate.");
    }

    private static void handleDeleteProgress(User current, ProgressService service) {
        System.out.print("Masukkan ID progress yang ingin dihapus: ");
        String id = in.nextLine().trim();
        Progress p = service.searchById(current.getUsername(), id);
        printProgress(p);
        System.out.print("Apakah anda ingin menghapus progress? (Y/N): ");
        String conf = in.nextLine().trim().toUpperCase();
        if (conf.equals("Y")) {
            service.delete(current.getUsername(), id);
            System.out.println("Progress dengan ID " + id + " berhasil dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    private static void handleSearchProgress(User current, ProgressService service) {
        System.out.print("Masukkan ID progress: ");
        String id = in.nextLine().trim();
        try {
            Progress p = service.searchById(current.getUsername(), id);
            printProgress(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleSearchProgressGlobal(ProgressService service) {
        System.out.print("Masukkan ID progress (global): ");
        String id = in.nextLine().trim();
    try {
        Progress p = service.searchById(id);
        printProgress(p);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

    private static boolean repeat(String action) {
        System.out.print("Apakah ingin " + action + " lagi? (Y/N): ");
        return in.nextLine().trim().equalsIgnoreCase("Y");
    }

    private static String pilihTipeLatihan() {
        while (true) {
            System.out.println("Pilih tipe latihan:");
            System.out.println("1. HIIT");
            System.out.println("2. STRENGTH");
            System.out.println("3. CARDIO");
            System.out.println("4. YOGA");
            System.out.print("Masukkan pilihan (1-4): ");
            String opt = in.nextLine().trim();
            switch (opt) {
                case "1": return "HIIT";
                case "2": return "STRENGTH";
                case "3": return "CARDIO";
                case "4": return "YOGA";
                default: System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void printFrame(String title) {
        System.out.println("========================================");
        System.out.println(" " + title);
        System.out.println("========================================");
    }

    private static void printMember(Member m) {
        System.out.println("----------------------------------------");
        System.out.println("ID Member       : " + m.getId());
        System.out.println("Nama            : " + m.getName());
        System.out.println("Masa Aktif      : " + m.getMasaAktifBulan() + " bulan");
    }

    private static void printProgress(Progress p) {
        System.out.println("----------------------------------------");
        System.out.println("ID Progress     : " + p.getId());
        System.out.println("Tanggal Latihan : " + p.getTanggal());
        System.out.println("Tipe Latihan    : " + p.getTipe());
        System.out.println("Gerakan         : " + p.getGerakan());
        System.out.println("Otot Dilatih    : " + p.getOtot());
        System.out.println("Durasi (menit)  : " + p.getDurasi());
    }
}
