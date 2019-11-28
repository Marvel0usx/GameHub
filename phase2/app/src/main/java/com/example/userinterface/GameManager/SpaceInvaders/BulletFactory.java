package com.example.userinterface.GameManager.SpaceInvaders;

import java.util.ArrayList;
import java.util.List;

public class BulletFactory {
    public static ArrayList<Bullet> buildBullet(String owner, int mode, int x, int y) {
        ArrayList<Bullet> bulletList = new ArrayList<>();
        switch (owner) {
            case "Player":
                if (mode == 1) {
                    bulletList.add(new PlayerBullet(x + 37, y, 50, -8));
                } else {
                    bulletList.add(new PlayerBullet(x+37, y, 50, -8));
                    bulletList.add(new PlayerBullet(x+87, y, 50, -8));
                    bulletList.add(new PlayerBullet(x-13, y, 50, -8));
                }
                break;
            case "Enemy":
                if (mode == 1) {
                    bulletList.add(new EnemyBullet(x, y, 100, 4));
                    System.out.println("this ran!");
                }
                if (mode == 2) {
                    bulletList.add(new EnemyBullet(x + 50, y, 100, 4));
                    bulletList.add(new EnemyBullet(x - 50, y, 100, 4));
                    bulletList.add(new EnemyBullet(x, y, 100, 4));
                }
                break;
        }
        return bulletList;
    }
}
