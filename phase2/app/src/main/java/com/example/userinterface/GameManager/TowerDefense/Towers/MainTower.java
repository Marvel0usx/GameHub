package com.example.userinterface.GameManager.TowerDefense.Towers;

import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.Ammo;
import com.example.userinterface.GameManager.TowerDefense.DifferentAmmo.AmmoFactory;
import com.example.userinterface.GameManager.TowerDefense.Enemy;

public class MainTower implements Towers {
    public int waitTime, time;
    String type;
    int x;
    int y;

    int range;

    public Ammo generateBullet(Enemy enemy) {
        AmmoFactory ammoFactory = new AmmoFactory();
        if (enemy.getY() > y - range && enemy.getY() < y + range) {
            if (time <= 0) {
                time = waitTime;
                System.out.println("Fasdfasdfasdfbajskdgfjasdgfhagskfgasifuasgfadagua");
                Ammo ammo = ammoFactory.buildAmmunition(type);
                System.out.println(ammo);
                ammo.setTarget(enemy);
                return ammo;
            } else {
                time--;
                return null;
            }
        } else {
            time = 0;
            return null;
        }
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getRange() {
        return range;
    }
}
