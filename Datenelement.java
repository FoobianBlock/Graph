public class Datenelement {
    private String ortsname;
    private int uebernachtungsplaetze;

    public Datenelement(String oName, int plaetze) {
        ortsname = oName;
        uebernachtungsplaetze = plaetze;
    }

    public String gibOrtsname() { return ortsname; }
    public int gibUePlaetze() { return uebernachtungsplaetze; }
}
