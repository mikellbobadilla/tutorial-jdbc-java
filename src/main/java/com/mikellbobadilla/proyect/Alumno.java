package com.mikellbobadilla.proyect;

import java.util.Date;

public class Alumno {
  private Long id;
  private String nombre;
  private String apellidos;
  private Date fechaNacimiento;

  public Alumno(String nombre, String apellidos, Date fechaNacimiento) {
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.fechaNacimiento = fechaNacimiento;
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

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  @Override
  public String toString() {
    return "Alumno{" +
      "id=" + id +
      ", nombre='" + nombre + '\'' +
      ", apellidos='" + apellidos + '\'' +
      '}';
  }
}
