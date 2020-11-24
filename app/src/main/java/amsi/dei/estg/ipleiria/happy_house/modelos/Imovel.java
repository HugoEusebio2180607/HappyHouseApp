package amsi.dei.estg.ipleiria.happy_house.modelos;

public class Imovel {
    private int id,area,nquartos,nwc,piso,imagem;
    private double preco;
    private String cidade,estado,descricao,morada,codigo_postal,garagem;


    public int getId() {
        return id;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) { this.area = area; }

    public int getNquartos() { return nquartos; }

    public void setNquartos(int nquartos) { this.nquartos = nquartos; }

    public int getNwc() { return nwc; }

    public void setNwc(int nwc) { this.nwc = nwc; }

    public int getPiso() { return piso; }

    public void setPiso(int piso) { this.piso = piso; }

    public int getImagem() { return imagem; }

    public void setImagem(int imagem) { this.imagem = imagem; }

    public double getPreco() { return preco; }

    public void setPreco(double preco) { this.preco = preco; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMorada() { return morada; }

    public void setMorada(String morada) { this.morada = morada; }

    public String getCodigo_postal() { return codigo_postal; }

    public void setCodigo_postal(String codigo_postal) { this.codigo_postal = codigo_postal; }

    public String getGaragem() { return garagem; }

    public void setGaragem(String garagem) { this.garagem = garagem; }

    public Imovel(int id, int area, int nquartos, int nwc, int piso, int imagem, String cidade, String estado, String descricao, String morada, String codigo_postal, double preco, String garagem) {
        this.id = id;
        this.area = area;
        this.nquartos = nquartos;
        this.nwc = nwc;
        this.piso = piso;
        this.imagem = imagem;
        this.cidade = cidade;
        this.estado = estado;
        this.descricao = descricao;
        this.morada= morada;
        this.codigo_postal=codigo_postal;
        this.garagem = garagem;
        this.preco = preco;
    }

}
