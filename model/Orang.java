package model;

public abstract class Orang {
    protected String name;

    public Orang(String name) {
        this.name = name.trim();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name.trim(); }

    // Abstract method → wajib dioverride di subclass
    public abstract void introduce();
}
