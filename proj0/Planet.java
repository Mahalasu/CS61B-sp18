/**
 * @author daigaoshang
 * @date 2024/2/3
 */
public class Planet {

    /**
     * xxPos:        Current x position<br/>
     * yyPos:        Current y position<br/>
     * xxVel:        Current velocity in the x direction<br/>
     * yyVel:        Current velocity in the y direction<br/>
     * mass:         Mass<br/>
     * imgFileName:  The name of the file that corresponds to the image
     *               that depicts the planet
     * G:            Gravitational Constant
     */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        return G * mass * p.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netX = 0;
        for (Planet planet : planets) {
            if (equals(planet)) {
                continue;
            }
            netX += calcForceExertedBy(planet) * (planet.xxPos - xxPos) / calcDistance(planet);
        }
        return netX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netY = 0;
        for (Planet planet : planets) {
            if (equals(planet)) {
                continue;
            }
            netY += calcForceExertedBy(planet) * (planet.yyPos - yyPos) / calcDistance(planet);
        }
        return netY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }

}
