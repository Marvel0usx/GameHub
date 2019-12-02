package com.example.userinterface.GameManager.SpaceInvaders;

import java.util.ArrayList;
import java.util.List;

public class BulletFactory {
    public static ArrayList<Bullet> buildBullet(String owner, int mode, int x, int y) {
        ArrayList<Bullet> bulletList = new ArrayList<>();
        switch (owner) {
            case "Player":
                if (mode == 1) {
                    bulletList.add(new PlayerBullet(x + 37, y, 25, -12));
                }
                if (mode == 2){
                    bulletList.add(new PlayerBullet(x+37, y, 25, -12));
                    bulletList.add(new PlayerBullet(x+87, y, 25, -12));
                    bulletList.add(new PlayerBullet(x-13, y, 25, -12));
                }
                if (mode == 3){

                    bulletList.add(new PlayerBullet(x+117, y, 25, -12));
                    bulletList.add(new PlayerBullet(x+37, y, 25, -12));
                    bulletList.add(new PlayerBullet(x+77, y, 25, -12));
                    bulletList.add(new PlayerBullet(x+37-40, y, 25, -12));
                    bulletList.add(new PlayerBullet(x+37-80, y, 25, -12));
                }
                break;
            case "Enemy":
                if (mode == 1) {
                    bulletList.add(new EnemyBullet(x, y, 100, 4));
                }
                if (mode == 2) {
                    bulletList.add(new EnemyBullet(x + 50, y, 100, 12));
                    bulletList.add(new EnemyBullet(x - 50, y, 100, 12));
                    bulletList.add(new EnemyBullet(x, y, 100, 12));
                }
                break;
            case "Boss":
                bulletList.add(new EnemyBullet(x + 50, y, 100, 25));
                bulletList.add(new EnemyBullet(x - 50, y, 100, 25));
                bulletList.add(new EnemyBullet(x, y, 100, 25));
        }
        return bulletList;
    }
}
