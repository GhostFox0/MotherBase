package inc.sam.farmalista.model;

/**
 * Created by sam on 05/01/17.
 */
//Model class for Medicine
public class Medicine {

    private int id_medicine;
    private String name;
    private String quantita;
    private String id_utente;
    private String dosaggio;
    private String data_inizio;
    private String data_scadenza;
    private String giorni_preavviso;
    private String numero_confezioni;

    //Constructor complete for Medicine
    public Medicine(String name, String quantita, String dosaggio, String data_inizio, String data_scadenza, String giorni_preavviso, String numero_confezioni) {
        this.name = name;
        this.quantita = quantita;
        this.dosaggio = dosaggio;
        this.data_inizio = data_inizio;
        this.data_scadenza = data_scadenza;
        this.giorni_preavviso = giorni_preavviso;
        this.numero_confezioni = numero_confezioni;
    }

    public int getId_medicine() {
        return id_medicine;
    }

    public void setId_medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public String getId_utente() {
        return id_utente;
    }

    public void setId_utente(String id_utente) {
        this.id_utente = id_utente;
    }

    public String getDosaggio() {
        return dosaggio;
    }

    public void setDosaggio(String dosaggio) {
        this.dosaggio = dosaggio;
    }

    public String getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(String data_inizio) {
        this.data_inizio = data_inizio;
    }

    public String getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(String data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public String getGiorni_preavviso() {
        return giorni_preavviso;
    }

    public void setGiorni_preavviso(String giorni_preavviso) {
        this.giorni_preavviso = giorni_preavviso;
    }

    public String getNumero_confezioni() {
        return numero_confezioni;
    }

    public void setNumero_confezioni(String numero_confezioni) {
        this.numero_confezioni = numero_confezioni;
    }
}
