public class Knoten {
    private Datenelement daten;
    private boolean besucht;

    public Knoten(Datenelement d) {
        daten = d;
    }

    public Datenelement gibDaten() { return daten; }

    public void setzeBesucht(boolean value) { besucht = value; }
    public boolean gibBesucht() { return besucht; }
}
