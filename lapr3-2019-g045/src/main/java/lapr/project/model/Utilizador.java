package lapr.project.model;

import java.util.Objects;
import lapr.project.data.UtilizadorBD;

public class Utilizador {
    
    private int idUtilizador;
    
    private String username;
    
    private String password;
    
    private String email;
    
    private String nome;
    
    private String cartaoCredito;
    
    private Float peso;
    
    private Float altura;
    
    private Float velocidadeMedia = 5.5f;
    
    private String genero;
    
    private Integer Pontos;
    
    private UtilizadorBD bd = new UtilizadorBD();

    public Utilizador() {
    }
    
    public Utilizador(int idUtilizador, String username, String password, String email, String nome, String cartaoCredito, float peso, float altura, Float velocidadeMedia, String genero) {
        this(username, password, email, nome, cartaoCredito, peso, altura, velocidadeMedia, genero);
        this.idUtilizador = idUtilizador;
    }
    
    public Utilizador(String username, String password, String email, String nome, String cartaoCredito, float peso, float altura, float velocidadeMedia, String genero) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cartaoCredito = cartaoCredito;
        this.peso = peso;
        this.altura = altura;
        this.velocidadeMedia = velocidadeMedia;
        this.genero = genero;
    }

    public Utilizador(String username, String email, Float altura, Float peso, Float velocidadeMedia, String cartaoCredito, String genero, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.cartaoCredito = cartaoCredito;
        this.peso = peso;
        this.altura = altura;
        this.velocidadeMedia = velocidadeMedia;
        this.genero = genero;
        this.nome = email;
    }

    public Utilizador(String username, String email, String password, String visaCardNumber, float height, float weight, Float velocidadeMedia, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cartaoCredito = visaCardNumber;
        this.peso = height;
        this.altura = weight;
        this.velocidadeMedia = velocidadeMedia;
        this.genero = gender;
    }

    public boolean save() {
        try {
            bd.getUtilizadorByUserName(username);
        } catch (IllegalArgumentException e) {
            return bd.addUtilizador(this);
        }
        return bd.atualizaUtilizador(this);

    }

    public int getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(int idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(Float velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getPontos() {
        return Pontos;
    }

    public void setPontos(Integer Pontos) {
        this.Pontos = Pontos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.cartaoCredito);
        hash = 53 * hash + Float.floatToIntBits(this.peso);
        hash = 53 * hash + Float.floatToIntBits(this.altura);
        hash = 53 * hash + Float.floatToIntBits(this.velocidadeMedia);
        hash = 53 * hash + Objects.hashCode(this.genero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        return this.hashCode() == obj.hashCode();
    }
    
    public void setBd(UtilizadorBD bd) {
        this.bd = bd;
    }
    
    
}