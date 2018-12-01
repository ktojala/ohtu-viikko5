
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int JOUKON_OLETUS_KAPASITEETTI = 5;
    public final static int JOUKON_OLETUS_KASVATUS_KOKO = 5; 

    private int kasvatuskoko;
    private int[] lukuTaulukko;
    private int alkioidenLkm;

    public IntJoukko() {
        alustaTaulukko(JOUKON_OLETUS_KAPASITEETTI, JOUKON_OLETUS_KASVATUS_KOKO);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alustaTaulukko(kapasiteetti, JOUKON_OLETUS_KASVATUS_KOKO);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
           return;
        }
        if (kasvatuskoko < 0) {
            return;
        }
        alustaTaulukko(kapasiteetti, kasvatuskoko);
    }

    public void lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukuTaulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukuTaulukko.length == 0) {
                kasvataTaulukkoa();
            }
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuTaulukko[i]) {
                return true;
            }
        }
        return false;
    }

    public void poista(int luku) {
        int paikka = etsiLuvunPaikkaTaulukossa(luku);
        if (paikka != -1) {
            lukuTaulukko[paikka] = lukuTaulukko[alkioidenLkm - 1];
            lukuTaulukko[alkioidenLkm - 1] = 0;
            alkioidenLkm--;
        }
    }

    private void kopioiTaulukko(int[] vanhaTaulukko, int[] uusiTaulukko) {
        System.arraycopy(vanhaTaulukko, 0, uusiTaulukko, 0, vanhaTaulukko.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukuTaulukko[i] + ", ";
        }
        if (alkioidenLkm > 0) {
            tuotos += lukuTaulukko[alkioidenLkm - 1];
        }
        return tuotos + "}";
    }

    public int[] toIntArray() {
        int[] taulukko = new int[alkioidenLkm];
        System.arraycopy(lukuTaulukko, 0, taulukko, 0, taulukko.length);
        return taulukko;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        lisaaTaulukkoon(yhdisteJoukko, a.toIntArray());
        lisaaTaulukkoon(yhdisteJoukko, b.toIntArray());
        int[] aTaulukko = a.toIntArray();
        int[] bTaulukko = b.toIntArray();
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulukko = a.toIntArray();
        for (int i = 0; i < aTaulukko.length; i++) {
            if (b.kuuluu(aTaulukko[i])) {
                leikkausJoukko.lisaa(aTaulukko[i]);
            }
        }
        return leikkausJoukko;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        lisaaTaulukkoon(erotusJoukko, a.toIntArray());
        int[] bTaulukko = b.toIntArray();
        for (int i = 0; i < bTaulukko.length; i++) {
            erotusJoukko.poista(bTaulukko[i]);
        }
        return erotusJoukko;
    }

    public void alustaTaulukko(int kapasiteetti, int kasvatuskoko) {
        this.lukuTaulukko = new int[kapasiteetti];
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    public void kasvataTaulukkoa() {
        int[] apuTaulukko = new int[this.alkioidenLkm + this.kasvatuskoko];
        System.arraycopy(this.lukuTaulukko, 0, apuTaulukko, 0, alkioidenLkm);
        this.lukuTaulukko = apuTaulukko;
    }
    
    public int etsiLuvunPaikkaTaulukossa(int luku) {
        if (kuuluu(luku)) {
            for (int i = 0; i < this.alkioidenLkm; i++) {
                if (this.lukuTaulukko[i] == luku) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private static void lisaaTaulukkoon(IntJoukko taulukko, int[] lisattavaTaulukko) {
        for (int i = 0; i < lisattavaTaulukko.length; i++) {
            taulukko.lisaa(lisattavaTaulukko[i]);
        }
    }
    
}