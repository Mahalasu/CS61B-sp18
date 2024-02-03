/**
 * @author daigaoshang
 * @date 2024/2/3
 */
public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        Planet[] planets = new Planet[n];
        in.readDouble();
        for (int i = 0; i < n; i++) {
            double pX = in.readDouble();
            double pY = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(pX, pY, vX, vY, mass, img);
        }
        return planets;
    }

    private static void draw(Planet[] planets) {
        for (Planet planet : planets) {
            planet.draw();
        }
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        for (double time = 0; time < T; time += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            draw(planets);
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }

}
