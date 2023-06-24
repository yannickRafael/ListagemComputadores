package mz.ac.isutc.lecc31.yannick;

public class Computador {
    private String  marca, modelo, nrSerie, processador;
    private int ram, hd, id;



    public Computador(int i, String marca, String modelo, String nrSerie, String processador, int ram, int hd) {
        this.marca = marca;
        this.modelo = modelo;
        this.nrSerie = nrSerie;
        this.processador = processador;
        this.ram = ram;
        this.hd = hd;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNrSerie() {
        return nrSerie;
    }

    public void setNrSerie(String nrSerie) {
        this.nrSerie = nrSerie;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }
}
