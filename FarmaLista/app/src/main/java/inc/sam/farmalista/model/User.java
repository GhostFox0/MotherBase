package inc.sam.farmalista.model;

/**
 * Created by sam on 02/01/17.
 */


//Model Class for User
public class User {

    private int id_user;
    private String email;
    private String name;
    private String surname;
    private String password;
    private String comune_nascita;
    private String gender;
    private String fiscal_code;
    private String birth_date;
    private String indirizzo;


    //Minimal constructor for registration/login of an User
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Complete constructor for update User
    public User(String name, String surname, String comune_nascita, String gender, String fiscal_code, String birth_date, String indirizzo) {
        this.name = name;
        this.surname = surname;
        this.comune_nascita = comune_nascita;
        this.gender = gender;
        this.fiscal_code = fiscal_code;
        this.birth_date = birth_date;
        this.indirizzo = indirizzo;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComune_nascita() {
        return comune_nascita;
    }

    public void setComune_nascita(String comune_nascita) {
        this.comune_nascita = comune_nascita;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFiscal_code() {
        return fiscal_code;
    }

    public void setFiscal_code(String fiscal_code) {
        this.fiscal_code = fiscal_code;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }





}
