package com.mikellbobadilla.escuela.models;

public class Profesor {
  private Long id;
  private String nombre;
  private String apellidos;

  public Profesor(String nombre, String apellidos) {
    this.nombre = nombre;
    this.apellidos = apellidos;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  @Override
  public String toString() {
    return "Profesor{" +
      "id=" + id +
      ", nombre='" + nombre + '\'' +
      ", apellidos='" + apellidos + '\'' +
      '}';
  }
}
