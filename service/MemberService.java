package service;

import model.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private final ArrayList<Member> members = new ArrayList<>();
    private int seq = 1;

    public MemberService() {
        create("Budi", 6);
        create("Sari", 12);
        create("Andi", 3);
    }

    public Member create(String name, int masaAktif) {
        Validator.require(Validator.notBlank(name), "Nama tidak boleh kosong.");
        Validator.require(masaAktif > 0, "Masa aktif minimal 1 bulan.");
        int id = seq++;
        Member m = new Member(id, name, masaAktif);
        members.add(m);
        return m;
    }

    public List<Member> readAll() {
        return members;
    }

    public void update(int id, String newName, int newAktif) {
        Member m = findById(id);
        if (Validator.notBlank(newName)) m.setName(newName);
        if (newAktif > 0) m.setMasaAktifBulan(newAktif);
    }

    public void delete(int id) {
        Member m = findById(id);
        members.remove(m);
    }

    public Member findById(int id) {
        return members.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Member dengan ID " + id + " tidak ditemukan."
                ));
    }
}
