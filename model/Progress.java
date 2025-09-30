package model;

public class Progress implements Printable {
    private String id;        
    private String username;  
    private String tanggal;   
    private String tipe;      
    private String gerakan;   
    private String otot;      
    private int durasi;       

    public Progress(String id, String username, String tanggal, String tipe, String gerakan, String otot, int durasi) {
        this.id = id;
        this.username = username.trim();
        this.tanggal = tanggal.trim();
        this.tipe = tipe.trim();
        this.gerakan = gerakan.trim();
        this.otot = otot.trim();
        this.durasi = durasi;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getTanggal() { return tanggal; }
    public String getTipe() { return tipe; }
    public String getGerakan() { return gerakan; }
    public String getOtot() { return otot; }
    public int getDurasi() { return durasi; }

    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setTipe(String tipe) { this.tipe = tipe; }
    public void setGerakan(String gerakan) { this.gerakan = gerakan; }
    public void setOtot(String otot) { this.otot = otot; }
    public void setDurasi(int durasi) { this.durasi = durasi; }

    @Override
    public String toString() {
        return "Progress{id='" + id + "', user='" + username + "', tanggal='" + tanggal + "', tipe='" + tipe +
                "', gerakan='" + gerakan + "', otot='" + otot + "', durasi=" + durasi + " menit}";
    }

    @Override
    public void printData() {
        System.out.println("----------------------------------------");
        System.out.println("ID Progress     : " + id);
        System.out.println("Tanggal Latihan : " + tanggal);
        System.out.println("Tipe Latihan    : " + tipe);
        System.out.println("Gerakan         : " + gerakan);
        System.out.println("Otot Dilatih    : " + otot);
        System.out.println("Durasi (menit)  : " + durasi);
    }
}
