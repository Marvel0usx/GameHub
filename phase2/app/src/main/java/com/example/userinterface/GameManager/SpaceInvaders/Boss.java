package com.example.userinterface.GameManager.SpaceInvaders;

class Boss extends Enemy {
    Boss(int x, int y, int damage, int xSpeed, int ySpeed, int lives, int size) {
        super(x, y, damage, xSpeed, ySpeed, lives, size);
    }

    /**
     * shoots an enemyBullet object by generating a list of bullets, which is added to an observer
     * list.
     */
    @Override
    void shoot() {
        this.enemyBullets = BulletFactory.buildBullet("Boss", 1, this.getX(), this.getY());
        setChanged();
        for (Observer obs : getObservers()) {
            for (Bullet bullet : this.enemyBullets)
                bullet.registerObserver(obs);
        }
        for (Bullet bullet : this.enemyBullets)
            bullet.setUpdated(true);
    }
}
