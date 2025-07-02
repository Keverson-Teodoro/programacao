package Model.entitie;

public class Produto {
    private long id;
    private String name;
    private double preco;

    public Produto(long id, String name, double preco) {
        this.id = id;
        this.name = name;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
