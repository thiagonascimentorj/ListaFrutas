package thiago.app.com.listafrutas;

/**
 * Created by Thiago on 02/08/2017.
 */

public class Fruta {

    private String imagem;
    private String nome;
    private double preco;

    public Fruta(String imagem, String nome, double preco) {
        this.imagem = imagem;
        this.nome = nome;
        this.preco = preco;
    }


    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco * 3.5;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
