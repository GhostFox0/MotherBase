/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Utente
 */
public class Ingrediente {
    
    private String nomeIngrediente;
    private String categoriaIngrediente;
    private double grammaturaIngrediente;
    private String destinatarioIngrediente;
    
    private final double DB_KCAL = 0.0;
    private final double DB_PROTEINE = 0.0;
    private final double DB_LIPIDI = 0.0;
    private final double DB_CARBOIDRATI = 0.0;
    private final double DB_FIBRA = 0.0;
    private final double DB_SODIO = 0.0;
    
    private double kcal = (DB_KCAL/100)*grammaturaIngrediente;
    private double proteine = (DB_PROTEINE/100)*grammaturaIngrediente;
    private double lipidi = (DB_LIPIDI/100)*grammaturaIngrediente;
    private double carboidrati = (DB_CARBOIDRATI/100)*grammaturaIngrediente;
    private double fibra = (DB_FIBRA/100)*grammaturaIngrediente;
    private double sodio = (DB_SODIO/100)*grammaturaIngrediente;
    
    
    
    
    
}
