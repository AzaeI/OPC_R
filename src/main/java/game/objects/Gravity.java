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
        if (g instanceof Bird) {
            g.setVelocityY(g.getVelocityY() - emplitude_gravity);
        } else {

        }
    }

    @Override
    public GameObject clone() {
        return null;
    }
}