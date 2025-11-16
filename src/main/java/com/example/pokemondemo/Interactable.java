package com.example.pokemondemo;

public class Interactable {
    public double x, y;
    public double w, h;

    public Interactable(double x, double y, double w, double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean intersects(double px, double py, double pw, double ph){
        return px < x + w &&
                px + pw > x &&
                py < y + h &&
                py + ph > y;
    }
    // En interaktionszone (arena-dør)
    public Interactable arenaDoor = new Interactable(350, 150, 32, 32);

}
