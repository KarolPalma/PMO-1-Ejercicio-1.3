package com.example.app01sqlite.transacciones;

public class Transacciones {

    // Tablas
    public static final String tablaPersona = "persona";
    // Campos
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido = "apellido";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    //Tablas - CREATE, DROP
    public static final String CreateTablePersona = "CREATE TABLE persona( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String DropTablePersona = "DROP TABLE IF EXITS persona";

    //Creación del nombre de la base de datos
    public static final String NameDataBase = "DBCurso";


}

