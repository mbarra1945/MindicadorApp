package com.example.mbarra.mindicadorapp;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Indicador {

    @SerializedName("version")
    private String version;
    @SerializedName("autor")
    private String autor;
    @SerializedName("codigo")
    private String codigo;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("unidad_medida")
    private String unidad_medida;
    private ArrayList<Object> serie;

    private static final String JSON_VERSION = "version";
    private static final String JSON_AUTOR = "autor";
    private static final String JSON_CODIGO = "codigo";
    private static final String JSON_NOMBRE = "nombre";
    private static final String JSON_UNIDAD_MEDIDA = "unidad_medida";
    private static final String JSON_SERIE = "serie";

    public Indicador(JSONObject jo) throws JSONException {
        this.version = jo.getString(JSON_VERSION);
        this.autor = jo.getString(JSON_AUTOR);
        this.codigo = jo.getString(JSON_CODIGO);
        this.nombre = jo.getString(JSON_NOMBRE);
        this.unidad_medida = jo.getString(JSON_UNIDAD_MEDIDA);
        this.serie = Utility.convertToArrayList(jo.getJSONArray(JSON_SERIE));
    }

    public JSONObject convertToJSON() throws JSONException {
        JSONObject jo = new JSONObject();

        jo.put(JSON_VERSION, version);
        jo.put(JSON_AUTOR, autor);
        jo.put(JSON_CODIGO, codigo);
        jo.put(JSON_NOMBRE, nombre);
        jo.put(JSON_UNIDAD_MEDIDA, unidad_medida);
        jo.put(JSON_SERIE, new JSONArray(serie));

        return jo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public ArrayList<Object> getSerie() {
        return serie;
    }

    public void setSerie(ArrayList<Object> serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Indicador{" +
                "version='" + version + '\'' +
                ", autor='" + autor + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", unidad_medida='" + unidad_medida + '\'' +
                ", serie=" + serie +
                '}';
    }
}
class Serie {
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("valor")
    private Double valor;

    private static final String JSON_FECHA = "fecha";
    private static final String JSON_VALOR = "valor";

    public Serie(JSONObject jo) throws JSONException {
        this.fecha = jo.getString(JSON_FECHA);
        this.valor = jo.getDouble(JSON_VALOR);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "fecha='" + fecha + '\'' +
                ", valor=" + valor +
                '}';
    }
}
