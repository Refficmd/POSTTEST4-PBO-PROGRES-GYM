package service;

import model.Progress;
import java.util.ArrayList;
import java.util.List;

public class ProgressService {
    private final ArrayList<Progress> progresses = new ArrayList<>();
    private int seq = 1;

    public ProgressService() {
        seedReffi();
    }

    private void seedReffi() {
        String u = "Reffi";
        progresses.add(new Progress(nextId(), u, "01-09-2025", "HIIT", "Burpees 3x12", "Full Body", 30));
        progresses.add(new Progress(nextId(), u, "02-09-2025", "STRENGTH", "Bench Press 4x10 40kg", "Dada", 45));
        progresses.add(new Progress(nextId(), u, "03-09-2025", "CARDIO", "Treadmill 6km", "Kaki", 35));
        progresses.add(new Progress(nextId(), u, "04-09-2025", "YOGA", "Sun Salutation x5", "Core", 50));
        progresses.add(new Progress(nextId(), u, "05-09-2025", "STRENGTH", "Deadlift 5x5 60kg", "Punggung", 40));
        progresses.add(new Progress(nextId(), u, "06-09-2025", "HIIT", "Jumping Jack 3x20", "Full Body", 25));
        progresses.add(new Progress(nextId(), u, "07-09-2025", "STRENGTH", "Squat 4x12 50kg", "Kaki", 45));
        progresses.add(new Progress(nextId(), u, "08-09-2025", "CARDIO", "Sepeda statis 10km", "Kaki", 30));
        progresses.add(new Progress(nextId(), u, "09-09-2025", "YOGA", "Warrior Pose x10", "Core", 35));
        progresses.add(new Progress(nextId(), u, "10-09-2025", "HIIT", "Mountain Climber 3x15", "Core", 20));
    }

    private String nextId() {
        return "P" + (seq++);
    }

    public String create(String username, String tanggal, String tipe, String gerakan, String otot, int durasi) {
        Validator.require(Validator.matchDate(tanggal), "Format tanggal harus dd-mm-yyyy.");
        Validator.require(Validator.notBlank(tipe), "Tipe latihan wajib.");
        Validator.require(Validator.notBlank(gerakan), "Gerakan wajib.");
        Validator.require(Validator.notBlank(otot), "Otot yang dilatih wajib.");
        Validator.require(durasi >= 10, "Durasi minimal 10 menit.");
        String id = nextId();
        progresses.add(new Progress(id, username, tanggal, tipe, gerakan, otot, durasi));
        return id;
    }

    public List<Progress> readByUser(String username) {
        ArrayList<Progress> result = new ArrayList<>();
        for (Progress p : progresses) {
            if (p.getUsername().equalsIgnoreCase(username)) {
                result.add(p);
            }
        }
        return result;
    }

    public Progress findByIdOwned(String username, String id) {
        return progresses.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id) && p.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Progress dengan ID " + id + " tidak ditemukan untuk akun ini."
                ));
    }

    public void update(String username, String id, String tanggal, String tipe, String gerakan, String otot, Integer durasi) {
        Progress p = findByIdOwned(username, id);
        if (Validator.notBlank(tanggal)) {
            Validator.require(Validator.matchDate(tanggal), "Format tanggal harus dd-mm-yyyy.");
            p.setTanggal(tanggal);
        }
        if (Validator.notBlank(tipe)) p.setTipe(tipe);
        if (Validator.notBlank(gerakan)) p.setGerakan(gerakan);
        if (Validator.notBlank(otot)) p.setOtot(otot);
        if (durasi != null && durasi > 0) {
            Validator.require(durasi >= 10, "Durasi minimal 10 menit.");
            p.setDurasi(durasi);
        }
    }

    public Progress delete(String username, String id) {
        Progress p = findByIdOwned(username, id);
        progresses.remove(p);
        return p;
    }

    public Progress searchById(String username, String id) {
        return findByIdOwned(username, id);
    }
    public Progress searchById(String id) {
        return progresses.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Progress dengan ID " + id + " tidak ditemukan."
                ));
    }
}
