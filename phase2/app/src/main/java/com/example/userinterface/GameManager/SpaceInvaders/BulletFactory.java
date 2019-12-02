package com.example.userinterface.GameManager.SpaceInvaders;

import java.util.ArrayList;

class BulletFactory {
    /**
     * A Factory design class pattern that returns a list of bullets given specifications from
     * various shoot() functions. Allows for simple extendability of new ship classes in the future
     * as well as adaptability for upgrading ships
     *
     * @param owner String that specifies who these bullets belong to
     * @param mode int that specifies the mode the ship is in
     * @param x starting x location of bullet
     * @param y starting y location of bullet
     * @return returns a list of bullets that are to be shot as per specifications
     */
    static ArrayList<Bullet> buildBullet(String owner, int mode, int x, int y) {
        ArrayList<Bullet> bulletList = new ArrayList<>();
        switch (owner) {
            case "Player":
                if (mode == 1) {
                    bulletList.add(new PlayerBullet(x + 37, y, 25, -12));
                }
                if (mode == 2){
                    bulletList.add(new PlayerBullet(x+37, y, 25, -12));
                    bulletList.add(new PlayerBullet(x+87, y, 25, -12));
                }
                if (mode == 3){

                    bulletList.add(new PlayerBullet(x+37-40, y, 50, -12));
                    bulletList.add(new PlayerBullet(x+37, y, 50, -12));
                    bulletList.add(new PlayerBullet(x+77, y, 50, -12));
                }
                break;
            case "Enemy":
                if (mode == 1) {
                    bulletList.add(new EnemyBullet(x, y, 100, 12));
                }
                if (mode == 2) {
                    bulletList.add(new EnemyBullet(x + 50, y, 100, 12));
                    bulletList.add(new EnemyBullet(x - 50, y, 100, 12));
                    bulletList.add(new EnemyBullet(x, y, 100, 12));
                }
                break;
            case "Boss":
                bulletList.add(new EnemyBullet(x + 150, y, 10, 20));
                bulletList.add(new EnemyBullet(x + 100, y, 10, 20));
                bulletList.add(new EnemyBullet(x + 50, y, 100, 20));
                bulletList.add(new EnemyBullet(x +200, y, 10, 20));
                bulletList.add(new EnemyBullet(x, y, 10, 20));
        }
        return bulletList;
    }
}
