package model;

public class Booking {
    private int id;
    private String username; // pemesan
    private int classId;     // refer ke GymClass.id

    public Booking(int id, String username, int classId) {
        this.id = id;
        this.username = username.trim().toLowerCase();
        this.classId = classId;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public int getClassId() { return classId; }

    @Override
    public String toString() {
        return "Booking{id=" + id + ", username='" + username + "', classId=" + classId + "}";
    }
}
