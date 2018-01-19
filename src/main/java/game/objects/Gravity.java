package game.objects;

public class Gravity  extends GameObject {

    private double emplitude_gravity;

    public Gravity(double emplitude_gravity) {
        this.emplitude_gravity = emplitude_gravity;
    }

    public double getEmplitude_gravity() {
        return emplitude_gravity;
    }

    public void agis_sur_GameObject(GameObject g) {
        //g.setVelocityY(g.getVelocityY() - emplitude_gravity);
    }

    @Override
    public void collisionWith(GameObject g) {

    }

    @Override
    public GameObject clone() {
        return null;
    }
}
