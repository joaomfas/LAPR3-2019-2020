package lapr.project.utils;

import java.sql.Date;
import java.util.concurrent.TimeUnit;
import lapr.project.model.*;

public class Calculos {

    public static double distEntreDoisLocais(double latA, double lonA, double latB, double lonB) {
        double lat1 = latA;
        double lon1 = lonA;
        double lat2 = latB;
        double lon2 = lonB;
        // shortest distance over the earth's surface
        // https://www.movable-type.co.uk/scripts/latlong.html
        final double R = 6371e3;
        double theta1 = Math.toRadians(lat1);
        double theta2 = Math.toRadians(lat2);
        double deltaTheta = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2)
                + Math.cos(theta1) * Math.cos(theta2)
                * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //double d = (R * c) / 1000; // distância em kilometros
        double d = (R * c); // distância em meters
        return d;
    }

    /**
     *
     *
     * @param c
     * @param u
     * @param v
     * @param locA
     * @param locB
     * @return
     */
    public static double energiaEntreLocais(Caminho c, Utilizador u, Veiculo v, Local locA, Local locB) {
        Double g = 9.80665;
        Double dist = 1000D;
        Double difAltitudes = 0D;
        if (locA != null && locB != null) {
            //dist = distEntreDoisLocais(locA.getLatitude(), locA.getLongitude(), locB.getLatitude(), locB.getLongitude()) * 1000;
            dist = distEntreDoisLocais(locA.getLatitude(), locA.getLongitude(), locB.getLatitude(), locB.getLongitude());
            difAltitudes = (double) (locB.getElevacao() - locA.getElevacao());
        }
        Double declive = difAltitudes / dist;
        Double vel = null;
        if (v != null) {
            if (v instanceof Bicicleta) {
                if (u.getVelocidadeMedia() != null) {
                    vel = (double) (u.getVelocidadeMedia());
                }
            }
        }
        if (vel == null) {
            vel = 20.0 * 1000 / 3600; // 20km /h
        }
        Double massaUser = 62D;
        Double massaVeic = 20D;
        Double coefAero = 0.45;
        if (v != null && v.getPeso() != null) {
            massaVeic = (double) v.getPeso();
        }
        if (v != null && v.getCoeficienteAerodinamico() != null) {
            coefAero = v.getCoeficienteAerodinamico();
        }
        if (u != null && u.getPeso() != null) {
            massaUser = (double) u.getPeso();
        }
        Double massaTotal = massaUser + massaVeic;
        Double coefCinetico = 0.0053;
        Double velRVento = 0D;
        if (c != null) {
            if (c.getCoefCinetico() != null) {
                coefCinetico = c.getCoefCinetico();
            }
            if (c.getDirVento() != null && c.getVelVento() != null) {
                velRVento = velocidadeRelativaVento(c.getDirVento(), c.getVelVento(), locA, locB);
            }
        }
        double direcaoVento = velRVento > 0 ? -1 : 1;
        Double densAr = 1.225;
        Double areaFrontal = 0.6;
        if (v != null && v.getAreaFrontal() != null) {
            areaFrontal = v.getAreaFrontal();
        }

        double velocidadeArrasto = 0;
        if (velRVento < 1.5) {
            velocidadeArrasto = Math.pow(vel, 3);
        } else {
            velocidadeArrasto = vel * Math.pow(velRVento, 2);
        }
        double potArrasto = 0.5 * densAr * velocidadeArrasto * coefAero * areaFrontal;

        double potAtrito = vel * massaTotal * g * coefCinetico;

        double potGravidade;
        if (declive < 0) {
            potGravidade = 0;
        } else {
            potGravidade = vel * massaTotal * g * declive;
        }

        Double potTotal = potArrasto + potAtrito + potGravidade;
        Double tempo = (dist / vel) / 3600; // em horas
        return potTotal * tempo / (1000); // em KWh
    }

    public static Double direcaoEntreDoisLocais(Local localA, Local localB) {
        double lonA = localA.getLongitude();
        double lonB = localB.getLongitude();
        double latA = Math.toRadians(localA.getLatitude());
        double latB = Math.toRadians(localB.getLatitude());
        double longDiff = Math.toRadians(lonB - lonA);
        double y = Math.sin(longDiff) * Math.cos(latB);
        double x = Math.cos(latA) * Math.sin(latB) - Math.sin(latA) * Math.cos(latB) * Math.cos(longDiff);
        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    public static Double velocidadeRelativaVento(Double dirVento, Double velVento, Local locA, Local locB) {
        Double pathBearing = direcaoEntreDoisLocais(locA, locB);
        double relativeWindBearing = pathBearing - dirVento;
        return Math.cos(Math.abs(relativeWindBearing)) * velVento;
    }

    public static long diferencaTempoSegundos(Date dataI, Date dataF) {
        if (dataI == null || dataF == null) {
            return 0;
        }
        long diffInMillis = Math.abs(dataF.getTime() - dataI.getTime());
        return TimeUnit.SECONDS.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    public static Double round2Decimals(Double a) {
        return Math.round(a * 100.0) / 100.0;
    }

}
